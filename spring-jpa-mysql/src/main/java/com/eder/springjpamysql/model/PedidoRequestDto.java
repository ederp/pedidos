package com.eder.springjpamysql.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
public class PedidoRequestDto {
	
	private Integer numControlePedido;
	private String dataCadastro;
	private String nomeProduto;
	private String valorUnitario;
	private Integer qtdeProduto;
	private Integer codCliente;

	public PedidoRequestDto() {
		// TODO Auto-generated constructor stub
	}

	
	public PedidoRequestDto(Integer numControlePedido, String dataCadastro, String nomeProduto, String valorUnitario,
			Integer qtdeProduto, Integer codCliente) {
		super();
		this.validaCamposOpcionais(dataCadastro, qtdeProduto);
		this.numControlePedido = numControlePedido;
		this.dataCadastro = dataCadastro;
		this.nomeProduto = nomeProduto;
		this.valorUnitario = valorUnitario;
		this.qtdeProduto = qtdeProduto;
		this.codCliente = codCliente;
	}

	 @ApiModelProperty(example = "111", required = true, value = "")
	 @NotNull
	public Integer getNumControlePedido() {
		return numControlePedido;
	}

	public void setNumControlePedido(Integer numControlePedido) {
		this.numControlePedido = numControlePedido;
	}

	@ApiModelProperty(example = "15/11/2023", value = "")
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@ApiModelProperty(example = "Paçoca Amor", required = true, value = "")
	@NotNull
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	@ApiModelProperty(example = "R$ 1,00", required = true, value = "")
	@NotNull
	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@ApiModelProperty(example = "3", value = "")
	public Integer getQtdeProduto() {
		return qtdeProduto;
	}

	public void setQtdeProduto(Integer qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
	}

	@ApiModelProperty(example = "10", required = true, value = "")
	@NotNull
	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	
	@Override
	public String toString() {
		return "{" +
					"\"numControlePedido\": "+this.getNumControlePedido()+", "+
					"\"dataCadastro\": \""+this.getDataCadastro()+"\", "+
					"\"nomeProduto\": \""+this.getNomeProduto()+"\", "+
					"\"valorUnitario\": \""+this.getValorUnitario()+"\", "+
					"\"qtdeProduto\": "+this.getQtdeProduto()+", "+
					"\"codCliente\": "+this.getCodCliente()+""+
				"}";
				
	}
	
	private void validaCamposOpcionais(String dataCadastro, Integer qtdeProduto) {
		Optional<String> optDataCadastro = Optional.ofNullable(dataCadastro);
		Optional<Integer> optQtdeProduto = Optional.ofNullable(qtdeProduto);
		
		if (optDataCadastro.isEmpty()) {
			dataCadastro = LocalDate.now().toString();
		} 
		if (optQtdeProduto.isEmpty()) {
			qtdeProduto = 1;
		}
	}

}
