package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.VeiculosModel;
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
  public VeiculosModel save(final VeiculosRequest veiculosRequest, Long veiculos_id){
    VeiculosModel veiculosModel = new VeiculosModel();

    if(veiculos_id != null){
      Optional<VeiculosModel> veiculo = veiculoRepository.findById(veiculos_id);
      if(veiculo.isEmpty()){
        throw new RuntimeException("Veículo não encontrado");
      }
    }
    veiculosModel.setId(veiculos_id);

    veiculosModel.setMarca(veiculosRequest.getMarca());
    veiculosModel.setModelo(veiculosRequest.getModelo());
    veiculosModel.setVersao(veiculosRequest.getVersao());
    veiculosModel.setAnoModelo(veiculosRequest.getAnoModelo());


    return veiculosModel;
  }
}
