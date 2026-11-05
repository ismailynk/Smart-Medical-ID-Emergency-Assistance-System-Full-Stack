package com.firat.ozel.hasta.servis.domain.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapperUtil<DTO extends Serializable, T extends Serializable> implements Serializable {

    private Class<DTO> dtoEntityClass;
    private Class<T> entityClass;

    public MapperUtil(Class<DTO> dtoEntityClass, Class<T> entityClass) {
        this.dtoEntityClass = dtoEntityClass;
        this.entityClass = entityClass;
    }

    public T getEntity(DTO dtoEntity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return (T) modelMapper.map(dtoEntity, entityClass);
    }

    public DTO getDTO(T entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Object tmpSource = entity;
        if (entity == null) {
            tmpSource = new Object();
        }
        return (DTO) modelMapper.map(tmpSource, dtoEntityClass);
    }

    public List<DTO> getDTOList(List<T> entityList) {
        List<DTO> dtoEntityList = new ArrayList<>();
        for (T e : entityList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            //modelMapper.getConfiguration().setAmbiguityIgnored(true);
            dtoEntityList.add(modelMapper.map(e, dtoEntityClass));
        }
        return (List<DTO>) dtoEntityList;
    }
}
