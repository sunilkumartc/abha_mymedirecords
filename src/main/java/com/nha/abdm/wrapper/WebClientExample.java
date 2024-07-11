package com.nha.abdm.wrapper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.netty.handler.timeout.ReadTimeoutException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.util.retry.Retry;

public class WebClientExample {
	
	 public static HttpHeaders setGatewayRequestHeaders() {
		 
		 String requestId = "b92c8c41-a915-46d5-9f9c-58dc4e5310e2";
	        String timestamp = "2024-07-08T07:43:04.394Z";
	        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJBbFJiNVdDbThUbTlFSl9JZk85ejA2ajlvQ3Y1MXBLS0ZrbkdiX1RCdkswIn0.eyJleHAiOjE3MjA0MjY0MzYsImlhdCI6MTcyMDQyNTIzNiwianRpIjoiNjg0MDc4ZmEtZjY2ZS00NzM5LWFhYzYtZTcwZTY1MDc3YTU1IiwiaXNzIjoiaHR0cHM6Ly9kZXYubmRobS5nb3YuaW4vYXV0aC9yZWFsbXMvY2VudHJhbC1yZWdpc3RyeSIsImF1ZCI6WyJhY2NvdW50IiwiU0JYVElEXzAwNjU3NiJdLCJzdWIiOiI3NmQ4MDVmZi02NjlkLTQwZDItYTRjZi0yMmYxNTI3MTA5Y2YiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJTQlhJRF8wMDc3MTEiLCJzZXNzaW9uX3N0YXRlIjoiZWUyOTFmYWItMWVjZS00OTM3LWE4NzMtMDQ3MmFhMzA0MjAxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjkwMDciXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImhpdSIsIm9mZmxpbmVfYWNjZXNzIiwiaGVhbHRoSWQiLCJwaHIiLCJPSURDIiwiaGVhbHRoX2xvY2tlciIsImhpcCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7IlNCWElEXzAwNzcxMSI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19LCJTQlhUSURfMDA2NTc2Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsImNsaWVudEhvc3QiOiIxMDAuNjUuMTYwLjIxNCIsImNsaWVudElkIjoiU0JYSURfMDA3NzExIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtc2J4aWRfMDA3NzExIiwiY2xpZW50QWRkcmVzcyI6IjEwMC42NS4xNjAuMjE0In0.FsfESpZTlI1uVx0u14ePurX8h0SRu3Sv7R0-chxH_inc7piAvpEpTx1rUdfK-mNm7UhLmXXq3QBhE3PVRlsvetSDr8x--kxoLRtW0Wr6dMIM6JakJXQwwrmsK2cgD0oZktu6Va8uwKRWViaKQ--SPWmBy10RapVNNIqt0Bmf3O2y7SNvS4ynEW55ZRUL1GjEDdenwe-AayL_Klsp1_N0qqEsXlTUVc2e6bJbH8l6oo-ZbHdiz_ZcvauMGgoA-MDUBsvMe4VfH98UzjlOdi5WNPqRigHTfy-66moVEtUGfYac-CDg7xLYdfqh33Mk776jffKQK9vg22feZsoq3o_C8w";
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.add("Authorization", "Bearer " + token);
		    headers.add("REQUEST-ID", requestId);
		    headers.add("TIMESTAMP",  timestamp);

		    return headers;
		  }
	 
    public static void main(String[] args) {
    	  ConnectionProvider connectionProvider = ConnectionProvider.builder("connectionProvider")
                   .maxIdleTime(Duration.ofSeconds(10))
                   .build();
               String ogn = "KbSc76bNQW10mkxb93T/1N0T7vG5E/TjRHDPkxbeuUSOADJMurEsq/tIBZN88txZEsPSO4dD/NwXzfFbHj2qZa9wQhIoUKxUzFf189f/60cvLJmU8NsUngUQpqRFdmRc8UhtGDOZWwtnS3n6aoTleE1O8vMz6Qcw+rVdAHcYsHZjVPH35/CujC3LY8LPJZ8KtCPOCp2r+otGaG1jYZLg1cXMbnzvSAHzDKJDbG64EUCZJzscjd3IDtwWgDA6e2xfEnoSAbBDqH9HVj/kgGd3TEVNXuaGAvzfb83ecQVdm7CsOK0hNpCzSdsbif5ItRqSuxX3NsJZDh+snQx9U8msR0DHNjUeMoXLRPE8V7wWjTU82h9YjPUfNWYll9zOSP3kZjHdytCsKUE+N8hbYRTif4LLWar6/LhAjxcjAHlRWPdmc2IXc92tq1cwQ+fDKXgLlPFRiuEalOm3dju0+ReI9+jUf+ssuzjxxJgwRmwuKG+B4Nz8WU5m/bMijm48Vmq8K04YDvlKNb11JrTl9uwMiQ3so7quLI6lM3viZy/os4klAWNKuqNDqcR4ADsHPoRN6tB+eBhHPTI0MhbwH9hGt43tnja9ndvDiHdaNABnHctPkAguNjy+y29dMm3U3INY182FbP57CkJSpsGDaS7NTE8wQ8kpZoyTPZC5AXx+r8A=";
               HttpClient httpClient = HttpClient.create(connectionProvider)
                   .wiretap("reactor.netty.http.client.HttpClient")
                   .responseTimeout(Duration.of(5, ChronoUnit.SECONDS));
               
               WebClient webClient =
                   WebClient.builder()
                           .baseUrl("https://abhasbx.abdm.gov.in")
                           .clientConnector(new ReactorClientHttpConnector(httpClient))
                           .defaultHeaders(httpHeaders -> httpHeaders.addAll(setGatewayRequestHeaders()))
                           .build();
               String requestBody = "{\n" +
                       "    \"txnId\": \"\",\n" +
                       "    \"scope\": [\n" +
                       "        \"abha-enrol\"\n" +
                       "    ],\n" +
                       "    \"loginHint\": \"aadhaar\",\n" +
                       "    \"loginId\": \"KbSc76bNQW10mkxb93T/1N0T7vG5E/TjRHDPkxbeuUSOADJMurEsq/tIBZN88txZEsPSO4dD/NwXzfFbHj2qZa9wQhIoUKxUzFf189f/60cvLJmU8NsUngUQpqRFdmRc8UhtGDOZWwtnS3n6aoTleE1O8vMz6Qcw+rVdAHcYsHZjVPH35/CujC3LY8LPJZ8KtCPOCp2r+otGaG1jYZLg1cXMbnzvSAHzDKJDbG64EUCZJzscjd3IDtwWgDA6e2xfEnoSAbBDqH9HVj/kgGd3TEVNXuaGAvzfb83ecQVdm7CsOK0hNpCzSdsbif5ItRqSuxX3NsJZDh+snQx9U8msR0DHNjUeMoXLRPE8V7wWjTU82h9YjPUfNWYll9zOSP3kZjHdytCsKUE+N8hbYRTif4LLWar6/LhAjxcjAHlRWPdmc2IXc92tq1cwQ+fDKXgLlPFRiuEalOm3dju0+ReI9+jUf+ssuzjxxJgwRmwuKG+B4Nz8WU5m/bMijm48Vmq8K04YDvlKNb11JrTl9uwMiQ3so7quLI6lM3viZy/os4klAWNKuqNDqcR4ADsHPoRN6tB+eBhHPTI0MhbwH9hGt43tnja9ndvDiHdaNABnHctPkAguNjy+y29dMm3U3INY182FbP57CkJSpsGDaS7NTE8wQ8kpZoyTPZC5AXx+r8A=\",\n" +
                       "    \"otpSystem\": \"aadhaar\"\n" +
                       "}";

        ResponseEntity<ObjectNode> t= webClient
        .post()
        .uri("/abha/api/v3/enrollment/request/otp")
        .body(BodyInserters.fromValue(requestBody))
        .retrieve()
        .toEntity(ObjectNode.class)
        .retryWhen(
            Retry.backoff(5, Duration.ofSeconds(2))
                .filter(
                    throwable ->
                        throwable instanceof HttpServerErrorException
                            || throwable instanceof WebClientRequestException
                            || throwable instanceof ReadTimeoutException
                            || throwable instanceof java.util.concurrent.TimeoutException))
        .block();
        
        System.out.println(t.toString());

    }
}

