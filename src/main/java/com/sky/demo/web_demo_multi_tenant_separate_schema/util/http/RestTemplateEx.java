package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.Map;

/**
 * expand for RestTemplate
 */
public class RestTemplateEx extends RestTemplate {

    // PUT

    public <T> T putForObject(String url, Object request, Class<T> responseType, Object ... uriVariables) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters());
        return (T)this.execute(url, HttpMethod.PUT, requestCallback, (ResponseExtractor)responseExtractor, uriVariables);
    }

    public <T> T putForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters());
        return (T)this.execute(url, HttpMethod.PUT, requestCallback, (ResponseExtractor)responseExtractor, uriVariables);
    }

    public <T> T putForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters());
        return (T)this.execute(url, HttpMethod.PUT, requestCallback, (ResponseExtractor)responseExtractor);
    }

    public <T> ResponseEntity<T> putForEntity(String url, Object request, Class<T> responseType, Object ... uriVariables) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        ResponseExtractor responseExtractor = this.responseEntityExtractor(responseType);
        return (ResponseEntity)this.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    public <T> ResponseEntity<T> putForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        ResponseExtractor responseExtractor = this.responseEntityExtractor(responseType);
        return (ResponseEntity)this.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }


    //DELETE

    public <T> ResponseEntity<T> deleteForEntity(String url, Class<T> responseType, Object ... uriVariables) throws RestClientException {
        ResponseExtractor responseExtractor = this.responseEntityExtractor(responseType);
        return (ResponseEntity)this.execute(url, HttpMethod.DELETE, null, responseExtractor, uriVariables);
    }

    public <T> ResponseEntity<T> deleteForEntityWithRequestBody(String url, Object request, Class<T> responseType, Object ... uriVariables) throws RestClientException {
        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        ResponseExtractor responseExtractor = this.responseEntityExtractor(responseType);
        return (ResponseEntity)this.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables);
    }
}

