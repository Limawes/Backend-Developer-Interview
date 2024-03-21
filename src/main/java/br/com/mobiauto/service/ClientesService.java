package br.com.mobiauto.service;

import br.com.mobiauto.domain.model.ClientesModel;
import br.com.mobiauto.domain.repository.ClientesRepository;
import br.com.mobiauto.domain.request.ClientesRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void save(final ClientesRequest clientesRequest, final Long cliente_id){
    ClientesModel clienteModel = new ClientesModel();

    // verificar se cria ou atualiza
      if(cliente_id != null){
        Optional<ClientesModel> cliente = clientesRepository.findById(cliente_id);
        if(cliente.isEmpty()) {
          throw new RuntimeException("Não encontrado");
        }
        clienteModel.setId(cliente_id);
      }

      clienteModel.setNome(clientesRequest.getNome());
      clienteModel.setEmail(clientesRequest.getEmail());
      clienteModel.setTelefone(clientesRequest.getTelefone());

      clientesRepository.save(clienteModel);
  }

  public ClientesModel findById(final Long id){
    Optional<ClientesModel> clientesModel = clientesRepository.findById(id);
    if(clientesModel.isEmpty()){
      throw new RuntimeException("Nenhum cliente encontrado");
    }
    return clientesModel.get();
  }

  public List<ClientesModel> findALl(){
    ClientesModel clientesModel = new ClientesModel();
    if(Objects.isNull(clientesModel)){
      throw new RuntimeException("Nenhuma informacao encontrada!");
    }
    return clientesRepository.findAll();
  }

  public void deleteById(Long id){
    ClientesModel clientesModel = new ClientesModel();
    if(Objects.isNull(clientesModel)){
      throw new RuntimeException("Sem dados para excluir");
    }
    clientesRepository.deleteById(id);
  }
}
