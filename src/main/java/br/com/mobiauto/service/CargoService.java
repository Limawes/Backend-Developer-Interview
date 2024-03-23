package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.repository.CargoRepository;
import br.com.mobiauto.domain.request.CargoRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargoService {

  public final CargoRepository cargoRepository;

  public CargoService(CargoRepository cargoRepository) {
    this.cargoRepository = cargoRepository;
  }

  public CargoModel save(final CargoRequest cargoRequest, Long cargo_id){
    CargoModel cargoModel = new CargoModel();

    if(cargo_id != null){
      Optional<CargoModel> cargos = cargoRepository.findById(cargo_id);
      if(cargos.isEmpty()){
        throw new RuntimeException("Cargo não encontrado!");
      }
      cargoModel.setId(cargo_id);
    }

    cargoModel.setNome(cargoRequest.getNome());

    return cargoRepository.save(cargoModel);
  }
}
