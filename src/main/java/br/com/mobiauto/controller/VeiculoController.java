package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.VeiculoModel;
import br.com.mobiauto.domain.request.VeiculosRequest;
import br.com.mobiauto.domain.response.VeiculoResponse;
import br.com.mobiauto.service.VeiculoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/veiculo")
public class VeiculoController {

  private final VeiculoService veiculoService;

  public VeiculoController(VeiculoService veiculoService) {
    this.veiculoService = veiculoService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROPIETARIO', 'ROLE_GERENTE', 'ROLE_ASSISTENTE')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody VeiculosRequest veiculosRequest){
    log.info("Criando veiculo: {}", veiculosRequest.toString());
    veiculoService.save(veiculosRequest, null);
    return ResponseEntity.created(null).build();
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROPIETARIO', 'ROLE_GERENTE', 'ROLE_ASSISTENTE')")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public VeiculoModel findById(@PathVariable Long id){
    log.info("Buscando veiculo por id: {}", id);
    return veiculoService.findById(id);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROPIETARIO', 'ROLE_GERENTE', 'ROLE_ASSISTENTE')")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<VeiculoResponse> findAll(){
    log.info("Buscando todos os veículos");
    return veiculoService.findAll();
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROPIETARIO', 'ROLE_GERENTE', 'ROLE_ASSISTENTE')")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody VeiculosRequest veiculosRequest, @PathVariable Long id){
    log.info("Atualizando um veiculo pelo Id: {}", veiculosRequest.toString());
    veiculoService.save(veiculosRequest, id);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROPIETARIO', 'ROLE_GERENTE', 'ROLE_ASSISTENTE')")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable Long id){
    log.info("Deletando um veiculo a partir do Id: {}", id);
    veiculoService.deleteById(id);
  }
}
