package com.example.springrx.api;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
// similar to @WebMvcTest, we need to use @WebFluxTest and specify the controller class
@WebFluxTest(controllers= {FluxAndMonoAPI.class})
public class FluxControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void fluxTest() {
	Flux<String> stringFlux=	webTestClient.get().uri("/v1/flux")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(String.class)
		.getResponseBody();
	
		StepVerifier.create(stringFlux.log()).expectSubscription()
		.expectNext("ABC")
		.verifyComplete();
		
	}
	
	@Test
	public void fluxStremTest() {
	
		Flux<Long> longFlux=	webTestClient.get().uri("/v1/fluxInfine")
				.accept(MediaType.APPLICATION_STREAM_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Long.class)
				.getResponseBody();
		
		StepVerifier.create(longFlux.log()).expectSubscription()
		.expectNext(0L)
		.expectNext(1L)
		.thenCancel()
		.verify();
		
	
	}
	
	@Test
	public void getAutherTest() {
		String expctedValue="Krushna";
		webTestClient.get().uri("/v1/getAuther")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBody(String.class)
		.consumeWith((response) ->  assertEquals(expctedValue, response.getResponseBody()));
	}
	
}
