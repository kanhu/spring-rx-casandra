package com.example.springrx.fluxMono;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

public class VirtualTImeJunitTest {
	
	@Test
	public void virutalTImeTest() {
		VirtualTimeScheduler.getOrSet();
		
		Flux<Long> longFlux=Flux.interval(Duration.ofSeconds(1))
				.take(3);
		
		/*StepVerifier.create(longFlux)
		.expectSubscription()
		.expectNext(0L,1L,2L)
		.verifyComplete(); */
		
		// with virtual time
		StepVerifier.withVirtualTime(() -> longFlux.log())
		.expectSubscription()
		.thenAwait(Duration.ofSeconds(3))
		.expectNext(0L,1L,2L)
		.verifyComplete();
		
		
	}

}
