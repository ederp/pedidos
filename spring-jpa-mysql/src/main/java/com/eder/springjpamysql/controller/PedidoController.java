package com.eder.springjpamysql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
	 @RequestMapping(value = "/teste", method = RequestMethod.GET)
	    public String get() {
	        return "Hello World!";
	    }
}
