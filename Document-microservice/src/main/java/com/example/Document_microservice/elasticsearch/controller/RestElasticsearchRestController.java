package com.example.Document_microservice.elasticsearch.controller;


import com.example.Document_microservice.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.elasticsearch.service.HistoryElasticsearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/elastic/history")
@RequiredArgsConstructor
@Slf4j
public class RestElasticsearchRestController {

    private final HistoryElasticsearchService historyElasticsearchService;


    @SecurityRequirement(name = "JWT")
    @GetMapping("all")
    public ResponseEntity<Iterable<HistoryDocument>> getAll(){
        log.info("метода начался");
        return ResponseEntity.ok().body(
                historyElasticsearchService.getAllHistory()
        );
    }
}
