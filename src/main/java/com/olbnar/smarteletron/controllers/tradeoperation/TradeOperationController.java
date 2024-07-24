package com.olbnar.smarteletron.controllers.tradeoperation;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;
import com.olbnar.smarteletron.services.tradeoperation.TradeOperationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @PostMapping ("/list")
    public TradeOperationListResponse listTradeOperations(@RequestBody TradeOperationListRequest tradeOperationRequest) {
        return tradeOperationService.getTradeOperationsByType(tradeOperationRequest);
    }

    //Criar uma primitiva para salvar uma operação
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/save")
    public TradeOperationResponse saveTradeOperation(@RequestBody TradeOperationRequest tradeOperationRequest) {
        return tradeOperationService.saveTradeOperation(tradeOperationRequest);
    }

}
