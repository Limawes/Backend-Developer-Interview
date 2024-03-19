package br.com.banco.controller;

import br.com.banco.model.TransferenciaModel;
import br.com.banco.response.TransferenciaResponse;
import br.com.banco.service.TransferenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conta/{contaId}/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService){
        this.transferenciaService = transferenciaService;
    }

    private static final Logger log = LoggerFactory.getLogger(TransferenciaController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransferenciaModel> findByIdConta(@PathVariable("contaId") Long contaId){
        log.info("Get all transfers");
        return transferenciaService.findByIdConta(contaId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TransferenciaModel> findById(@PathVariable("contaId") Long contaId,
                                                 @PathVariable("id") Long id){
        log.info("Get all transfers");
        return transferenciaService.findByIdTransferencias(contaId, id);
    }

    @GetMapping("/inicio/{inicio}/fim/{fim}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransferenciaModel> dataTransferenciaBetween(@PathVariable("contaId") Long contaId,
                                                             @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
                                                             @PathVariable("fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fim){
        log.info("Get transfers by start date and end date");
        return transferenciaService.findByDataTransferenciaBetween(inicio, fim, contaId);
    }

    @GetMapping("/operador")
    @ResponseStatus(HttpStatus.OK)
    public List<TransferenciaModel> findByNomeOperador(@PathVariable("contaId") Long contaId,
                                                       @RequestParam String op) {
        return transferenciaService.findByNomeOperador(op, contaId);
    }

    @GetMapping("/{id}/op/{op}/{inicio}/{fim}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransferenciaModel> findByAllFilters (@PathVariable("contaId") Long contaId,
                                                      @PathVariable("id") Long id,
                                                      @PathVariable("op") String op,
                                                      @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
                                                      @PathVariable("fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fim) {
        log.info("Getting all by all filters");
        return transferenciaService.findByAllFilters(contaId, id, op, inicio, fim);
    }

}
