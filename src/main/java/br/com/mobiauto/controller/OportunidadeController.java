package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.OportunidadeModel;
import br.com.mobiauto.domain.request.OportunidadesRequest;
import br.com.mobiauto.domain.response.OportunidadesResponse;
import br.com.mobiauto.service.OportunidadesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/oportunidade")
public class OportunidadeController {

  private final OportunidadesService oportunidadesService;

  public OportunidadeController(OportunidadesService oportunidadesService) {
    this.oportunidadesService = oportunidadesService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody OportunidadesRequest oportunidadesRequest){
    log.info("Criando uma nova oportunidade: {}", oportunidadesRequest);
    oportunidadesService.save(oportunidadesRequest, null);
    return ResponseEntity.created(null).build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<OportunidadesResponse> findAll(){
    log.info("Bucando todas as oportunidades");
    return oportunidadesService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public OportunidadeModel findById(@PathVariable Long id){
    log.info("Bucando uma oportunidade pelo ID: {}", id);
    return oportunidadesService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(
                        @RequestBody OportunidadesRequest oportunidadesRequest,
                        @PathVariable Long id){
    log.info("Atualizando a oportunidade: {}", oportunidadesRequest);
    oportunidadesService.save(oportunidadesRequest, id);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id){
    log.info("Excluindo a oportunidade pelo id: {}", id);
    oportunidadesService.deleteById(id);
  }
}
