package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entity.base.BaseEntity;

public interface ToEntityConverter<T extends BaseDTO, E extends BaseEntity> {
    public E convert(T from);
}
