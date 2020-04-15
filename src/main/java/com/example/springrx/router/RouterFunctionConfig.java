package com.example.springrx.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.springrx.handeller.MyHandellerFunction;

@Configuration
public class RouterFunctionConfig {

	@Bean
	public RouterFunction<ServerResponse> router(MyHandellerFunction handellerFunction) {
		return RouterFunctions
				.route(RequestPredicates.GET("/functionalWeb")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handellerFunction::functionalWeb)
				.andRoute(
						RequestPredicates.GET("/mono")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						handellerFunction::mono);
	}
}
