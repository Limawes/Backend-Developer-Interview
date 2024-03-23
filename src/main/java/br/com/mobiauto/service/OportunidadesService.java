package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.*;
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
  private final RevendaService revendaService;

  public OportunidadesService(OportunidadesRepository oportunidadesRepository, ClientesService clientesService, VeiculosService veiculosService, UsuarioService usuarioService, RevendaService revendaService) {
    this.oportunidadesRepository = oportunidadesRepository;
    this.clientesService = clientesService;
    this.veiculosService = veiculosService;
    this.usuarioService = usuarioService;
    this.revendaService = revendaService;
  }

  public OportunidadeModel save(final OportunidadesRequest oportunidadesRequest, final Long oportunidade_id){
    OportunidadeModel oportunidadeModel = new OportunidadeModel();
    Long cliente_id = null;
    Long veiculo_id = null;
    Long responsavel_id = null;
    Long loja_id = null;

    if(oportunidade_id != null){
      Optional<OportunidadeModel> oportunidades = oportunidadesRepository.findById(oportunidade_id);
      if(oportunidades.isEmpty()){
        throw new RuntimeException("Oportunidade não encontrada!");
      }
      oportunidadeModel.setId(oportunidade_id);
    }
    OportunidadeModel resultSet = oportunidadesRepository.findById(oportunidade_id).get();
    cliente_id = resultSet.getClienteId().getId();
    veiculo_id = resultSet.getVeiculoId().getId();
    responsavel_id = resultSet.getResponsavelId().getId();
    loja_id = resultSet.getLojaId().getId();


    final ClienteModel clienteModel = clientesService
      .save(oportunidadesRequest.getClienteId(), cliente_id);

    final VeiculoModel veiculoModel = veiculosService
      .save(oportunidadesRequest.getVeiculoId(), veiculo_id);

    final UsuarioModel usuarioModel = usuarioService
      .save(oportunidadesRequest.getResponsavelId(), responsavel_id);

    final RevendaModel revendaModel = revendaService
      .save(oportunidadesRequest.getLojaId(), loja_id);

    oportunidadeModel.setCodigoIdentificador(oportunidadesRequest.getCodigoIdentificador());
    oportunidadeModel.setStatus(oportunidadesRequest.getStatus());
    oportunidadeModel.setMotivoConclusao(oportunidadesRequest.getMotivoConclusao());
    oportunidadeModel.setDataAtribuicao(oportunidadesRequest.getDataAtribuicao());
    oportunidadeModel.setDataConclusao(oportunidadesRequest.getDataConclusao());
    oportunidadeModel.setClienteId(clienteModel);
    oportunidadeModel.setVeiculoId(veiculoModel);
    oportunidadeModel.setResponsavelId(usuarioModel);
    oportunidadeModel.setLojaId(revendaModel);

    return oportunidadeModel;
  }
}
