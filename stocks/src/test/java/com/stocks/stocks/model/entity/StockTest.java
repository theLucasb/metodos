// package com.stocks.stocks.model.entity;

// import static org.mockito.Mockito.when;

// import com.nimbusds.common.contenttype.ContentType;
// import com.stocks.stocks.controller.StockController;
// import com.stocks.stocks.dto.StockDto;
// import com.stocks.stocks.services.StockService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;

// @WebMvcTest
// public class StockTest {

// @Autowired
// StockController stockController;

// @MockBean
// private StockService stockService;

// @BeforeEach
// public void setup() {
// standaloneSetup(this.stockController);
// }

// private void standaloneSetup(StockController stockController2) {
// }

// public void deveRetornarSucesso_QuandoBuscarStock() throws Exception {
// when(this.stockService.stockUnico(1L))
// .thenReturn(new StockDto(1L, "BEEF", "MINERVA", null, null, null, null));

// // given()
// // .accept(ContentType)
// // .when()
// // .get("/{id}", 1L)
// // .then()
// // .statusCode(HttpStatus.OK.value());

// }

// // private Object given() {
// // return null;
// // }
// }