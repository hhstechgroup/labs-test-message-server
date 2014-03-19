package com.engagepoint.university.messaging.controller.LazyDataModel.impl;

import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.controller.LazyDataModel.LazySorter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class LazyEmailDTODataModel extends LazyDataModel<EmailDTO> implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(LazyEmailDTODataModel.class);
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
        performFilter(filters, data);

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
                LOG.info(e.getMessage(), e);
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

    public void performFilter(Map<String, String> filters, List<EmailDTO> data) {
        for(EmailDTO emailDTO : datasource) {
            boolean match = true;

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                try {
                    String filterProperty = entry.getKey();
                    String filterValue = entry.getValue();
                    String fieldValue;

                    if("id".equals(filterProperty)){
                        fieldValue = String.valueOf(emailDTO.getId());
                    } else {

                        Field field = emailDTO.getClass().getDeclaredField(filterProperty);
                        field.setAccessible(true);
                        fieldValue = String.valueOf(field.get(emailDTO));
                    }

                    if(filterValue == null || fieldValue.matches("(.*)" + filterValue + "(.*)")) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    LOG.info(e.getMessage(), e);
                    match = false;
                }
            }

            if(match) {
                data.add(emailDTO);
            }
        }
    }
}
