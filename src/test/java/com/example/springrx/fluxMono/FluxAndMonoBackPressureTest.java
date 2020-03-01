package com.example.springrx.fluxMono;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressureTest {

	@Test
	public void backPressureTest() {
		Flux<Integer> finitieFlux = Flux.range(1, 10).log();

		StepVerifier.create(finitieFlux).expectSubscription().thenRequest(1).expectNext(1).thenRequest(1).expectNext(2)
				.thenCancel().verify();

	}

	@Test
	public void backPressure() {
		Flux<Integer> finitieFlux = Flux.range(1, 10).log();
		finitieFlux.subscribe(element -> System.out.println(element) // subscriber
				, (e) -> System.err.println("Error happen") // error handaller
				, () -> System.out.println("Done") // on complete
				, (subcribe) -> subcribe.request(2) // then request
		);

	}

	@Test
	/**
	 * Back pressure with customized hookOnNext
	 */
	public void customizeBackPressure() {
		Flux<Integer> finitieFlux = Flux.range(1, 10).log();
		finitieFlux.subscribe(new BaseSubscriber<Integer>() {
			protected void hookOnNext(Integer value) {
				request(1);
				System.out.println("Value received as " + value);
				if (value == 4)
					cancel();

			};
		}

		);

	}

}
