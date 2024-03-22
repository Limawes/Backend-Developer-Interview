package br.com.mobiauto.service;

import br.com.mobiauto.domain.repository.RevendaRepository;

public class RevendaService {

  private final RevendaRepository revendaRepository;


  public RevendaService(RevendaRepository revendaRepository) {
    this.revendaRepository = revendaRepository;
  }


}
