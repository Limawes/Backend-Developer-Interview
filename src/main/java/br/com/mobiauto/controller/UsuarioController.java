package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.domain.request.UsuariosRequest;
import br.com.mobiauto.domain.response.UsuarioResponse;
import br.com.mobiauto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Operation(summary = "Criando um usuario")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody UsuariosRequest usuariosRequest){
    log.info("Criando usuario: {}", usuariosRequest.toString());
    usuarioService.save(usuariosRequest, null);
    return ResponseEntity.created(null).build();
  }

  @Operation(summary = "Buscando um usuario")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UsuarioResponse findById(@PathVariable Long id){
    log.info("Buscando usuario por id: {}", id);
    return usuarioService.findById(id);
  }

  @Operation(summary = "Buscando todos usuarios")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UsuarioResponse> findAll(){
    log.info("Buscando todos os usuarios!");
    return usuarioService.findAll();
  }

  @Operation(summary = "Atualizando um usuario")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody UsuariosRequest usuarioRequest, @PathVariable Long id){
    log.info("Atualizando um usuario pelo Id: {}", usuarioRequest.toString());
    usuarioService.save(usuarioRequest, id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Deletando um usuario pelo id")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable Long id){
    log.info("Deletando um usuario a partir do Id: {}", id);
    usuarioService.deleteById(id);
  }
}
