package com.example.Document_microservice.conttoller;


import com.example.Document_microservice.convert.manager.ManagerMapperHistory;
import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/History")
@RequiredArgsConstructor
@Slf4j
public class HistoryRestController {

    private final HistoryService historyService;
    private final ManagerMapperHistory mapperHistory;

    @PostMapping()
    public ResponseEntity<String> addHistory(@Valid @RequestBody RequestHistoryDto dto, BindingResult bindingResult) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else throw new BindException(bindingResult);
        } else {

            historyService.save(
                    mapperHistory.toModel(
                            dto
                    )
            );

            return ResponseEntity.ok().body("123");
        }
    }
}
