package com.crud_example.mapper;
import com.crud_example.entity.BaseEntityAudit;
import com.crud_example.entity.DTOEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <S extends BaseEntityAudit, D extends DTOEntity> D convertToDto(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public <S extends BaseEntityAudit, D extends DTOEntity> D convertToDtoWithStandardStrategy(S source, Class<D> destinationClass) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        D destination = modelMapper.map(source, destinationClass);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return destination;
    }

    public <T extends BaseEntityAudit> T convertToEntity(DTOEntity dto, T entity) {
        return (T) modelMapper.map(dto, entity.getClass());
    }

    public <T extends BaseEntityAudit> void updateToEntity(DTOEntity dto, T entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(dto, entity);
    }

    public <T extends BaseEntityAudit> List<T> mapListOfEntityToDTO(final List<T> entityList, Type listType) {
        return modelMapper.map(entityList, listType);
    }

    public <T extends BaseEntityAudit, D extends DTOEntity> List<D> mapListOfEntityToDTOWithStandardStrategy(List<T> entityList, TypeToken<List<D>> listType) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        List<D> responseList = modelMapper.map(entityList, listType.getType());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return responseList;
    }

    public <S extends DTOEntity, D extends BaseEntityAudit> D convertToEntityWithStandardStrategy(S source, Class<D> destinationClass) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        D destination = modelMapper.map(source, destinationClass);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return destination;
    }

    public <T extends BaseEntityAudit> void updateToEntityWithFullTypeMatchingRequired(DTOEntity dto, T entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        modelMapper.map(dto, entity);
        modelMapper.getConfiguration().setFullTypeMatchingRequired(false);
    }

    public <S extends BaseEntityAudit, D extends DTOEntity> D convertToDotWithStandardStrategy(S source,
                                                                                               Class<D> destinationClass) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        D destination = modelMapper.map(source, destinationClass);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return destination;
    }

}


