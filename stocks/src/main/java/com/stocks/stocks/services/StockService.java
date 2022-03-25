package com.stocks.stocks.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletResponse;

import com.stocks.stocks.dto.AllStockDto;
import com.stocks.stocks.dto.StockDto;
import com.stocks.stocks.dto.InfoStockDto;
import com.stocks.stocks.model.Stock;
import com.stocks.stocks.repository.StockRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {

    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;

    public Stock salvar(Stock stock) {
        return stockRepository.save(stock);
    }

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public InfoStockDto getInfoStock(Long id) throws NotFoundException {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            return new InfoStockDto(stock.get());
        } else {
            throw new NotFoundException("STOCK_NOT_FOUND");
        }
    }

    public List<AllStockDto> listByUpdate() {
        return stockRepository.findUpdateAllOrder().stream().map(AllStockDto::new).toList();
    }

    public ResponseEntity<StockDto> atualizaStocks(StockDto stockDto) {
        Stock stock = stockRepository.findById(stockDto.getId()).orElseThrow(Error::new);
        if (stockDto.getBidMin() != null) {
            stock.setBidMin(stockDto.getBidMin());
        }
        if (stockDto.getBidMax() != null) {
            stock.setBidMax(stockDto.getBidMax());
        }
        if (stockDto.getAskMin() != null) {
            stock.setAskMin(stockDto.getAskMin());
        }
        if (stockDto.getAskMax() != null) {
            stock.setAskMax(stockDto.getAskMax());
        }
        stockRepository.save(stockDto.entity());
        return new ResponseEntity<>(stockDto, HttpStatus.OK);
    }

    public SseEmitter subscribe(HttpServletResponse response) {
        response.setHeader("Cache_control", "no-store");
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try {
            emitters.add(sseEmitter);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        sseEmitter.onCompletion(() -> this.emitters.remove(sseEmitter));

        return sseEmitter;
    }

    public void dispatchEventToClients() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(stockRepository.findUpdateAllOrder());
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }

}