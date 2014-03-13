package com.engagepoint.university.messaging.services.LazyDataModel.impl;

import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.services.LazyDataModel.LazySorter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.*;

public class LazyEmailDTODataModel extends LazyDataModel<EmailDTO> implements Serializable {
    private List<EmailDTO> datasource;

    public LazyEmailDTODataModel(List<EmailDTO> datasource) {
        this.datasource = datasource;
    }

    @Override
    public EmailDTO getRowData(String rowKey) {
        for (EmailDTO mail : datasource) {
            if (mail.getSender().equals(rowKey))
                return mail;
        }

        return null;
    }

    @Override
    public Object getRowKey(EmailDTO mail) {
        return mail.getSender();
    }

    @Override
    public List<EmailDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<EmailDTO> data = new ArrayList<EmailDTO>();

        //filter
        for(EmailDTO emailDTO : datasource) {
            boolean match = true;

            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(emailDTO.getClass().getField(filterProperty).get(emailDTO));

                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                }
            }

            if(match) {
                data.add(emailDTO);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        performRowCount(data);

        return paginatedUserList(first, pageSize, data);
    }

    public List<EmailDTO> paginatedUserList(int first, int pageSize, List<EmailDTO> data) {
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

    public void performRowCount(List<EmailDTO> data) {
        int dataSize = data.size();
        this.setRowCount(dataSize);
    }

}
