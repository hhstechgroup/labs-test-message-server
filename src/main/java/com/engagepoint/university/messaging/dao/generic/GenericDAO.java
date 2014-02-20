package com.engagepoint.university.messaging.dao.generic;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.List;

public interface GenericDAO<DTOType extends BaseDTO> {

    public DTOType getById(Long id);

    public List<DTOType> getAll();

    public void save(DTOType DTOType);

    public void update(DTOType DTOType);

    public void delete(Integer id);

    public void delete(DTOType DTOType);
}
