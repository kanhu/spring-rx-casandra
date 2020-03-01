package com.example.springrx.fluxMono;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ColdAndHotPublisherTest {
	
	@Test
	/**
	 * Cold publisher, it emits all the elements from the beginning when ever a new subscriber added
	 * @throws InterruptedException
	 */
	public void coldPublisherTest() throws InterruptedException {
		Flux<String> stringFlux=Flux.just("A","B","C","D","E")
				.delayElements(Duration.ofSeconds(1)).log();
		
		stringFlux.subscribe(System.out::println);
		Thread.sleep(2000);
		
		// second subscriber
		stringFlux.subscribe(System.out::println);
		
	}
	/**
	 * This is hot subscriber which will not emit the element from beginning
	 * @throws InterruptedException
	 */
	@Test
	public void hotPublisher() throws InterruptedException {
		Flux<String> stringFlux=Flux.just("A","B","C","D","E")
				.delayElements(Duration.ofSeconds(1)).log();
		
		ConnectableFlux<String> connactableFlux= stringFlux.publish();
		connactableFlux.connect();
		connactableFlux.subscribe(s-> System.out.println("Subcriber 1 " + s));
		Thread.sleep(2000);
		connactableFlux.subscribe(s-> System.out.println("Subcriber 2 " + s));// does not emit the value from beginning
		Thread.sleep(3000);
		
		
	}
	

}
