package com.nha.abdm.wrapper;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class Test1 {

    public static void main(String[] args) {
        String url = "https://healthidsbx.abdm.gov.in/api/v2/registration/aadhaar/generateOtp";
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJBbFJiNVdDbThUbTlFSl9JZk85ejA2ajlvQ3Y1MXBLS0ZrbkdiX1RCdkswIn0.eyJleHAiOjE3MjA0NTQyOTUsImlhdCI6MTcyMDQ1MzA5NSwianRpIjoiYzQ0NmI4MDktNWZmNi00M2Q3LWFkMjMtN2UwNjIxNDlkZDk4IiwiaXNzIjoiaHR0cHM6Ly9kZXYubmRobS5nb3YuaW4vYXV0aC9yZWFsbXMvY2VudHJhbC1yZWdpc3RyeSIsImF1ZCI6WyJhY2NvdW50IiwiU0JYVElEXzAwNjU3NiJdLCJzdWIiOiI3NmQ4MDVmZi02NjlkLTQwZDItYTRjZi0yMmYxNTI3MTA5Y2YiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJTQlhJRF8wMDc3MTEiLCJzZXNzaW9uX3N0YXRlIjoiYTg2M2ViZjktMTQ0Ni00OWM2LTk2MDgtYTA2NDY3YmFkZDhlIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjkwMDciXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImhpdSIsIm9mZmxpbmVfYWNjZXNzIiwiaGVhbHRoSWQiLCJwaHIiLCJPSURDIiwiaGVhbHRoX2xvY2tlciIsImhpcCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7IlNCWElEXzAwNzcxMSI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19LCJTQlhUSURfMDA2NTc2Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsImNsaWVudEhvc3QiOiIxMDAuNjUuMTYwLjIxNCIsImNsaWVudElkIjoiU0JYSURfMDA3NzExIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtc2J4aWRfMDA3NzExIiwiY2xpZW50QWRkcmVzcyI6IjEwMC42NS4xNjAuMjE0In0.PeKU5-gac3iZZDcmlYH-3IInPgyQ0vyL2esLigNuEvzLASvD9YWEz6dOZzkRFGGx0FAZ7ceE2SowBCgcBE0LsQ7ckcCCHVHOaEknw35SNGlFspG4h6W_BWmlmf-5Z7Zty_s9ZRc6_W8Sfgc0dK757l1fhoLCy0uMFkhwqGlpD8Qjio7qS9FwR_jCcjKZmgtFCJWuaaqYn-yS5Udli4xZSn7a-rakKkDVclLypDE8Fw5eSQA_sS9bvuwJJj_0cVAyWy4jHe5UW1z1Eraegnd1fDEo5bEL0ujy__j4bkKZEuWtUJSSztfoChrQ2OgXkUACJftnC-_qd5yDTz80Zjj5zg";
        String timestamp = java.time.Instant.now().toString();
        String requestId = "ba150a31-9fda-43c4-a0ab-486f5c0860d9";
        String encryptedAadhaarNumber = "fyY0OpVEPCPUxeIA2hjcYfn4KhvA8xwaXL9B5gjCZ0iKA8h9OIVIu1/j0um3Hlq2hKr+fOOp5oXIUiZ7k+dMRxGXyW7V3mbYB87VelXaHmWKQ8jbkQIMpLdMRq6m2xWElMj/+ozFb/OTwBKHlAAwyLttbF3P4LB+kR8v3BsKYOz+SkZg9YDilPgNX5Q/ZBmOQxpMTc9gY2xMGNEuCLSs/nbh5bTBxtbEJbMy1PV5Gf24OXZsUTTp65FzCBKj0aUiRDpgNxg0wfLXMWCuKT82MAuXl+XUNvIBbx5cd7rOQS1QxvPIAzZI751SBZ4spKtt/eDb4JxwDYxQwjoJJxu8ABZ+0Etjkn6h80SiTnYwt57LM2sc/dONWDN1/ujffGLpE9cGf8DskSD4dAYNNiqJJcKqT2h/qz2FAyifxgw90svUbuGOEXgTYWRHZx+IbTKf9mZ946F2m9G0YL3B7Rds9QnyK4lQmvQHPn4rm/SuQWkh9/Wzv3mk+zfnwvR8Yomztv8N/XAoXs7QjiA6KasYXnGjY4oAqP1mcuuB/XOxr9KNZgzznSF8d3XsfpIp2K4QIPmOO0uLatwAGEHCMlHuQzGhulWIC4RYeCCnkspRPmvwO6CD6CPi+NY1uj8lXhSEF03rJzuAbWwIXNpPASiSAWQhNxuW5lnk0upRwV59lW0=";
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();

        // Define the request body
        String requestBody = "{\"aadhaar\": \"" + encryptedAadhaarNumber + "\"}";


        try {
            Mono<String> response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class);

            System.out.println("Response: " + response.block());
        } catch (WebClientResponseException e) {
            System.out.println("Error response code: " + e.getRawStatusCode());
            System.out.println("Error response body: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

