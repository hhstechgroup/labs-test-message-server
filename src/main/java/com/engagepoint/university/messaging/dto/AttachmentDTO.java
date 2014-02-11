package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.entities.base.Base;

import java.util.Arrays;

public class AttachmentDTO{

    private Integer id;
    private String mimeType;
    private String name;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentDTO that = (AttachmentDTO) o;

        if (content.equals(that.content)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!mimeType.equals(that.mimeType)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttachmentDTO{" +
                "id=" + id +
                ", mimeType='" + mimeType + '\'' +
                ", name='" + name + '\'' +
                ", content=" + content +
                '}';
    }
}
