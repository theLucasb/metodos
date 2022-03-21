/*
 * package com.user.user.controller;
 * 
 * import java.sql.SQLException;
 * import java.util.List;
 * 
 * import com.user.user.Dto.OrdersDto;
 * import com.user.user.Model.Orders;
 * import com.user.user.Repository.CompraRepository;
 * import com.user.user.Repository.VendaRepository;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * @RestController
 * public class CompraController {
 * 
 * @Autowired
 * private VendaRepository vendaRepository;
 * 
 * @Autowired
 * private CompraRepository compraRepository;
 * 
 * @PostMapping("/venda")
 * public Orders vender(@RequestBody OrdersDto ordersDto) {
 * 
 * if(ordersDto.getType() == 0){
 * compraRepository.insert(ordersDto.getId_user(), ordersDto.getId_stock(),
 * ordersDto.getVolume(),
 * ordersDto.getPrice());
 * List<Orders> orders =
 * vendaRepository.findByTypeStock2(ordersDto.getId_stock());
 * //List<Orders> teste1 = vendaRepository.testando1();
 * List<Orders> userteste1= compraRepository.fyndteste();
 * //List<Orders> userteste1= compraRepository.fyndteste();
 * List<Orders> userFind = vendaRepository.findByCalculo2();
 * if (orders.isEmpty()){
 * System.out.println(ordersDto.getStatus());
 * if(!userteste1.isEmpty()) {
 * System.out.println("compra negativa");
 * for (Orders cont:userteste1) {
 * System.out.println(ordersDto.getId_order());
 * compraRepository.updateDollarBalanceNE(cont, cont.getId_user());
 * compraRepository.RemainingNE(cont);
 * compraRepository.atualizarBalanceNE(cont.getId_order(),cont.getId_user(),
 * cont.getId_stock());
 * }
 * vendaRepository.fecharStatusVenda();
 * }
 * if(!userFind.isEmpty() ){
 * System.out.println("venda positiva");
 * for (Orders cont: userFind) {
 * vendaRepository.updateDollarBalance(cont.getId_user());
 * vendaRepository.updateRemainingValue(cont.getId_stock(),cont);
 * vendaRepository.atualizarBalance(cont.getId_user(), cont.getId_stock());
 * }
 * }
 * if(!userteste.isEmpty()){
 * System.out.println("compra positiva");
 * for ( Orders cont: userteste ) {
 * compraRepository.atualizaDolarCompra(cont.getId_user(), cont);
 * compraRepository.atualizaVolumeCompra(cont, cont.getId_user());
 * compraRepository.atualizaBalanceCompra(cont.getId_order(), cont.getId_user(),
 * cont.getId_stock());
 * }
 * vendaRepository.fecharStatusCompra();
 * }
 * if (!teste1.isEmpty()){
 * System.out.println("venda negativa");
 * for (Orders cont: teste1) {
 * vendaRepository.RemainingNE(cont.getId_user(), cont.getId_stock());
 * vendaRepository.updateDollarBalanceNE(cont, cont.getId_user());
 * vendaRepository.updateRemainingValue2(cont);
 * }
 * }
 * vendaRepository.fecharStatusVenda();
 * }
 * }
 * return null;
 * }
 * }
 */