package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.repository.CargoRepository;
import br.com.mobiauto.domain.request.CargoRequest;
import br.com.mobiauto.domain.response.CargoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
      cargoModel.setIdCargo(cargo_id);
    }

    cargoModel.setNome(cargoRequest.getNome());

    return cargoRepository.save(cargoModel);
  }

  public CargoModel findById(final Long id){
    Optional<CargoModel> cargoModel = cargoRepository.findById(id);
    if(Objects.isNull(cargoModel)){
      throw new RuntimeException("Nenhum cargo encontrado");
    }
    return cargoModel.get();
  }

  public List<CargoResponse> findAll(){
    List<CargoModel> cargos = cargoRepository.findAll();
    List<CargoResponse> cargoResponseList = new ArrayList<>();

    for(CargoModel cargo : cargos){
      CargoResponse cargoResponse = new CargoResponse();
      cargoResponse.setIdCargo(cargo.getIdCargo());
      cargoResponse.setNome(cargo.getNome());

      cargoResponseList.add(cargoResponse);
    }

    return cargoResponseList;
  }

  public void deleteById(final Long id){
    CargoModel cargoModel = new CargoModel();
    if(Objects.isNull(cargoModel)){
      throw new RuntimeException("Sem dados para excluir");
    }
    cargoRepository.deleteById(id);
  }
}
