package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.Arrays;

public class AttachmentDTO extends BaseDTO {

    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AttachmentDTO{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
