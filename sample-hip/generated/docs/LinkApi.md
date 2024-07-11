# LinkApi

All URIs are relative to *http://localhost:8082/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**linkCareContexts**](LinkApi.md#linkCareContexts) | **POST** /link-carecontexts | Links care contexts for a given patient&#39;s abha address |
| [**linkStatusRequestIdGet**](LinkApi.md#linkStatusRequestIdGet) | **GET** /link-status/{requestId} | Get status of Link request. |
| [**verifyOTP**](LinkApi.md#verifyOTP) | **POST** /verify-otp | Verify OTP for link auth modes Mobile and Aadhaar |


<a id="linkCareContexts"></a>
# **linkCareContexts**
> FacadeResponse linkCareContexts(linkCareContextsRequest)

Links care contexts for a given patient&#39;s abha address

Links care contexts for a given patient&#39;s abha address

### Example
```java
// Import classes:
import com.nha.abdm.wrapper.client.invoker.ApiClient;
import com.nha.abdm.wrapper.client.invoker.ApiException;
import com.nha.abdm.wrapper.client.invoker.Configuration;
import com.nha.abdm.wrapper.client.invoker.models.*;
import com.nha.abdm.wrapper.client.api.LinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082/v1");

    LinkApi apiInstance = new LinkApi(defaultClient);
    LinkCareContextsRequest linkCareContextsRequest = new LinkCareContextsRequest(); // LinkCareContextsRequest | Links Care Contexts
    try {
      FacadeResponse result = apiInstance.linkCareContexts(linkCareContextsRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LinkApi#linkCareContexts");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **linkCareContextsRequest** | [**LinkCareContextsRequest**](LinkCareContextsRequest.md)| Links Care Contexts | [optional] |

### Return type

[**FacadeResponse**](FacadeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request body supplied |  -  |
| **404** | Address not found |  -  |
| **422** | Validation exception |  -  |

<a id="linkStatusRequestIdGet"></a>
# **linkStatusRequestIdGet**
> RequestStatusResponse linkStatusRequestIdGet(requestId)

Get status of Link request.

### Example
```java
// Import classes:
import com.nha.abdm.wrapper.client.invoker.ApiClient;
import com.nha.abdm.wrapper.client.invoker.ApiException;
import com.nha.abdm.wrapper.client.invoker.Configuration;
import com.nha.abdm.wrapper.client.invoker.models.*;
import com.nha.abdm.wrapper.client.api.LinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082/v1");

    LinkApi apiInstance = new LinkApi(defaultClient);
    String requestId = "requestId_example"; // String | Request Id of the link care context request.
    try {
      RequestStatusResponse result = apiInstance.linkStatusRequestIdGet(requestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LinkApi#linkStatusRequestIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **requestId** | **String**| Request Id of the link care context request. | |

### Return type

[**RequestStatusResponse**](RequestStatusResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request body supplied |  -  |
| **404** | Address not found |  -  |
| **422** | Validation exception |  -  |

<a id="verifyOTP"></a>
# **verifyOTP**
> FacadeResponse verifyOTP(verifyOTPRequest)

Verify OTP for link auth modes Mobile and Aadhaar

Verify OTP for link auth modes Mobile and Aadhaar

### Example
```java
// Import classes:
import com.nha.abdm.wrapper.client.invoker.ApiClient;
import com.nha.abdm.wrapper.client.invoker.ApiException;
import com.nha.abdm.wrapper.client.invoker.Configuration;
import com.nha.abdm.wrapper.client.invoker.models.*;
import com.nha.abdm.wrapper.client.api.LinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082/v1");

    LinkApi apiInstance = new LinkApi(defaultClient);
    VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest(); // VerifyOTPRequest | Verifies OTP
    try {
      FacadeResponse result = apiInstance.verifyOTP(verifyOTPRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LinkApi#verifyOTP");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **verifyOTPRequest** | [**VerifyOTPRequest**](VerifyOTPRequest.md)| Verifies OTP | [optional] |

### Return type

[**FacadeResponse**](FacadeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request body supplied |  -  |
| **404** | Address not found |  -  |
| **422** | Validation exception |  -  |

