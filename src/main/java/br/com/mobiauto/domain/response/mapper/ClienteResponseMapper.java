package br.com.mobiauto.domain.response.mapper;

import br.com.mobiauto.domain.model.ClienteModel;
import br.com.mobiauto.domain.response.ClientesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteResponseMapper {

  ClienteResponseMapper INSTANCE = Mappers.getMapper(ClienteResponseMapper.class);
  ClientesResponse modelToResponse(ClienteModel model);
}
