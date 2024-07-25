package com.olbnar.smarteletron.services.tradeoperation.impl;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;
import com.olbnar.smarteletron.exception.tradeoperation.TradeOperationException;
import com.olbnar.smarteletron.mapper.tradeoperation.TradeOperationMapper;
import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import com.olbnar.smarteletron.repositories.tradeoperation.TradeOperationRepository;
import com.olbnar.smarteletron.services.tradeoperation.TradeOperationService;
import com.olbnar.smarteletron.validation.tradeoperation.TradeOperationValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeOperationServiceImpl implements TradeOperationService {

    private final TradeOperationRepository tradeOperationRepository;
    private final TradeOperationValidation tradeOperationValidation;
    private final TradeOperationMapper tradeOperationMapper;

    public TradeOperationServiceImpl(TradeOperationRepository tradeOperationRepository, TradeOperationValidation tradeOperationValidation, TradeOperationMapper tradeOperationMapper) {
        this.tradeOperationRepository = tradeOperationRepository;
        this.tradeOperationValidation = tradeOperationValidation;
        this.tradeOperationMapper = tradeOperationMapper;
    }

    @Override
    public Page<TradeOperationListResponse> getTradeOperationsByType(TradeOperationListRequest tradeOperationRequest, PageRequest pageRequest) {
        tradeOperationValidation .isOperationTypeValid(tradeOperationRequest.getOperationType().toString());
        Page<TradeOperation> tradeOperationsPage = tradeOperationRepository.findByOperationType(tradeOperationRequest.getOperationType(), pageRequest);
        return tradeOperationsPage.map(tradeOperation -> tradeOperationMapper.entityToListResponse(List.of(tradeOperation)));
    }

    @Override
    public TradeOperationResponse saveTradeOperation(TradeOperationRequest tradeOperationRequest) {
        //Valida se o tipo de operação é válido
        tradeOperationValidation.isOperationTypeValid(tradeOperationRequest.getOperationType());
        //Validar se o operationCode já existe
        tradeOperationValidation.validateOperationCodeUniqueness(tradeOperationRequest.getOperationCode());
        //convert tradeOperationRequest to TradeOperation
        TradeOperation tradeOperation = tradeOperationMapper.requestToEntity(tradeOperationRequest);
        //Salvar a operação
        tradeOperationRepository.save(tradeOperation);
        //Retornar a operação salva
        return new TradeOperationResponse(tradeOperation);
    }

    @Override
    public void deleteTradeOperation(Long id) {
        //valida se a operação existe pelo id
        if (!tradeOperationRepository.existsById(id)) {
            throw new TradeOperationException("Operação não encontrada: " + id);
        }
        //Deleta a operação
        tradeOperationRepository.deleteById(id);
    }


}
