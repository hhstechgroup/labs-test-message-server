package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entities.base.BaseEntity;

public interface ToEntityConverter<FROM_DTO extends BaseDTO, TO_ENTITY extends BaseEntity> {
    public TO_ENTITY convert(FROM_DTO from);
}
