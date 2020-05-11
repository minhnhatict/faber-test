package faber.codetest.nhat.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@Service
public class HttpClientService {

    public <T> T send(String url, Map<String, Object> paramsMap, Class<T> responseType, HttpMethod method) {
        RestTemplate restTemplate = new RestTemplate();

        //init headers
        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        //add query params
        Set<String> keySet = paramsMap.keySet();
        for (String key : keySet) {
            builder.queryParam(key, paramsMap.get(key));
        }

        //send request
        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity<>(httpHeaders);

        ResponseEntity<T> result = restTemplate.exchange(builder.toUriString(), method, request, responseType);
        return result.getBody();
    }
}
