package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class RestTemplateService {

    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server")
                .encode()
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    public String getNameWithPathVariable() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server/{name}")
                .encode()
                .build()
                .expand("Flature") // 복수의 값은 , 를 사용
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server")
                .queryParam("name", "jams")
                .encode()
                .build()
                .expand("Flature") // 복수의 값은 , 를 사용
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }


    public ResponseEntity<MemberDTO> postWithParamAndBody() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server")
                .queryParam("name", "Flature")
                .queryParam("email", "test@ai.com")
                .queryParam("organization", "jame")
                .encode()
                .build().toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("james@ai.com");
        memberDTO.setOrganization("Around Hub Studio");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);
        return responseEntity;
    }

    public ResponseEntity<MemberDTO> postWithHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server/add-header")
                .encode()
                .build().toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("james@ai.com");
        memberDTO.setOrganization("king");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "wikibooks api")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);
        return responseEntity;
    }

    public RestTemplate restTemplate() {
        // 타임아웃 설정
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofDays(2000)) // 연결 타임아웃 (2초)
                .setResponseTimeout(Timeout.ofDays(5000)) // 응답 타임아웃 (5초)
                .build();

        // 커넥션 매니저 설정
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build();

        // HttpClient 설정
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // HttpComponentsClientHttpRequestFactory 생성
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

}
