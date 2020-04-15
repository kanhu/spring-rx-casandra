package com.example.springrx.api;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1")
public class FluxAndMonoAPI {

	@GetMapping("/flux")
	public Flux<String> getFlux() {
		return Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1)).log();
	}
	
	@GetMapping(value="/fluxStream", produces= MediaType.APPLICATION_STREAM_JSON_VALUE )
	public Flux<String> getFluxStream() {
		return Flux.just("A", "B", "C")
				.delayElements(Duration.ofSeconds(1))
				.log();
	}
	

	@GetMapping(value="/fluxInfine", produces= MediaType.APPLICATION_STREAM_JSON_VALUE )
	public Flux<Long> getFluxInfineStream() {
		return Flux.interval(Duration.ofSeconds(1)).log();
	}
	
	@GetMapping("/getAuther")
	public Mono<String> getAuther(){
		return Mono.just("Krushna");
	}
	
	
	
	
	
}
