package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.model.UsuariosModel;
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

  public UsuariosModel save(final UsuariosRequest usuariosRequest, Long usuario_id){
    UsuariosModel usuariosModel = new UsuariosModel();
    CargoModel cargo = new CargoModel();
    Long cargo_id = null;

    if(usuario_id != null){
      Optional<UsuariosModel> usuario = usuariosRepository.findById(usuario_id);
      if(usuario.isEmpty()){
        throw new RuntimeException("Usuario nao econtrado!");
      }

      usuariosModel.setId(usuario_id);
    }

    UsuariosModel resultSet = usuariosRepository.findById(usuario_id).get();
    cargo_id = resultSet.getCargo().get(Math.toIntExact(cargo.getId())).getId();
    final CargoModel cargoModel = cargoService.save(usuariosRequest.getCargo(), cargo_id);

    usuariosModel.setCodigoIdentificador(usuariosRequest.getCodigoIdentificador());
    usuariosModel.setNome(usuariosRequest.getNome());
    usuariosModel.setEmail(usuariosRequest.getEmail());
    usuariosModel.setSenha(usuariosRequest.getSenha());
    //noinspection unchecked
    usuariosModel.setCargo((List<CargoModel>) cargoModel);


    return usuariosRepository.save(usuariosModel);
  }
}
