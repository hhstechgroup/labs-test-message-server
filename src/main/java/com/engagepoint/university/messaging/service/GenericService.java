package com.engagepoint.university.messaging.service;


import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.List;

public interface GenericService<DTOType extends BaseDTO> {
    public DTOType getById(Long id);
    public List<DTOType> getAll();
    public void save(DTOType dtoType);
    public void update(DTOType dtoType);
    public void delete(Long id);
    public void delete(DTOType dtoType);
}
