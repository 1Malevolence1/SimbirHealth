package com.example.Document_microservice.document.elasticsearch.controller;


import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.elasticsearch.service.HistoryElasticsearchService;
import com.example.Document_microservice.document.model.History;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
                historyElasticsearchService.getAllHistoryByPacientId(123l)
        );
    }


    @SecurityRequirement(name = "JWT")
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody History history){
        log.info("{}", history);
        historyElasticsearchService.add(new HistoryDocument(history));
        return ResponseEntity.ok().build();
    }
}
