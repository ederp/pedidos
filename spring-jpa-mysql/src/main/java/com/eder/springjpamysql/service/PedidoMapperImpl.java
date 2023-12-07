package com.eder.springjpamysql.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.eder.springjpamysql.model.Pedido;
import com.eder.springjpamysql.model.PedidoRequestDto;

public class PedidoMapperImpl implements PedidoMapper{
	
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public PedidoMapperImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pedido toPedido(PedidoRequestDto prd) {
		// TODO Auto-generated method stub
		this.validaCamposOpcionais(prd);
		
		return new Pedido(prd.getNumControlePedido(), prd.getDataCadastro(), 
				prd.getNomeProduto(), prd.getValorUnitario(), prd.getQtdeProduto(), prd.getCodCliente());
	}
	
	private void validaCamposOpcionais(PedidoRequestDto prd) {
		Optional<String> optDataCadastro = Optional.ofNullable(prd.getDataCadastro());
		Optional<Integer> optQtdeProduto = Optional.ofNullable(prd.getQtdeProduto());
		
		if (optDataCadastro.isEmpty()) {
			prd.setDataCadastro(LocalDate.now().format(formatadorData).toString());
		} 
		if (optQtdeProduto.isEmpty()) {
			prd.setQtdeProduto(1);
		}
	}

}
