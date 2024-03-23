package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.VeiculoModel;
import br.com.mobiauto.domain.request.VeiculosRequest;
import br.com.mobiauto.domain.response.VeiculoResponse;
import br.com.mobiauto.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @Operation(summary = "Criando um veiculo")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody VeiculosRequest veiculosRequest){
    log.info("Criando veiculo: {}", veiculosRequest.toString());
    veiculoService.save(veiculosRequest, null);
    return ResponseEntity.created(null).build();
  }

  @Operation(summary = "Buscando um veiculo")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public VeiculoModel findById(@PathVariable Long id){
    log.info("Buscando veiculo por id: {}", id);
    return veiculoService.findById(id);
  }

  @Operation(summary = "Buscando todos veiculos")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<VeiculoResponse> findAll(){
    log.info("Buscando todos os veículos");
    return veiculoService.findAll();
  }

  @Operation(summary = "Atualizando um veiculo")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody VeiculosRequest veiculosRequest, @PathVariable Long id){
    log.info("Atualizando um veiculo pelo Id: {}", veiculosRequest.toString());
    veiculoService.save(veiculosRequest, id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Deletando um veiculo pelo id")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable Long id){
    log.info("Deletando um veiculo a partir do Id: {}", id);
    veiculoService.deleteById(id);
  }
}
