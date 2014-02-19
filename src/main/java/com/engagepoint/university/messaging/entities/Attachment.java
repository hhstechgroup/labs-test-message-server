package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "attachment")
@NamedQueries({
        @NamedQuery(name = Attachment.GET_ALL_BY_ATTACHMENT_ID, query = "SELECT at FROM Attachment at WHERE at.id = :idAttachment"),
        @NamedQuery(name = Attachment.GET_ALL_BY_NAME, query = "SELECT at FROM Attachment at WHERE at.name = :name"),
        @NamedQuery(name = Attachment.GET_ALL_BY_CONTENT, query = "SELECT at FROM Attachment at WHERE at.content = :content"),
        @NamedQuery(name = Attachment.GET_ALL_ATTACHMENTS, query = "SELECT at FROM Attachment at")})

public class Attachment implements Serializable, BaseEntity {

    private static final long serialVersionUID = 765348739781231295L;
    public static final String GET_ALL_BY_ATTACHMENT_ID = "Attachment.findByIdAttachment";
    public static final String GET_ALL_BY_MIME_TYPE = "Attachment.findByMimeType";
    public static final String GET_ALL_BY_NAME = "Attachment.findByName";
    public static final String GET_ALL_BY_CONTENT = "Attachment.findByContent";
    public static final String GET_ALL_ATTACHMENTS = "Attachment.getAttachmentByMessage";

    private Long id;
    private String name;
    private String content;
    private Collection<Email> emailCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Size(max = 14000000)
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToMany(mappedBy = "attachmentCollection", cascade = CascadeType.ALL)
    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }
}
