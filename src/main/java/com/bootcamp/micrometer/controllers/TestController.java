package com.bootcamp.micrometer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class TestController {
	
	private final static Logger Logger = LoggerFactory.getLogger(TestController.class);
	
	private Counter celsiusFahre;
	private Counter fahreCelsius;
	
	public TestController (MeterRegistry registry) {
		this.celsiusFahre = Counter.builder("invocaciones.CelsiusAFahrenheit").description("Invocaciones totales").register(registry);
		this.fahreCelsius = Counter.builder("invocaciones.FahrenheitACelsius").description("Invocaciones totales").register(registry);
	}
	
	@GetMapping(path="/celsius/{id}")
	public String celsius(@PathVariable("id") int grados){
		// Recibe grados celsius 
		Logger.info("Llamada al endpoint celsius.");
		this.celsiusFahre.increment();
		return "Grados Fahrenheit: "+((grados * 1.8f) + 32);
	}
	
	@GetMapping(path="/fahrenheit/{id}")
	public String fahrenheit(@PathVariable("id") int grados){
		// Recibe grados fahrenheit 
		Logger.info("Llamada al endpoint fahrenheit.");
		this.fahreCelsius.increment();
		return "Grados Fahrenheit: "+((grados - 32) / 1.8f);
	}
	
	
	@GetMapping(path="/mostrarTemperatura")
	public String mostrarTemperatura() {
		return  "4ºC";
	}
	
	
	
	
	
}
