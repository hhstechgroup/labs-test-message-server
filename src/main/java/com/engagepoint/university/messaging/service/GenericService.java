package com.engagepoint.university.messaging.service;


import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.List;

public interface GenericService<DTOType extends BaseDTO> {
    public DTOType getById(Long id);

    public List<DTOType> getAll();
    public void save(DTOType DTOType);
    public void update(DTOType DTOType);
    public void delete(Long id);
    public void delete(DTOType DTOType);
}
