package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.RevendaModel;
import br.com.mobiauto.domain.request.RevendasRequest;
import br.com.mobiauto.domain.response.RevendaResponse;
import br.com.mobiauto.service.RevendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/revenda")
public class RevendaController {

  private final RevendaService revendaService;

  public RevendaController(RevendaService revendaService) {
    this.revendaService = revendaService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody RevendasRequest revendasRequest){
    log.info("Criando uma nova revenda: {}", revendasRequest);
    revendaService.save(revendasRequest, null);
    return ResponseEntity.created(null).build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RevendaResponse> findAll(){
    log.info("Bucando todas as Revendas");
    return revendaService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RevendaModel findById(@PathVariable Long id){
    log.info("Bucando uma revenda pelo ID: {}", id);
    return revendaService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody RevendasRequest revendasRequest, Long id){
    log.info("Atualizando a revenda: {}", revendasRequest.getNomeSocial());
    revendaService.save(revendasRequest, id);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id){
    log.info("Excluindo a revenda pelo id: {}", id);
    revendaService.deleteById(id);
  }
}
