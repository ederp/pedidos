package com.eder.springjpamysql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.eder.springjpamysql.model.Pedido;
import com.eder.springjpamysql.model.PedidoRequestDto;
import com.eder.springjpamysql.service.PedidoMapper;


@SpringBootTest
@AutoConfigureMockMvc
class SpringJpaMysqlApplicationTests {
	
	private Pedido pedido;
	private PedidoRequestDto pedidoRequestDto;
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	//testes de algumas regras de negócio
	@Test
	public void testCalculoValorTotal(){
		pedido = new Pedido(235, "15/11/2023", "Capa para IPhone 13", "R$ 20,00", 3, 5);
		assertEquals(pedido.getValorTotal(), "R$ 60,00");
	}
	
	@Test
	public void testDescontoCincoPorCento() {
		pedido = new Pedido(235, "15/11/2023", "Capa para IPhone 13", "R$ 20,00", 6, 5);
		assertEquals(pedido.getValorTotal(), "R$ 114,00");
	}
	
	@Test
	public void testDescontoDezPorCento() {
		pedido = new Pedido(235, "15/11/2023", "Capa para IPhone 13", "R$ 20,00", 11, 5);
		assertEquals(pedido.getValorTotal(), "R$ 198,00");
	}
	
	@Test
	public void testMapper() {
		pedidoRequestDto = new PedidoRequestDto(435, "16/11/2023", "Controle para Xbox One", "R$ 400,00", 1, 6);
		pedido = PedidoMapper.INSTANCE.toPedido(pedidoRequestDto);
		assertEquals(pedido.getNumControlePedido(), 435);
		assertEquals(pedido.getDataCadastro(), "16/11/2023");
		assertEquals(pedido.getNomeProduto(), "Controle para Xbox One");
		assertEquals(pedido.getValorUnitario(), "R$ 400,00");
		assertEquals(pedido.getValorTotal(), "R$ 400,00");
		assertEquals(pedido.getQtdeProduto(), 1);
		assertEquals(pedido.getCodCliente(), 6);
	}
	
	//testes de funcionamento dos endpoints
	@Test
	public void testGetPedidos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
					.get("/pedidos")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].numControlePedido").isNotEmpty());
	}
	
	@Test
	public void testGetPedidoByNumControlePedido() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
					.get("/pedidos/id/{numControlePedido}", 2503)
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numControlePedido").value(2503));
	}
	
	@Test
	public void testGetPedidoByDataCadastro() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
					.get("/pedidos/data/{dataCadastro}", "2023-11-17")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].dataCadastro").isNotEmpty());
	}
	
	@Test
	public void testPostJson() throws Exception {
		List<PedidoRequestDto> pedidos = new ArrayList<>();
		pedidoRequestDto = new PedidoRequestDto(435, "16/11/2023", "Controle para Xbox One", "R$ 400,00", 1, 6);
		pedidos.add(pedidoRequestDto);
		mockMvc.perform(MockMvcRequestBuilders
					.post("/pedidos")
					.contentType("application/json")
					.content(asJsonString(pedidos.get(0))))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void testPostXml() throws Exception {
		List<PedidoRequestDto> pedidos = new ArrayList<>();
		pedidoRequestDto = new PedidoRequestDto(999, "17/11/2023", "Refrigerante Coca-Cola 350 ml", "R$ 4,00", 5, 6);
		pedidos.add(pedidoRequestDto);
		mockMvc.perform(MockMvcRequestBuilders
					.post("/pedidos")
					.content(asXmlString(pedidos.get(0)))
					.contentType("application/xml"))
		        .andExpect(status().isCreated());
	}
	
	
	private String asJsonString(PedidoRequestDto pedido) {
		return "["+pedido.toString()+"]";
	}
	
	private String asXmlString(PedidoRequestDto pedido) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<root>\n"
				+ "   <element>\n"
				+ "      <codCliente>"+pedido.getCodCliente()+"</codCliente>\n"
				+ "      <nomeProduto>"+pedido.getNomeProduto()+"</nomeProduto>\n"
				+ "      <numControlePedido>"+pedido.getNumControlePedido()+"</numControlePedido>\n"
				+ "      <valorUnitario>"+pedido.getValorUnitario()+"</valorUnitario>\n"
				+ "      <qtdeProduto>"+pedido.getQtdeProduto()+"</qtdeProduto>\n"
				+ "   </element>\n"
				+ "</root>";
	}
	
}
