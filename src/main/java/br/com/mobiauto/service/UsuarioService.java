package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.model.RevendaModel;
import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.domain.model.UsuariosCargosModel;
import br.com.mobiauto.domain.repository.CargoRepository;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.domain.repository.UsuarioRepository;
import br.com.mobiauto.domain.repository.UsuariosCargosRepository;
import br.com.mobiauto.domain.request.UsuariosRequest;
import br.com.mobiauto.domain.response.CargoResponse;
import br.com.mobiauto.domain.response.UsuarioResponse;
import br.com.mobiauto.domain.response.UsuariosCargosResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

  public final UsuarioRepository usuarioRepository;
  public final CargoService cargoService;
  public final CargoRepository cargoRepository;
  public final RevendaRepository revendaRepository;
  private final UsuariosCargosRepository usuariosCargosRepository;


  public UsuarioService(UsuarioRepository usuarioRepository, CargoService cargoService, CargoRepository cargoRepository, RevendaRepository revendaRepository,
                        UsuariosCargosRepository usuariosCargosRepository) {
    this.usuarioRepository = usuarioRepository;
    this.cargoService = cargoService;
    this.cargoRepository = cargoRepository;
    this.revendaRepository = revendaRepository;
    this.usuariosCargosRepository = usuariosCargosRepository;
  }

  @Transactional
  public UsuarioModel save(final UsuariosRequest usuariosRequest, Long usuario_id){
    UsuarioModel usuarioModel = new UsuarioModel();

    if(usuario_id != null){
      Optional<UsuarioModel> usuario = usuarioRepository.findById(usuario_id);
      if(usuario.isEmpty()){
        throw new RuntimeException("Usuario nao econtrado!");
      }

      usuarioModel.setIdUsuario(usuario_id);
    }

    usuarioModel.setNome(usuariosRequest.getNome());
    usuarioModel.setEmail(usuariosRequest.getEmail());
    usuarioModel.setSenha(usuariosRequest.getSenha());

    if(usuariosRequest.getCargo() != null){
      CargoModel cargo = cargoRepository.findById(usuariosRequest.getCargo())
        .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado!"));
      usuarioModel.setCargo(usuarioModel.getCargo());
      UsuariosCargosModel usuariosCargosModel = new UsuariosCargosModel();
      UsuariosCargosResponse usuariosCargosResponse = new UsuariosCargosResponse();
      if(usuariosCargosModel.getId() == null ){
        usuariosCargosModel.setId(usuariosCargosResponse.getId());
      }
      usuariosCargosModel.setUsuarioId(usuarioModel);
      usuariosCargosModel.setCargoId(cargo);

      usuariosCargosRepository.save(usuariosCargosModel);
    }

    if(usuariosRequest.getLojaId() != null){
      RevendaModel loja = revendaRepository.findById(usuariosRequest.getLojaId())
        .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada!"));
      usuarioModel.setLojaId(loja);
    }

    return usuarioRepository.save(usuarioModel);
  }

  public UsuarioResponse findById(final Long id){
    UsuarioResponse usuarioResponse = new UsuarioResponse();
    CargoResponse cargoResponse = new CargoResponse();
    if(usuarioResponse.getIdUsuario().equals(id)){
      usuarioResponse.setIdUsuario(usuarioResponse.getIdUsuario());
      usuarioResponse.setCargo(usuarioResponse.getCargo());
      usuarioResponse.setLojaId(usuarioResponse.getLojaId());
      usuarioResponse.setNome(usuarioResponse.getNome());
      usuarioResponse.setEmail(usuarioResponse.getEmail());
    }
    return usuarioResponse;
  }

  public List<UsuarioResponse> findAll(){
    List<UsuarioModel> usuarios = usuarioRepository.findAll();
    List<UsuarioResponse> usuarioResponseList = new ArrayList<>();

    for(UsuarioModel usuario : usuarios){
      UsuarioResponse usuarioResponse = new UsuarioResponse();
      usuarioResponse.setIdUsuario(usuario.getIdUsuario());
      usuarioResponse.setNome(usuario.getNome());
      usuarioResponse.setEmail(usuario.getEmail());
      usuarioResponse.setCargo(usuarioResponse.getCargo());
      usuarioResponse.setLojaId(usuarioResponse.getLojaId());

      usuarioResponseList.add(usuarioResponse);
    }

    return usuarioResponseList;
  }

  public void deleteById(Long id){
    UsuarioModel usuarioModel = new UsuarioModel();
    if(Objects.isNull(usuarioModel)){
      throw new RuntimeException("Sem dados para exlcuir");
    }
    usuarioRepository.deleteById(id);
  }

}
