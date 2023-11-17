package com.eder.springjpamysql.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
public class Pedido {
	
	@Id
	private Integer numControlePedido;
	private LocalDate dataCadastro;
	private String nomeProduto;
	private BigDecimal valorUnitario;
	private Integer qtdeProduto;
	private BigDecimal valorTotal = BigDecimal.ONE;
	@JoinColumn
	private Integer codCliente;
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public Pedido(Integer numControlePedido, LocalDate dataCadastro, String nomeProduto,
			BigDecimal valorUnitario, Integer qtdeProduto, Integer codCliente) {
		super();
		this.numControlePedido = numControlePedido;
		this.dataCadastro = dataCadastro;
		this.nomeProduto = nomeProduto;
		this.valorUnitario = valorUnitario;
		this.valorUnitario = this.valorUnitario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.qtdeProduto = qtdeProduto;
		this.setValorTotal(this.valorUnitario, this.qtdeProduto);
		this.codCliente = codCliente;
	}
	
	public Integer getnumControlePedido() {
		return numControlePedido;
	}

	public void setnumControlePedido(Integer numControlePedido) {
		this.numControlePedido = numControlePedido;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQtdeProduto() {
		return qtdeProduto;
	}

	public void setQtdeProduto(Integer qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
		this.setValorTotal(this.valorUnitario, this.qtdeProduto);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	//setado como private pois deve ser um valor calculado somente pela classe Pedido
	private void setValorTotal(BigDecimal valorUnitario, Integer qtdeProduto) {
		this.valorTotal = valorUnitario.multiply(BigDecimal.valueOf(qtdeProduto));
		if (qtdeProduto >= 5 && qtdeProduto < 9) {
			this.valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95));
		} else if (qtdeProduto >= 10) {
			this.valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.9));
		}
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	
}
