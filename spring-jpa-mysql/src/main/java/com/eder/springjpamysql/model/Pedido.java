package com.eder.springjpamysql.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
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
	@Transient
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Transient
	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "BR"));
	@Transient
	private NumberFormat formatadorMoeda = new DecimalFormat("¤ #.00", dfs);
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public Pedido(Integer numControlePedido, String dataCadastro, String nomeProduto,
			String valorUnitario, Integer qtdeProduto, Integer codCliente) {
		super();
		this.numControlePedido = numControlePedido;
		this.dataCadastro = this.formataDataEntrada(dataCadastro);
		this.nomeProduto = nomeProduto;
		this.valorUnitario = this.formataMoedaEntrada(valorUnitario);
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

	public String getDataCadastro() {
		return this.formataDataSaida(dataCadastro);
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = this.formataDataEntrada(dataCadastro);
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

	public String getValorUnitario() {
		return this.formataMoedaSaida(valorUnitario);
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = this.formataMoedaEntrada(valorUnitario);
	}

	public Integer getQtdeProduto() {
		return qtdeProduto;
	}

	public void setQtdeProduto(Integer qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
		this.setValorTotal(this.valorUnitario, this.qtdeProduto);
	}

	public String getValorTotal() {
		return this.formataMoedaSaida(valorTotal);
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
	
	private LocalDate formataDataEntrada(String dataEntrada) {
		return LocalDate.parse(dataEntrada, this.formatadorData);
	}
	
	private String formataDataSaida(LocalDate dataSaida) {
		try {
			return this.formatadorData.format(dataSaida);
		} catch (Exception e) {
			return null;
		}
	}
	
	private BigDecimal formataMoedaEntrada(String moedaEntrada){
		String valor = null;
		try {
			valor = formatadorMoeda.parse(moedaEntrada).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BigDecimal(valor);
	}
	
	private String formataMoedaSaida(BigDecimal moedaSaida) {
		return this.formatadorMoeda.format(moedaSaida);
	}

	@Override
	public String toString() {
		return "{" +
					"\"numControlePedido\": "+this.getnumControlePedido()+", "+
					"\"dataCadastro\": \""+this.getDataCadastro()+"\", "+
					"\"nomeProduto\": \""+this.getNomeProduto()+"\", "+
					"\"valorUnitario\": \""+this.getValorUnitario()+"\", "+
					"\"qtdeProduto\": "+this.getQtdeProduto()+", "+
					"\"codCliente\": "+this.getCodCliente()+""+
				"}";
				
	}
}
