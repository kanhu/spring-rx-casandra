package com.example.springrx.fluxMono;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressureTest {
	
	public void backPressureTest() {
		Flux<Integer> finitieFlux=Flux.range(1, 10).log();
		
		StepVerifier.create(finitieFlux).expectSubscription()
		.thenRequest(1)
		.expectNext(1)
		.thenRequest(1)
		.expectNext(2)
		.thenCancel();
	}

}
