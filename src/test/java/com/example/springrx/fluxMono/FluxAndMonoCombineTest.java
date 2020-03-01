package com.example.springrx.fluxMono;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoCombineTest {

	@Test
	public void combineUsingMerge() {
		Flux<String> flux1=Flux.just("A","B","C");
		Flux<String> flux2=Flux.just("D","E","F");
		Flux<String> mergeFlux=Flux.merge(flux1,flux2);
		StepVerifier.create(mergeFlux.log()).expectNextCount(6).verifyComplete();
	}
	
	@Test
	public void combineUsingMergeWithDealy() {
		Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
		Flux<String> mergeFlux=Flux.merge(flux1,flux2);
		StepVerifier.create(mergeFlux.log()).expectNextCount(6).verifyComplete();
	}
	
	@Test
	public void combineUsingConcatWithDealy() {
		Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
		Flux<String> mergeFlux=Flux.concat(flux1,flux2);
		StepVerifier.create(mergeFlux.log()).expectNextCount(6).verifyComplete();
	}
	
	@Test
	public void combineUsingZipWithDealy() {
		Flux<String> flux1=Flux.just("S1","S2","S3").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2=Flux.just("S4","S5","S6").delayElements(Duration.ofSeconds(1));
		Flux<String> mergeFlux=Flux.zip(flux1,flux2,(t1,t2) -> t1.concat(t2));
		
		StepVerifier.create(mergeFlux.log())
		.expectNext("S1S4","S2S5","S3S6")
		.verifyComplete();
	}
	
	
}
