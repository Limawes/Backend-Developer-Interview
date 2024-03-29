package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.ClienteModel;
import br.com.mobiauto.domain.model.RevendaModel;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.domain.request.RevendasRequest;
import br.com.mobiauto.domain.response.RevendaResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RevendaService {

  private final RevendaRepository revendaRepository;


  public RevendaService(RevendaRepository revendaRepository) {
    this.revendaRepository = revendaRepository;
  }

  @Transactional
  public RevendaModel save(final RevendasRequest revendasRequest, Long revendas_id){
    RevendaModel revendaModel = new RevendaModel();
    if(revendas_id != null) {
      Optional<RevendaModel> revendas = revendaRepository.findById(revendas_id);
      if (revendas.isEmpty()) {
        throw new RuntimeException("Loja não encontrada!");
      }
      revendaModel.setIdRevenda(revendasRequest.getIdRevenda());
    }
    //Aqui eu verifico se o cnpj já existe
    if(revendaRepository.existsByCnpj(revendasRequest.getCnpj())){
      throw new RuntimeException("Cnpj já existe para outra revenda!");
    }
    revendaModel.setCnpj(revendasRequest.getCnpj());
    revendaModel.setNomeSocial(revendasRequest.getNomeSocial());
    return revendaRepository.save(revendaModel);
  }

  public RevendaModel findById(final Long id){
    Optional<RevendaModel> revendaModel = revendaRepository.findById(id);
    if(revendaModel.isEmpty()){
      throw new RuntimeException("Loja não encontrada");
    }
    return revendaModel.get();
  }

  public List<RevendaResponse> findAll(){
    List<RevendaModel> revendas = revendaRepository.findAll();
    List<RevendaResponse> revendaResponseList = new ArrayList<>();

    for(RevendaModel revenda : revendas){
      RevendaResponse revendaResponse = new RevendaResponse();
        revendaResponse.setIdRevenda(revenda.getIdRevenda());
        revendaResponse.setCnpj(revenda.getCnpj());
        revendaResponse.setNomeSocial(revenda.getNomeSocial());

      revendaResponseList.add(revendaResponse);
    }

    return revendaResponseList;
  }

  public void deleteById(Long id){
    RevendaModel revendaModel = new RevendaModel();
    if(Objects.isNull(revendaModel)){
      throw new RuntimeException("Sem dados para exlcuir");
    }
    revendaRepository.deleteById(id);
  }
}
