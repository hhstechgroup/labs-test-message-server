package com.engagepoint.university.messaging.services.paginator;

import com.engagepoint.university.messaging.dto.base.BaseDTO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class LazyDataModelInterface<T extends BaseDTO> extends LazyDataModel<T> {
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return null;
    }
}
