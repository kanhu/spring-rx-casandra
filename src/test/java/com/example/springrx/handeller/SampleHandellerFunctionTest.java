package com.example.springrx.handeller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
//@WebFluxTest This will not work for the functional web

// we have to use the spring boot test
@SpringBootTest// this will load the full Spring context
@AutoConfigureWebTestClient
public class SampleHandellerFunctionTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void fluxTest() {
	Flux<Integer> stringFlux=	webTestClient.get().uri("/mono")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
	
		StepVerifier.create(stringFlux.log()).expectSubscription()
		.expectNext(1)
		.verifyComplete();
		
	}

}
