package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.ClienteModel;
import br.com.mobiauto.domain.repository.ClientesRepository;
import br.com.mobiauto.domain.request.ClientesRequest;
import br.com.mobiauto.domain.response.ClientesResponse;
import br.com.mobiauto.service.execeptions.NoDataFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientesService {

  private final ClientesRepository clientesRepository;

  public ClientesService(ClientesRepository clientesRepository) {
    this.clientesRepository = clientesRepository;
  }

  @Transactional
  //se nada der errado o método é confirmado, caso contrario as ações serão revertidas
  public ClienteModel save(final ClientesRequest clientesRequest, final Long clienteId){
    //@TODO Remover futuramente esse acoplamento do ClienteModel passando como parametro a conversão do Request para a Model
    ClienteModel clienteModel = new ClienteModel();

    // verificar se cria ou atualiza
      if(!Objects.isNull(clienteId)){
        Optional<ClienteModel> cliente = clientesRepository.findById(clienteId);
        if(cliente.isEmpty()) {
          throw new NoDataFoundException();
        }
        clienteModel.setIdCliente(clienteId);
      }

      clienteModel.setNome(clientesRequest.getNome());
      clienteModel.setEmail(clientesRequest.getEmail());
      clienteModel.setTelefone(clientesRequest.getTelefone());

      return clientesRepository.save(clienteModel);
  }

  public ClienteModel findById(final Long id){
    Optional<ClienteModel> clientesModel = clientesRepository.findById(id);
    if(clientesModel.isEmpty()){
      throw new NoDataFoundException("Cliente não encontrado");
    }
    return clientesModel.get();
  }

  public List<ClientesResponse> findAll(){
    List<ClienteModel> clientes = clientesRepository.findAll();
    List<ClientesResponse> clientesResponseList = new ArrayList<>();

    for(ClienteModel cliente : clientes){
      ClientesResponse clientesResponse = new ClientesResponse();

      clientesResponse.setIdCliente(cliente.getIdCliente());
      clientesResponse.setNome(cliente.getNome());
      clientesResponse.setTelefone(cliente.getTelefone());
      clientesResponse.setEmail(cliente.getEmail());

      clientesResponseList.add(clientesResponse);
    }

    return clientesResponseList;
  }

  public void deleteById(Long id){
    ClienteModel clienteModel = new ClienteModel();
    if(Objects.isNull(clienteModel)){
      throw new NoDataFoundException("Sem dados para excluir");
    }
    clientesRepository.deleteById(id);
  }
}
