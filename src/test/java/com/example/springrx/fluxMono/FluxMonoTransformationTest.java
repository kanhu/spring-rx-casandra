package com.example.springrx.fluxMono;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;




public class FluxMonoTransformationTest {

	@Test
	public void transfromUsingFlatMap() {
		Flux<String> values =Flux.fromIterable(Arrays.asList("S1","S2","S3","s123")).log()
				.map(s -> s.toLowerCase())
				.filter(s -> s.length() <=2)
				.flatMap(s -> Flux.fromIterable(getString(s)));
				
		
		StepVerifier.create(values).expectNext("s1","s1","s1","s2","s2","s2","s3","s3","s3")
		.verifyComplete();
	}
	
	
	@Test
	public void transfromUsingFlatMapUsingParallel() {
		Flux<String> values =Flux.fromIterable(Arrays.asList("s1","s2","s3","s4")).log()
				.window(2) // Flux<Flux<String> ("S1","S2")  ("s3","s4")
				.flatMap(s -> s.map(this::getString).subscribeOn(Schedulers.parallel()))// Flux<List<String>
				.flatMap(s -> Flux.fromIterable(s));
		
				
		
		StepVerifier.create(values).expectNextCount(12).verifyComplete();
		
	}
	
	
	
	
	public List<String> getString(String s){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// sleep for 1 sec
		return Arrays.asList(s,s,s);
	}

}
