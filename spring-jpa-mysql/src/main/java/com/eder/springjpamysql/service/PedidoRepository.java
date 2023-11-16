package com.eder.springjpamysql.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eder.springjpamysql.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query("select p from Pedido p where p.dataCadastro = ?1")
	public List<Pedido> findByDataCadastro(LocalDate dataCadastro);
	
}
