package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.*;
import br.com.mobiauto.domain.repository.*;
import br.com.mobiauto.domain.request.OportunidadesRequest;
import br.com.mobiauto.domain.response.OportunidadesResponse;
import br.com.mobiauto.domain.response.UsuarioResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final UsuarioRepository usuarioRepository;
  private final ClientesRepository clientesRepository;
  private final VeiculoRepository veiculoRepository;
  private final RevendaRepository revendaRepository;

  public OportunidadesService(OportunidadesRepository oportunidadesRepository, ClientesService clientesService, VeiculoService veiculoService, UsuarioService usuarioService, RevendaService revendaService,
                              UsuarioRepository usuarioRepository,
                              ClientesRepository clientesRepository,
                              VeiculoRepository veiculoRepository,
                              RevendaRepository revendaRepository) {
    this.oportunidadesRepository = oportunidadesRepository;
    this.clientesService = clientesService;
    this.veiculoService = veiculoService;
    this.usuarioService = usuarioService;
    this.revendaService = revendaService;
    this.usuarioRepository = usuarioRepository;
    this.clientesRepository = clientesRepository;
    this.veiculoRepository = veiculoRepository;
    this.revendaRepository = revendaRepository;
  }

  @Transactional
  public OportunidadeModel save(final OportunidadesRequest oportunidadesRequest, final Long oportunidade_id){
    OportunidadeModel oportunidadeModel = new OportunidadeModel();
    Long responsavel_id = null;

    if(oportunidade_id != null){
      Optional<OportunidadeModel> oportunidades = oportunidadesRepository.findById(oportunidade_id);
      if(oportunidades.isEmpty()){
        throw new RuntimeException("Oportunidade não encontrada!");
      }
      oportunidadeModel.setIdOportunidade(oportunidade_id);
    }

    oportunidadeModel.setStatus(oportunidadesRequest.getStatus().isEmpty() ?
      "novo" : oportunidadesRequest.getStatus());
    if(
      oportunidadesRequest.getStatus().equalsIgnoreCase("concluído")
        || oportunidadesRequest.getStatus().equalsIgnoreCase("concluido")
    ){
      oportunidadeModel.setMotivoConclusao(oportunidadesRequest.getMotivoConclusao());
    }
    oportunidadeModel.setMotivoConclusao(null);
    oportunidadeModel.setDataAtribuicao(oportunidadesRequest.getDataAtribuicao());
    oportunidadeModel.setDataConclusao(oportunidadesRequest.getDataConclusao());

    if(oportunidadesRequest.getClienteId() != null){
      ClienteModel cliente = clientesRepository.findById(oportunidadesRequest.getClienteId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
      oportunidadeModel.setClienteId(cliente);
    }

    if(oportunidadesRequest.getVeiculoId() != null){
      VeiculoModel veiculo = veiculoRepository.findById(oportunidadesRequest.getVeiculoId())
        .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
      oportunidadeModel.setVeiculoId(veiculo);
    }

    if(oportunidadesRequest.getLojaId() != null){
      RevendaModel revenda = revendaRepository.findById(oportunidadesRequest.getLojaId())
        .orElseThrow(() -> new IllegalArgumentException("Revenda não encontrada"));
      oportunidadeModel.setLojaId(revenda);
    }

    if(oportunidadesRequest.getResponsavelId() != null){
      UsuarioModel usuario = usuarioRepository.findById(oportunidadesRequest.getResponsavelId())
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
      oportunidadeModel.setResponsavelId(usuario);
    }
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
      oportunidadesResponse.setCliente(oportunidade.getClienteId());
      oportunidadesResponse.setStatus(oportunidade.getStatus());
      oportunidadesResponse.setDataAtribuicao(oportunidade.getDataAtribuicao());
      oportunidadesResponse.setDataConclusao(oportunidade.getDataConclusao());
      oportunidadesResponse.setMotivoConclusao(oportunidade.getMotivoConclusao());
      oportunidadesResponse.setLoja(oportunidade.getLojaId());
      oportunidadesResponse.setVeiculo(oportunidade.getVeiculoId());
      oportunidadesResponse.setResposavel(oportunidade.getResponsavelId());

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
