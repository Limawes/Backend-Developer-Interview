package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.VeiculoModel;
import br.com.mobiauto.domain.repository.VeiculoRepository;
import br.com.mobiauto.domain.request.VeiculosRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VeiculosService {

  private final VeiculoRepository veiculoRepository;

  public VeiculosService(VeiculoRepository veiculoRepository) {
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
    veiculoModel.setId(veiculos_id);

    veiculoModel.setMarca(veiculosRequest.getMarca());
    veiculoModel.setModelo(veiculosRequest.getModelo());
    veiculoModel.setVersao(veiculosRequest.getVersao());
    veiculoModel.setAnoModelo(veiculosRequest.getAnoModelo());


    return veiculoModel;
  }
}
