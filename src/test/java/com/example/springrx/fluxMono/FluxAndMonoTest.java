package com.example.springrx.fluxMono;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {
	@Test
	public void fluxTest() {
		Flux<String> strings = Flux.just("Spring", "Spring boot", "Spring mvc").log();
		strings.subscribe(System.out::println, e -> e.printStackTrace());
	}

	@Test
	public void testFluxElements() {
		Flux<String> strings = Flux.just("Spring2", "Spring boot2", "Spring mvc2").log();
		StepVerifier.create(strings).expectNext("Spring2").expectNext("Spring boot2").expectNext("Spring mvc2")
		.verifyComplete();
				
	}
	
	@Test
	public void testMono() {
		Mono<String> mono= Mono.just("Krushna").log();
		StepVerifier.create(mono).expectNext("Krushna").verifyComplete();
	}

}
