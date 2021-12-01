package com.bootcamp.micrometer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.micrometer.service.TempManagementI;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class TestController {
	
	@Autowired
	TempManagementI tempManagement;
	
	private final static Logger Logger = LoggerFactory.getLogger(TestController.class);
	
	private Counter celsiusFahre;
	private Counter fahreCelsius;
	
	public TestController (MeterRegistry registry) {
		this.celsiusFahre = Counter.builder("invocaciones.CelsiusAFahrenheit").description("Invocaciones totales").register(registry);
		this.fahreCelsius = Counter.builder("invocaciones.FahrenheitACelsius").description("Invocaciones totales").register(registry);
	}
	
	@GetMapping(path="/celsius")
	public String celsius(@RequestParam("grados") float grados){
		// Recibe grados celsius 
		Logger.info("Llamada al endpoint celsius.");
		this.celsiusFahre.increment();
		return "Grados Fahrenheit: "+tempManagement.celsiusAFahrenheit(grados);
	}
	
	@GetMapping(path="/fahrenheit")
	public String fahrenheit(@RequestParam("grados") float grados){
		// Recibe grados fahrenheit 
		Logger.info("Llamada al endpoint fahrenheit.");
		this.fahreCelsius.increment();
		return "Grados Fahrenheit: "+tempManagement.fahrenheitACelsius(grados);
	}
	
	
	@GetMapping(path="/mostrarTemperatura")
	public String mostrarTemperatura() {
		return  "4ºC";
	}
	
	
	
	
	
}
