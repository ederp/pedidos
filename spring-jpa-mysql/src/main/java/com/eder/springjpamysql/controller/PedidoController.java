package com.eder.springjpamysql.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eder.springjpamysql.model.Pedido;
import com.eder.springjpamysql.service.PedidoService;

@RestController
@RequestMapping({"/pedidos"})
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	 @GetMapping
	 public List<Pedido> getPedidos(){
		 return pedidoService.findAll();
	 }
	 
	 @GetMapping(path="/data/{dataCadastro}")
	 public List<Pedido> getPedidosByDataCadastro(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dataCadastro){
		 return pedidoService.findByDataCadastro(dataCadastro);
	 }
	 
	 @GetMapping(path="id/{numControlePedido}")
	 public ResponseEntity<Pedido> getPedidosByNumControlePedido(@PathVariable Integer numControlePedido){
		 return pedidoService.findByNumeroPedido(numControlePedido);
	 }
	 
	 @PostMapping
	 public ResponseEntity<List<Pedido>> create(@RequestBody List<Pedido> pedidos){
		 URI location = URI.create("/pedidos");
		 return ResponseEntity.created(location).body(pedidoService.createPedidos(pedidos));
	 }
}
