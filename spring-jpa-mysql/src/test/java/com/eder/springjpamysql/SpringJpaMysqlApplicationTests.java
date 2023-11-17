package com.eder.springjpamysql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eder.springjpamysql.model.Pedido;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class SpringJpaMysqlApplicationTests {
	
	Pedido pedido;

	@Test
	void contextLoads() {
	}

	@Test
	public void testConversionOfObject() throws JsonProcessingException {
		BigDecimal valor = new BigDecimal(18.15);
		List<Pedido> pedidos = new ArrayList<>();
		pedido = new Pedido(235, LocalDate.of(2023, 10, 23), "Capa para IPhone 13", valor, 3, 5);
		pedidos.add(pedido);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(pedidos);
        System.out.println(json);
	
	}
	
	/*
	@Test
	public final void givenJsonWithValidList_whenDeserializing_thenCorrect() throws JsonParseException, IOException {
	    String json = "{\n"
	    		+ "    \"pedidos\": [{\n"
	    		+ "        \"numControlePedido\": 2503, \n"
	    		+ "        \"nomeProduto\": \"Playstation 5\",\n"
	    		+ "        \"valorUnitario\": 3500.00,\n"
	    		+ "        \"codCliente\": 1\n"
	    		+ "    }]\n"
	    		+ "}";
	    List<ObjectMapper> mapper = new ArrayList<ObjectMapper>();

	    List<Pedido> pedidos =  new ArrayList<>();
	    mapper.forEach(m -> {
			try {
				m.reader().forType(Pedido.class).readValue(json);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}); 

	    pedidos.forEach(pedido -> {
	    	assertEquals(2503, pedido.getnumControlePedido());
	    	//assertEquals();
		    assertEquals("Playstation 5", pedido.getNomeProduto());
	    	assertEquals("3500.00", pedido.getValorUnitario().toString());
	    	assertEquals("3500.00", pedido.getValorTotal().toString());
		    assertEquals(5, pedido.getCodCliente());
	    });
	    
	} */
}
