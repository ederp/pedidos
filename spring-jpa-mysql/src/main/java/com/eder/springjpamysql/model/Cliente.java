package com.eder.springjpamysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCliente;
	private String nomeCliente;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer codCliente, String nomeCliente) {
		super();
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
}
