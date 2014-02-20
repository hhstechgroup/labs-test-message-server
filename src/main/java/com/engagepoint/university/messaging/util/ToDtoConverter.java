package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entities.base.BaseEntity;

public interface ToDtoConverter<FROM_ENTITY extends BaseEntity, TO_DTO extends BaseDTO> {
    public TO_DTO convert(FROM_ENTITY from);
}
