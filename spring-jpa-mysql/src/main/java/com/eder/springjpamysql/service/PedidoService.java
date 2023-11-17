package com.eder.springjpamysql.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eder.springjpamysql.model.Pedido;

@Service
public class PedidoService {
	
	private PedidoRepository repository;
	
	public PedidoService(PedidoRepository repository) {
		super();
		this.repository = repository;
	}
	
	private Pedido create(Pedido pedido) {
		Optional<LocalDate> optDataCadastro = Optional.ofNullable(pedido.getDataCadastro());
		Optional<Integer> optQtdeProduto = Optional.ofNullable(pedido.getQtdeProduto());
		
		if (optDataCadastro.isEmpty()) {
			pedido.setDataCadastro(LocalDate.now());
		}
		if (optQtdeProduto.isEmpty()) {
			pedido.setQtdeProduto(1);
		}
		return repository.save(pedido);
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public List<Pedido> findByDataCadastro(LocalDate dataCadastro) {
		return repository.findByDataCadastro(dataCadastro);
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity findByNumeroPedido(Integer numControlePedido){
		return repository.findById(numControlePedido)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	public List<Pedido> createPedidos(List<Pedido> pedidos){
		List<Pedido> pedidosCriados = new ArrayList<Pedido>();
		pedidos.stream()
			.filter(pedido -> ! this.findByNumeroPedido(pedido.getnumControlePedido()).getStatusCode().equals(HttpStatus.OK))
			.limit(10)
			.forEach(pedido -> pedidosCriados.add(this.create(pedido)));
		return pedidosCriados;
	}
}
