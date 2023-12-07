package com.eder.springjpamysql.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eder.springjpamysql.model.Pedido;
import com.eder.springjpamysql.model.PedidoRequestDto;

@Mapper
public interface PedidoMapper {
	PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
	Pedido toPedido(PedidoRequestDto prd);
}
