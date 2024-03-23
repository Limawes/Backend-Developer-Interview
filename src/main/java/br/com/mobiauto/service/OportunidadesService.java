package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.ClientesModel;
import br.com.mobiauto.domain.model.OportunidadesModel;
import br.com.mobiauto.domain.model.UsuariosModel;
import br.com.mobiauto.domain.model.VeiculosModel;
import br.com.mobiauto.domain.repository.OportunidadesRepository;
import br.com.mobiauto.domain.request.OportunidadesRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OportunidadesService {

  private final OportunidadesRepository oportunidadesRepository;
  private final ClientesService clientesService;
  private final VeiculosService veiculosService;
  private final UsuarioService usuarioService;

  public OportunidadesService(OportunidadesRepository oportunidadesRepository, ClientesService clientesService, VeiculosService veiculosService, UsuarioService usuarioService) {
    this.oportunidadesRepository = oportunidadesRepository;
    this.clientesService = clientesService;
    this.veiculosService = veiculosService;
    this.usuarioService = usuarioService;
  }

  public void save(final OportunidadesRequest oportunidadesRequest, final Long oportunidade_id){
    OportunidadesModel oportunidadesModel = new OportunidadesModel();
    Long cliente_id = null;
    Long veiculo_id = null;
    Long responsavel_id = null;

    if(oportunidade_id != null){
      Optional<OportunidadesModel> oportunidades = oportunidadesRepository.findById(oportunidade_id);
      if(oportunidades.isEmpty()){
        throw new RuntimeException("Oportunidade não encontrada!");
      }
      oportunidadesModel.setId(oportunidade_id);
    }
    OportunidadesModel resultSet = oportunidadesRepository.findById(oportunidade_id).get();
    cliente_id = resultSet.getClienteId().getId();
    veiculo_id = resultSet.getVeiculoId().getId();
    responsavel_id = resultSet.getResponsavelId().getId();


    final ClientesModel clientesModel = clientesService
      .save(oportunidadesRequest.getClienteId(), cliente_id);

    final VeiculosModel veiculosModel = veiculosService
      .save(oportunidadesRequest.getVeiculoId(), veiculo_id);

    final UsuariosModel usuariosModel = usuarioService
      .save(oportunidadesRequest.getResponsavelId(), responsavel_id);

    oportunidadesModel.setCodigoIdentificador(oportunidadesRequest.getCodigoIdentificador());
    oportunidadesModel.setStatus(oportunidadesRequest.getStatus());
    oportunidadesModel.setMotivoConclusao(oportunidadesRequest.getMotivoConclusao());
    oportunidadesModel.setDataAtribuicao(oportunidadesRequest.getDataAtribuicao());
    oportunidadesModel.setDataConclusao(oportunidadesRequest.getDataConclusao());
    oportunidadesModel.setClienteId(clientesModel);
    oportunidadesModel.setVeiculoId(veiculosModel);
    oportunidadesModel.setResponsavelId(usuariosModel);

  }
}
