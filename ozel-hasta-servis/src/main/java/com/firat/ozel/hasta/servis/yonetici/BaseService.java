package com.firat.ozel.hasta.servis.yonetici;

import com.firat.ozel.hasta.servis.domain.mapper.MapperUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public abstract class BaseService<DTO extends Serializable, T extends Serializable> implements KayitImplements<DTO> {
    private JpaRepository<T, UUID> repository;
    private MapperUtil<DTO, T> modelMapper;

    protected BaseService(JpaRepository<T, UUID> repository, Class<DTO> dtoClass, Class<T> entityClass) {
        this.repository = repository;
        modelMapper = new MapperUtil<>(dtoClass, entityClass);
    }

    public MapperUtil<DTO, T> getModelMapper() {
        return this.modelMapper;
    }

    @Override
    public DTO guncelle(DTO dtoEntity) {
        return modelMapper.getDTO(repository.save(modelMapper.getEntity(dtoEntity)));
    }

    @Override
    public DTO kaydet(DTO dtoEntity) {
        return modelMapper.getDTO(repository.save(modelMapper.getEntity(dtoEntity)));
    }

    @Override
    public List<DTO> tumuListele() {
        return modelMapper.getDTOList(repository.findAll());
    }

    @Override
    public DTO getir(UUID id) {
        return modelMapper.getDTO(repository.findById(id).get());
    }

    @Override
    public Optional<DTO> getirById(UUID id) {
        return Optional.ofNullable(modelMapper.getDTO(repository.findById(id).get()));
    }

    @Override
    public boolean sil(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean tumunuSil(List<UUID> ids) {
        for (UUID id : ids) {
            sil(id);
        }
        return true;
    }
}
