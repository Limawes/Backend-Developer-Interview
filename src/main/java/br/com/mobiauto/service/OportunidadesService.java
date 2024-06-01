package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.*;
import br.com.mobiauto.domain.repository.*;
import br.com.mobiauto.domain.request.OportunidadesRequest;
import br.com.mobiauto.domain.response.OportunidadesResponse;
import br.com.mobiauto.security.domain.model.UserModel;
import br.com.mobiauto.security.domain.repository.UserRepository;
import br.com.mobiauto.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OportunidadesService {

  private final OportunidadesRepository oportunidadesRepository;
  private final ClientesService clientesService;
  private final VeiculoService veiculoService;
  private final UserService userService;
  private final RevendaService revendaService;
  private final UserRepository userRepository;
  private final ClientesRepository clientesRepository;
  private final VeiculoRepository veiculoRepository;
  private final RevendaRepository revendaRepository;

  @Autowired
  private Queue<OportunidadeModel> filaDeOportunidades;

  public OportunidadesService(OportunidadesRepository oportunidadesRepository, ClientesService clientesService, VeiculoService veiculoService, UserService userService, RevendaService revendaService,
                              UserRepository userRepository,
                              ClientesRepository clientesRepository,
                              VeiculoRepository veiculoRepository,
                              RevendaRepository revendaRepository) {
    this.oportunidadesRepository = oportunidadesRepository;
    this.clientesService = clientesService;
    this.veiculoService = veiculoService;
    this.userService = userService;
    this.revendaService = revendaService;
    this.userRepository = userRepository;
    this.clientesRepository = clientesRepository;
    this.veiculoRepository = veiculoRepository;
    this.revendaRepository = revendaRepository;
  }

  @Transactional
  public OportunidadeModel save(final OportunidadesRequest oportunidadesRequest, final Long oportunidadeId) {
    OportunidadeModel oportunidadeModel = new OportunidadeModel();

    //Verificando se é pra atualizar ou criar novo
    if (oportunidadeId != null) {
      Optional<OportunidadeModel> oportunidades = oportunidadesRepository.findById(oportunidadeId);
      if (oportunidades.isEmpty()) {
        throw new RuntimeException("Oportunidade não encontrada!");
      }
      oportunidadeModel.setIdOportunidade(oportunidadeId);
    }

    oportunidadeModel.setDataAtribuicao(LocalDateTime.now());
    oportunidadeModel.setStatus(oportunidadesRequest.getStatus().isEmpty() ?
      "novo" : oportunidadesRequest.getStatus());
    if (oportunidadeId != null &&
      (oportunidadesRequest.getStatus().equalsIgnoreCase("concluido")
        || oportunidadesRequest.getStatus().equalsIgnoreCase("concluído"))
    ) {
      oportunidadeModel.setDataConclusao(LocalDateTime.now());
      oportunidadeModel.setMotivoConclusao(oportunidadesRequest.getMotivoConclusao());
    } else {
      oportunidadeModel.setMotivoConclusao(null);
    }

    if (oportunidadesRequest.getClienteId() != null) {
      ClienteModel cliente = clientesRepository.findById(oportunidadesRequest.getClienteId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
      oportunidadeModel.setCliente(cliente);
    }

    if (oportunidadesRequest.getVeiculoId() != null) {
      VeiculoModel veiculo = veiculoRepository.findById(oportunidadesRequest.getVeiculoId())
        .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
      oportunidadeModel.setVeiculo(veiculo);
    }

    if (oportunidadesRequest.getLojaId() != null) {
      RevendaModel revenda = revendaRepository.findById(oportunidadesRequest.getLojaId())
        .orElseThrow(() -> new IllegalArgumentException("Revenda não encontrada"));
      oportunidadeModel.setLoja(revenda);
    }

    if (oportunidadesRequest.getResponsavelId() != null) {
      UserModel usuario = userRepository.findById(oportunidadesRequest.getResponsavelId())
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
      if (Objects.equals(usuario.getLoja().getIdRevenda(), oportunidadesRequest.getLojaId())) {
        oportunidadeModel.setResponsavelId(usuario);
      } else {
        throw new RuntimeException("Este usuario nao pertence a revenda: {}"
          + oportunidadesRequest.getLojaId());
      }
    }
    return oportunidadesRepository.save(oportunidadeModel);
  }

  public OportunidadeModel findById(final Long id) {
    Optional<OportunidadeModel> oportunidadeModel = oportunidadesRepository.findById(id);
    if (Objects.isNull(oportunidadeModel)) {
      throw new RuntimeException("Essa oportunidade não foi encontrada!");
    }
    return oportunidadeModel.get();
  }

  public List<OportunidadesResponse> findAll() {
    List<OportunidadeModel> oportunidades = oportunidadesRepository.findAll();
    List<OportunidadesResponse> oportunidadesResponseList = new ArrayList<>();

    for (OportunidadeModel oportunidade : oportunidades) {
      OportunidadesResponse oportunidadesResponse = new OportunidadesResponse();

      oportunidadesResponse.setIdOportunidade(oportunidade.getIdOportunidade());
      oportunidadesResponse.setCliente(oportunidade.getCliente());
      oportunidadesResponse.setStatus(oportunidade.getStatus());
      oportunidadesResponse.setDataAtribuicao(oportunidade.getDataAtribuicao());
      oportunidadesResponse.setDataConclusao(oportunidade.getDataConclusao());
      oportunidadesResponse.setMotivoConclusao(oportunidade.getMotivoConclusao());
      oportunidadesResponse.setLoja(oportunidade.getLoja());
      oportunidadesResponse.setVeiculo(oportunidade.getVeiculo());
      oportunidadesResponse.setResposavel(oportunidade.getResponsavelId());

      oportunidadesResponseList.add(oportunidadesResponse);
    }

    return oportunidadesResponseList;
  }

  public void deleteById(final Long id) {
    OportunidadeModel oportunidadeModel = new OportunidadeModel();
    if (Objects.isNull(oportunidadeModel)) {
      throw new RuntimeException("Sem dados para excluir");
    }
    oportunidadesRepository.getById(id);
  }

  //A cada 5 minutos o sistema distribui as oportunidades para os responsáveis com menos oportunidades
  @Scheduled(fixedRate = 300000)
  public void ordernarOportunidade() {
    OportunidadeModel oportunidadeModel = new OportunidadeModel();

    List<OportunidadeModel> oportunidadeSemResponsavel
      = oportunidadesRepository.oportunidadeSemResponsavel();

    //adicionando oportunidades sem responsável na fila
    for (OportunidadeModel element : oportunidadeSemResponsavel) {
      filaDeOportunidades.add(element);
    }

    //atribuindo oportunidade ao responsvel com menor quantidade de oportunidades
    if (!filaDeOportunidades.isEmpty()) {
      List<Object[]> responsaveisLista = oportunidadesRepository.findResponsavelComMenosOportunidades();

      if (!responsaveisLista.isEmpty() && responsaveisLista.get(0)[0] != null) {
        Object[] responsavelData = responsaveisLista.get(0);
        Integer idResponsavelInteger = (Integer) responsavelData[0];
        Long idResponsavel = idResponsavelInteger.longValue();

        Optional<UserModel> responsavelOptional = userRepository.findById(idResponsavel);

        if (responsavelOptional.isPresent()) {
          UserModel responsavel = responsavelOptional.get();
          OportunidadeModel oportunidade = filaDeOportunidades.peek();//recupera o valor sem remover

          if (oportunidade != null && responsavel != null) {
            oportunidade.setResponsavelId(responsavel);
            filaDeOportunidades.poll(); // remove o primeiro valor
            oportunidadesRepository.save(oportunidade);
          } else {
            log.info("A lista está vazia!");
          }
        } else {
          log.info("O responsável não foi encontrado");
        }
      } else {
        log.info("A lista de responsáveis está vazia, ou o responsável não foi encontrado!");
      }
    }


  }
}
