package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class RestTemplateController {
    private final RestTemplateService restTemplateService;

    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }


    @GetMapping
    public String getName() {
        return this.restTemplateService.getName();
    }

    @GetMapping("/pathVariable")
    public String getNameWithPathVariable() {
        return this.restTemplateService.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public String getNameWithParameter() {
        return this.restTemplateService.getNameWithParameter();
    }


    @PostMapping
    public ResponseEntity<MemberDTO> postDTO() {
        return this.restTemplateService.postWithParamAndBody();
    }

    @PostMapping("/header")
    public ResponseEntity<MemberDTO> postWithHeader() {
        return this.restTemplateService.postWithHeader();
    }

}
