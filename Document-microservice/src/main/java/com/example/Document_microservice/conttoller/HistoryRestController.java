package com.example.Document_microservice.conttoller;


import com.example.Document_microservice.config.ConstantResponseSuccessfulText;
import com.example.Document_microservice.convert.manager.ManagerMapperHistory;
import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.dto.ResponseHistoryDto;
import com.example.Document_microservice.exeption.TheStoryDoesNotBelongToThisUser;
import com.example.Document_microservice.exeption.Validate;
import com.example.Document_microservice.service.HistoryDataValidate;
import com.example.Document_microservice.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/History")
@RequiredArgsConstructor
@Slf4j
public class HistoryRestController {

    private final HistoryService historyService;
    private final ManagerMapperHistory mapperHistory;
    private final HistoryDataValidate historyDataValidate;

    @PostMapping()
    public ResponseEntity<String> addHistory(@Valid @RequestBody RequestHistoryDto dto, BindingResult bindingResult,
                                             @RequestHeader("Authorization") String authorizationHeader) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else throw new BindException(bindingResult);
        } else {

            historyDataValidate.validate(authorizationHeader, dto);
            historyService.save(
                    mapperHistory.toModel(
                            dto
                    )
            );
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.CREATE_HISTORY_OK);
        }
    }


    @GetMapping("{historyId:\\d+}")
    public ResponseEntity<ResponseHistoryDto> getHistoryById(@PathVariable(name = "historyId") Long historyId,
                                                             @RequestHeader("Authorization") String authorizationHeader) {
        ResponseHistoryDto dto = mapperHistory.toDto(historyService.getHistoryById(historyId));
        Long pacientId = dto.pacientId();

        historyDataValidate.verification(authorizationHeader, pacientId);

        return ResponseEntity.ok().body(
                dto
        );
    }


    @PutMapping("{historyId:\\d+}")
    public ResponseEntity<String> updateHistory(@RequestBody RequestHistoryDto dto,
                                                @PathVariable(name = "historyId") Long historyId,
                                                @RequestHeader("Authorization") String authorizationHeader) {


        historyDataValidate.validate(authorizationHeader, dto);
        historyService.update(
                mapperHistory.toModel(
                        dto,
                        historyId
                )

        );
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_HISTORY_OK);
    }
}
