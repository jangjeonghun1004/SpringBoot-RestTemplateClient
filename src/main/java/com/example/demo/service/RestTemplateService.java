package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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

}
