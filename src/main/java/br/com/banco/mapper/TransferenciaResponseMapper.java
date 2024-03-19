package br.com.banco.mapper;

import br.com.banco.model.TransferenciaModel;
import br.com.banco.response.TransferenciaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferenciaResponseMapper {
    TransferenciaResponseMapper INSTANCE = Mappers.getMapper(TransferenciaResponseMapper.class);

    TransferenciaResponse modelToResponse(TransferenciaModel model);
}
