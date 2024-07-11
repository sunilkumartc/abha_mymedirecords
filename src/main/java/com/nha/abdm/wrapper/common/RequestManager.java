/* (C) 2024 */
package com.nha.abdm.wrapper.common;

import com.nha.abdm.wrapper.common.requests.SessionManager;
import com.nha.abdm.wrapper.common.responses.GenericResponse;
import io.netty.handler.timeout.ReadTimeoutException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.util.retry.Retry;

@Component
public class RequestManager {
  private static final Logger log = LogManager.getLogger(RequestManager.class);

  @Value("${connectionTimeout}")
  private int connectionTimeout;

  @Value("${responseTimeout}")
  private int responseTimeout;

  private final WebClient webClient;
  private final SessionManager sessionManager;

  @Autowired
  public RequestManager(
      @Value("${gatewayBaseUrl}") final String gatewayBaseUrl,
      @Value("${sandboxBaseUrl1}") final String sandboxBaseUrl1,
      @Value("${useProxySettings}") final boolean useProxySettings,
      SessionManager sessionManager) {
    this.sessionManager = sessionManager;
    /**webClient =
        WebClient.builder()
            .baseUrl(sandboxBaseUrl1)
            .clientConnector(
                new ReactorClientHttpConnector(sessionManager.getHttpClient(useProxySettings)))
            .defaultHeaders(
                httpHeaders -> httpHeaders.addAll(sessionManager.setGatewayRequestHeaders()))
            .build();**/
    
     webClient =
            WebClient.builder()
                .baseUrl("https://healthidsbx.abdm.gov.in")
                .defaultHeader("accept", "*/*")
                .defaultHeader("Accept-Language", "en-US")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization",sessionManager.fetchAccessToken())
                .build();
  }

  // Initializing headers every time to avoid setting the old headers/session token and getting
  // unauthorised error from gateway.
  @Retryable(
      value = {WebClientRequestException.class, ReadTimeoutException.class, TimeoutException.class},
      maxAttempts = 5,
      backoff = @Backoff(delay = 1000, multiplier = 2))
  public <T> ResponseEntity<GenericResponse> fetchResponseFromGateway(String uri, T request) {
   
	  
	  return webClient
        .post()
        .uri(uri)
        .headers(httpHeaders -> httpHeaders.addAll(sessionManager.setGatewayRequestHeaders()))
        .body(BodyInserters.fromValue(request))
       // .bodyValue(request)
        .retrieve()
        .toEntity(GenericResponse.class)
        .retryWhen(
            Retry.backoff(5, Duration.ofSeconds(2))
                .filter(
                    throwable ->
                        throwable instanceof HttpServerErrorException
                            || throwable instanceof WebClientRequestException
                            || throwable instanceof ReadTimeoutException
                            || throwable instanceof TimeoutException))
        .block();
  }
  
  @Retryable(
	      value = {WebClientRequestException.class, ReadTimeoutException.class, TimeoutException.class},
	      maxAttempts = 5,
	      backoff = @Backoff(delay = 1000, multiplier = 2))
	  public <T> ResponseEntity<String> fetchResponseFromGateway1(String uri, T request) {
	   
	  WebClient webClient =
		        WebClient.builder()
		            .baseUrl("https://healthidsbx.abdm.gov.in"+uri)
		           // .defaultHeader("accept", "*/*")
		            //.defaultHeader("Accept-Language", "en-US")
		            .defaultHeader("Content-Type", "application/json")
		            .defaultHeader("Authorization", "Bearer " + sessionManager.fetchAccessToken())
		            .build();
	  return webClient
	        .post()
	        //.uri(uri)
	       // .headers(httpHeaders -> httpHeaders.addAll(sessionManager.setGatewayRequestHeaders()))
	        //.body(BodyInserters.fromValue(request))
	        .bodyValue(request)
	        .retrieve()
	        .toEntity(String.class)
	        .retryWhen(
	            Retry.backoff(5, Duration.ofSeconds(2))
	                .filter(
	                    throwable ->
	                        throwable instanceof HttpServerErrorException
	                            || throwable instanceof WebClientRequestException
	                            || throwable instanceof ReadTimeoutException
	                            || throwable instanceof TimeoutException))
	        .block();
	  }
}