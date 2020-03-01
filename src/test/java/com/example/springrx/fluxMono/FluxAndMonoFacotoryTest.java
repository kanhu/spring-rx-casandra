package com.example.springrx.fluxMono;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoFacotoryTest {
	List<String> values= Arrays.asList("S1","S2","S3");
	
	@Test
	public void testIterable() {
		Flux<String> names= Flux.fromIterable(values).log();
		StepVerifier.create(names).expectNext("S1","S2","S3")
		.verifyComplete();
	}
}
