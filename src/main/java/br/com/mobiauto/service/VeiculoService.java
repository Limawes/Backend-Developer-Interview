package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.VeiculoModel;
import br.com.mobiauto.domain.repository.VeiculoRepository;
import br.com.mobiauto.domain.request.VeiculosRequest;
import br.com.mobiauto.domain.response.VeiculoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoService {

  private final VeiculoRepository veiculoRepository;

  public VeiculoService(VeiculoRepository veiculoRepository) {
    this.veiculoRepository = veiculoRepository;
  }

  @Transactional
  public VeiculoModel save(final VeiculosRequest veiculosRequest, Long veiculos_id){
    VeiculoModel veiculoModel = new VeiculoModel();

    if(veiculos_id != null){
      Optional<VeiculoModel> veiculo = veiculoRepository.findById(veiculos_id);
      if(veiculo.isEmpty()){
        throw new RuntimeException("Veículo não encontrado");
      }
    }
    veiculoModel.setIdVeiculo(veiculos_id);

    veiculoModel.setMarca(veiculosRequest.getMarca());
    veiculoModel.setModelo(veiculosRequest.getModelo());
    veiculoModel.setVersao(veiculosRequest.getVersao());
    veiculoModel.setAnoModelo(veiculosRequest.getAnoModelo());


    return veiculoRepository.save(veiculoModel);
  }

  public VeiculoModel findById(final Long id){
    Optional<VeiculoModel> veiculoModel = veiculoRepository.findById(id);
    if(veiculoModel.isEmpty()){
      throw new RuntimeException("Veiculo não encontrado");
    }
    return veiculoModel.get();
  }

  public List<VeiculoResponse> findAll(){
    List<VeiculoModel> veiculos = veiculoRepository.findAll();
    List<VeiculoResponse> veiculoResponseList = new ArrayList<>();

    for(VeiculoModel veiculo : veiculos){
      VeiculoResponse veiculoResponse = new VeiculoResponse();
      veiculoResponse.setIdVeiculo(veiculo.getIdVeiculo());
      veiculoResponse.setMarca(veiculo.getMarca());
      veiculoResponse.setModelo(veiculo.getModelo());
      veiculoResponse.setVersao(veiculo.getVersao());
      veiculoResponse.setAnoModelo(veiculo.getAnoModelo());

      veiculoResponseList.add(veiculoResponse);
    }

    return veiculoResponseList;
  }

  public void deleteById(Long id){
    VeiculoModel veiculoModel = new VeiculoModel();
    if(Objects.isNull(veiculoModel)){
      throw new RuntimeException("Sem dados para exlcuir");
    }
    veiculoRepository.deleteById(id);
  }
}
