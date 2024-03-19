package br.com.banco.service;

import br.com.banco.model.ContaModel;
import br.com.banco.model.TransferenciaModel;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.response.TransferenciaResponse;
import br.com.banco.util.GeneralUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

  public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
  }

    public List<TransferenciaModel> findAll() {
        return transferenciaRepository.findAll();
    }

    public List<TransferenciaModel> findByDataTransferenciaBetween(Date dataInicio, Date dataFim, Long contaId) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim, contaId);
    }

    public List<TransferenciaModel> findByNomeOperador(String operador, Long contaId) {
      return transferenciaRepository.findByNomeOperadorTransacao(operador, contaId);
    }

   public Optional<TransferenciaModel> findByIdTransferencias(Long id, Long contaId){
    return transferenciaRepository.findByIdTransferencias(id, contaId);
   }

    public List<TransferenciaModel> findByIdConta(Long id) {
      return transferenciaRepository.findByIdConta(id);
    }

  public List<TransferenciaModel> findByAllFilters(Long contaId, Long id, String operador, Date inicio, Date fim){
    return transferenciaRepository.findByAllFilters(contaId, id, operador, inicio, fim);
  }

}
