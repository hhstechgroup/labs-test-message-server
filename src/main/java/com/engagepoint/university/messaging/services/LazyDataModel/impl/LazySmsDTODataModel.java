package com.engagepoint.university.messaging.services.LazyDataModel.impl;

import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.services.LazyDataModel.LazySorter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.*;

public class LazySmsDTODataModel extends LazyDataModel<SmsDTO> implements Serializable {
    private List<SmsDTO> datasource;

    public LazySmsDTODataModel(List<SmsDTO> datasource) {
        this.datasource = datasource;
    }

    @Override
    public SmsDTO getRowData(String rowKey) {
        for (SmsDTO mail : datasource) {
            if (mail.getSender().equals(rowKey))
                return mail;
        }

        return null;
    }

    @Override
    public Object getRowKey(SmsDTO mail) {
        return mail.getSender();
    }

    @Override
    public List<SmsDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, String> filters){
        List<SmsDTO> data = new ArrayList<SmsDTO>();
        performFilter(filters, data);

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        performRowCount(data);

        return paginatedUserList(first, pageSize, data);
    }

    public List<SmsDTO> paginatedUserList(int first, int pageSize, List<SmsDTO> data) {
        int dataSize = data.size();
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

    public void performRowCount(List<SmsDTO> data) {
        int dataSize = data.size();
        this.setRowCount(dataSize);
    }

    public void performFilter(Map<String, String> filters, List<SmsDTO> data) {
        for (SmsDTO mail : datasource) {
            boolean match = true;

            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(mail.getClass().getField(filterProperty).get(mail));

                    if (filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    match = false;
                }
            }

            if (match) {
                data.add(mail);
            }
        }
    }
}
