package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.request.CargoRequest;
import br.com.mobiauto.domain.response.CargoResponse;
import br.com.mobiauto.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/cargo")
public class CargoController {

  private final CargoService cargoService;

  public CargoController(CargoService cargoService) {
    this.cargoService = cargoService;
  }

  @Operation(summary = "Criando um cargo")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody CargoRequest cargoRequest){
    log.info("Criando cargo: {}", cargoRequest.toString());
    cargoService.save(cargoRequest, null);
    return ResponseEntity.created(null).build();
  }

  @Operation(summary = "Buscando um cargo")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CargoModel findById(@PathVariable Long id){
    log.info("Buscando cargo por id: {}", id);
    return cargoService.findById(id);
  }

  @Operation(summary = "Buscando todos cargo")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CargoResponse> findAll(){
    log.info("Buscando todos os cargos");
    return cargoService.findAll();
  }

  @Operation(summary = "Atualizando um cargo")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody CargoRequest cargoRequest, @PathVariable Long id){
    log.info("Atualizando um cargo pelo Id: {}", cargoRequest.toString());
    cargoService.save(cargoRequest, id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Deletando um cargo pelo id")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable Long id){
    log.info("Deletando um cargo a partir do Id: {}", id);
    cargoService.deleteById(id);
  }
}
