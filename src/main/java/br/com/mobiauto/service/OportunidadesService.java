package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.*;
import br.com.mobiauto.domain.repository.OportunidadesRepository;
import br.com.mobiauto.domain.request.OportunidadesRequest;
import br.com.mobiauto.domain.response.OportunidadesResponse;
import br.com.mobiauto.domain.response.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OportunidadesService {

  private final OportunidadesRepository oportunidadesRepository;
  private final ClientesService clientesService;
  private final VeiculoService veiculoService;
  private final UsuarioService usuarioService;
  private final RevendaService revendaService;

  public OportunidadesService(OportunidadesRepository oportunidadesRepository, ClientesService clientesService, VeiculoService veiculoService, UsuarioService usuarioService, RevendaService revendaService) {
    this.oportunidadesRepository = oportunidadesRepository;
    this.clientesService = clientesService;
    this.veiculoService = veiculoService;
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
      oportunidadeModel.setIdOportunidade(oportunidadesRequest.getIdOportunidade());
    }
    OportunidadeModel resultSet = oportunidadesRepository.findById(oportunidade_id).get();
    cliente_id = resultSet.getClienteId().getIdCliente();
    veiculo_id = resultSet.getVeiculoId().getIdVeiculo();
    responsavel_id = resultSet.getResponsavelId().getIdUsuario();
    loja_id = resultSet.getLojaId().getIdRevenda();


    final ClienteModel clienteModel = clientesService
      .findById(cliente_id);

    final VeiculoModel veiculoModel = veiculoService
      .findById(veiculo_id);

    final UsuarioResponse usuarioModel = usuarioService
      .findById(responsavel_id);

    final RevendaModel revendaModel = revendaService
      .findById(loja_id);

    oportunidadeModel.setCodigoIdentificador(oportunidadesRequest.getCodigoIdentificador());
    oportunidadeModel.setStatus(oportunidadesRequest.getStatus());
    oportunidadeModel.setMotivoConclusao(oportunidadesRequest.getMotivoConclusao());
    oportunidadeModel.setDataAtribuicao(oportunidadesRequest.getDataAtribuicao());
    oportunidadeModel.setDataConclusao(oportunidadesRequest.getDataConclusao());
    oportunidadeModel.setClienteId(clienteModel);
    oportunidadeModel.setVeiculoId(veiculoModel);
//    oportunidadeModel.setResponsavelId();
    oportunidadeModel.setLojaId(revendaModel);

    return oportunidadesRepository.save(oportunidadeModel);
  }

  public OportunidadeModel findById(final Long id){
    Optional<OportunidadeModel> oportunidadeModel = oportunidadesRepository.findById(id);
    if(Objects.isNull(oportunidadeModel)){
      throw new RuntimeException("Essa oportunidade não foi encontrada!");
    }
    return oportunidadeModel.get();
  }

  public List<OportunidadesResponse> findAll(){
    List<OportunidadeModel> oportunidades = oportunidadesRepository.findAll();
    List<OportunidadesResponse> oportunidadesResponseList = new ArrayList<>();

    for(OportunidadeModel oportunidade : oportunidades){
      OportunidadesResponse oportunidadesResponse = new OportunidadesResponse();

      oportunidadesResponse.setIdOportunidade(oportunidade.getIdOportunidade());
      oportunidadesResponse.setClienteId(oportunidadesResponse.getClienteId());
      oportunidadesResponse.setStatus(oportunidade.getStatus());
      oportunidadesResponse.setCodigoIdentificador(oportunidade.getCodigoIdentificador());
      oportunidadesResponse.setDataAtribuicao(oportunidade.getDataAtribuicao());
      oportunidadesResponse.setDataConclusao(oportunidade.getDataConclusao());
      oportunidadesResponse.setMotivoConclusao(oportunidade.getMotivoConclusao());
      oportunidadesResponse.setLojaId(oportunidadesResponse.getLojaId());
      oportunidadesResponse.setVeiculoId(oportunidadesResponse.getVeiculoId());

      oportunidadesResponseList.add(oportunidadesResponse);
    }

    return oportunidadesResponseList;
  }

  public void deleteById(final Long id){
    OportunidadeModel oportunidadeModel = new OportunidadeModel();
    if(Objects.isNull(oportunidadeModel)){
      throw new RuntimeException("Sem dados para excluir");
    }
    oportunidadesRepository.getById(id);
  }
}
