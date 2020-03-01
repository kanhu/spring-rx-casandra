package com.example.springrx.fluxMono;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoErrorTest {

	@Test
	public void fluxErrorHandilingFallBack() {
		Flux<String> flux1 = Flux.just("A", "B", "C", "D")
				.concatWith(Flux.error(new RuntimeException("Error occured")))
				.concatWith(Flux.just("F")).onErrorReturn("X");

		StepVerifier.create(flux1.log()).expectSubscription().expectNext("A", "B", "C", "D", "X")
				// .expectError(RuntimeException.class)
				.verifyComplete();

	}

	@Test
	public void fluxErrorHandilingResume() {
		Flux<String> flux1 = Flux.just("A", "B", "C", "D")
				.concatWith(Flux.error(new RuntimeException("Error occured")))
				.concatWith(Flux.just("F")).onErrorResume((e) -> {
					e.printStackTrace();
					return Flux.just("X");
				}

				);

		StepVerifier.create(flux1.log()).expectSubscription().expectNext("A", "B", "C", "D", "X")
				// .expectError(RuntimeException.class)
				.verifyComplete();

	}

	@Test
	public void fluxErrorHandiling() {
		Flux<String> flux1 = Flux.just("A", "B", "C", "D")
				.concatWith(Flux.error(new RuntimeException("Error occured")))
				.concatWith(Flux.just("F"));

		StepVerifier.create(flux1.log()).expectSubscription().expectNext("A", "B", "C", "D")
				.expectError(RuntimeException.class).verify();

	}
}
