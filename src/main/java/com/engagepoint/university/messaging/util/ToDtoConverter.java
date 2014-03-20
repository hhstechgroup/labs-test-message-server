package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entity.base.BaseEntity;

public interface ToDtoConverter<E extends BaseEntity, T extends BaseDTO> {
    public T convert(E from);
}
