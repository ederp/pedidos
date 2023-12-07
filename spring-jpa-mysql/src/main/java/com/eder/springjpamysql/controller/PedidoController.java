package com.eder.springjpamysql.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eder.springjpamysql.model.Pedido;
import com.eder.springjpamysql.model.PedidoRequestDto;
import com.eder.springjpamysql.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Pedido")
@RestController
@RequestMapping({"/pedidos"})
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	 @ApiOperation(value = "Mostra lista de todos os pedidos",
			 response = Pedido.class,
			 responseContainer = "List")
	 @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	 public List<Pedido> getPedidos(){
		 return pedidoService.findAll();
	 }
	 
	 @ApiOperation(value = "Mostra lista de pedidos por data",
			 response = Pedido.class,
			 responseContainer = "List")
	 @GetMapping(path="/data/{dataCadastro}", produces= MediaType.APPLICATION_JSON_VALUE)
	 public List<Pedido> getPedidosByDataCadastro(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dataCadastro){
		 return pedidoService.findByDataCadastro(dataCadastro);
	 }
	 
	 @ApiOperation(value = "Mostra o pedido referente ao número de controle passado por parâmetro",
			 response = Pedido.class)
	 @GetMapping(path="id/{numControlePedido}", produces= MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Pedido> getPedidosByNumControlePedido(@PathVariable Integer numControlePedido){
		 return pedidoService.findByNumeroPedido(numControlePedido);
	 }
	 
	 @ApiOperation(value = "Cria um ou mais pedidos",
			 responseContainer = "List")
	 @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			 produces= MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<Pedido>> create(@RequestBody List<PedidoRequestDto> pedidos){
		 URI location = URI.create("/pedidos");
		 return ResponseEntity.created(location).body(pedidoService.createPedidos(pedidos));
	 }
}
