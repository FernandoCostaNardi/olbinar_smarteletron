package com.olbnar.smarteletron.controllers.tradeoperation;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;
import com.olbnar.smarteletron.services.tradeoperation.TradeOperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trade-operation")
public class TradeOperationController {

    private final TradeOperationService tradeOperationService;

    public TradeOperationController(TradeOperationService tradeOperationService) {
        this.tradeOperationService = tradeOperationService;
    }

    //Criar api para buscar as operações salvas por typo de operação
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/list")
    public Page<TradeOperationListResponse> listTradeOperations(
            @RequestBody TradeOperationListRequest tradeOperationRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return tradeOperationService.getTradeOperationsByType(tradeOperationRequest, PageRequest.of(page, size));
    }

    //Criar uma primitiva para salvar uma operação
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/save")
    public TradeOperationResponse saveTradeOperation(@RequestBody TradeOperationRequest tradeOperationRequest) {
        return tradeOperationService.saveTradeOperation(tradeOperationRequest);
    }

    //Criar uma primitiva para apagar uma operação
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTradeOperation(@PathVariable Long id) {
        tradeOperationService.deleteTradeOperation(id);
    }

}
