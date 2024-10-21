package com.example.Document_microservice.document.conttoller;


import com.example.Document_microservice.document.config.ConstantResponseSuccessfulText;
import com.example.Document_microservice.document.convert.manager.ManagerMapperHistory;
import com.example.Document_microservice.document.dto.RequestHistoryDto;
import com.example.Document_microservice.document.dto.ResponseHistoryDto;
import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.elasticsearch.service.HistoryElasticsearchService;
import com.example.Document_microservice.document.model.History;
import com.example.Document_microservice.document.service.HistoryDataValidate;
import com.example.Document_microservice.document.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/History")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "History")
public class HistoryRestController {

    private final HistoryService historyService;
    private final ManagerMapperHistory mapperHistory;
    private final HistoryDataValidate historyDataValidate;
    private final HistoryElasticsearchService historyElasticsearchService;



    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    @PostMapping()
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Создание истории посещения и назначения", description = "Только администраторы и менеджеры и врачи. {pacientId} - с олью User. Все поля обязатлеьны")
    public ResponseEntity<String> addHistory(@Valid @RequestBody RequestHistoryDto dto, BindingResult bindingResult,
                                             HttpServletRequest request) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else throw new BindException(bindingResult);
        } else {

            historyDataValidate.validate(request.getHeader("Authorization"), dto);

            History history =  historyService.save(mapperHistory.toModel(dto));
            historyElasticsearchService.add(mapperHistory.toModelHistoryInModelHistoryDocument(history));

            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.CREATE_HISTORY_OK);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_DOCTOR')")
    @GetMapping("{id:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение подробной информации о посещении и назначениях", description = "Только врачи и аккаунт, которому принадлежит история")
    public ResponseEntity<ResponseHistoryDto> getHistoryById(@PathVariable(name = "id") Long historyId,
                                                             HttpServletRequest request) {
        ResponseHistoryDto dto = mapperHistory.toDtoFromHistoryDocument(historyElasticsearchService.findHistoryById(historyId));
        Long pacientId = dto.pacientId();

        historyDataValidate.verification(request.getHeader("Authorization"), pacientId);

        return ResponseEntity.ok().body(
                dto
        );
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_DOCTOR')")
    @GetMapping("Account/{id:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение истории посещений и назначений аккаунта", description = "Только врачи и аккаунт, которому принадлежит история. Возвращает записи где {pacientId}={id}.")
    public ResponseEntity<Iterable<ResponseHistoryDto>> getAllHistoryUserByIdUser(@PathVariable(name = "id") Long pacientId,
                                                                              HttpServletRequest request) {

        historyDataValidate.verification(request.getHeader("Authorization"), pacientId);

        return ResponseEntity.ok().body(
                mapperHistory.toDtoFromHistoryDocument(
                        historyElasticsearchService.getAllHistoryByPacientId(pacientId)
                )
        );
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    @PutMapping("{id:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Обновление истории посещения и назначения", description = "Только администраторы и менеджеры и врачи. {pacientId} - сролью User.")
    public ResponseEntity<String> updateHistory(@RequestBody RequestHistoryDto dto,
                                                @PathVariable(name = "id") Long historyId,
                                                HttpServletRequest request) {


        historyDataValidate.validate(request.getHeader("Authorization"), dto);

        History history = mapperHistory.toModel(dto, historyId);
        HistoryDocument historyDocument = mapperHistory.toModelHistoryInModelHistoryDocument(history);

        historyService.update(history);
        historyElasticsearchService.update(historyDocument);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_HISTORY_OK);
    }
}
