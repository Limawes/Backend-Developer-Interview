package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.domain.repository.UsuariosRepository;
import br.com.mobiauto.domain.request.UsuariosRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

  public final UsuariosRepository usuariosRepository;
  public final CargoService cargoService;


  public UsuarioService(UsuariosRepository usuariosRepository, CargoService cargoService) {
    this.usuariosRepository = usuariosRepository;
    this.cargoService = cargoService;
  }

  public UsuarioModel save(final UsuariosRequest usuariosRequest, Long usuario_id){
    UsuarioModel usuarioModel = new UsuarioModel();
    CargoModel cargo = new CargoModel();
    Long cargo_id = null;

    if(usuario_id != null){
      Optional<UsuarioModel> usuario = usuariosRepository.findById(usuario_id);
      if(usuario.isEmpty()){
        throw new RuntimeException("Usuario nao econtrado!");
      }

      usuarioModel.setId(usuario_id);
    }

    UsuarioModel resultSet = usuariosRepository.findById(usuario_id).get();
    cargo_id = resultSet.getCargo().get(Math.toIntExact(cargo.getId())).getId();
    final CargoModel cargoModel = cargoService.save(usuariosRequest.getCargo(), cargo_id);

    usuarioModel.setCodigoIdentificador(usuariosRequest.getCodigoIdentificador());
    usuarioModel.setNome(usuariosRequest.getNome());
    usuarioModel.setEmail(usuariosRequest.getEmail());
    usuarioModel.setSenha(usuariosRequest.getSenha());
    //noinspection unchecked
    usuarioModel.setCargo((List<CargoModel>) cargoModel);


    return usuariosRepository.save(usuarioModel);
  }
}
