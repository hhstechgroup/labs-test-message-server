package com.engagepoint.university.messaging.controller.lazydatamodel.impl;

import com.engagepoint.university.messaging.controller.lazydatamodel.LazySorter;
import com.engagepoint.university.messaging.dto.JmsDTO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LazyJmsDTODataModel extends LazyDataModel<JmsDTO> implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(LazyJmsDTODataModel.class);

    private List<JmsDTO> datasource;

    public LazyJmsDTODataModel(List<JmsDTO> datasource) {
        this.datasource = datasource;
    }

    @Override
    public JmsDTO getRowData(String rowKey) {
        for (JmsDTO jms : datasource) {
            if (jms.getBody().equals(rowKey)){
                return jms;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(JmsDTO jms) {
        return jms.getBody();
    }

    @Override
    public List<JmsDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<JmsDTO> data = new ArrayList<JmsDTO>();
        //filter
        performFilter(filters, data);
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
        performRowCount(data);
        return paginatedUserList(first, pageSize, data);
    }

    public List<JmsDTO> paginatedUserList(int first, int pageSize, List<JmsDTO> data) {
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

    public void performRowCount(List<JmsDTO> data) {
        int dataSize = data.size();
        this.setRowCount(dataSize);
    }

    public void performFilter(Map<String, String> filters, List<JmsDTO> data) {
        for (JmsDTO jmsDTO : datasource) {
            boolean match = true;

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                try {
                    String filterProperty = entry.getKey();
                    String filterValue = entry.getValue();
                    String fieldValue;

                    if("id".equals(filterProperty)){
                        fieldValue = String.valueOf(jmsDTO.getId());
                    } else {

                        Field field = jmsDTO.getClass().getDeclaredField(filterProperty);
                        field.setAccessible(true);
                        fieldValue = String.valueOf(field.get(jmsDTO));
                    }

                    if (filterValue == null || fieldValue.matches("(.*)" + filterValue + "(.*)")) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    LOG.info(e.getMessage(), e);
                    match = false;
                }
            }

            if (match) {
                data.add(jmsDTO);
            }
        }
    }
}
