package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

public interface ToDtoConverter<FROM_ENTITY, TO_DTO extends BaseDTO> {
    public TO_DTO convert(FROM_ENTITY from);
}
