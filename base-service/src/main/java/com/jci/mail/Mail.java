package com.jci.mail;

import org.apache.commons.lang.ArrayUtils;

import java.util.Date;

/**
 * Message queue to send
 */
public class Mail extends BaseObject {

    private String[] to;

    private String subject;

    private String text;

    private String node;

    private Date created;

    @Override
    public String toString() {
        return String.format("%s [id=%s, node=%s, created=%s, subject=%s, to=%s, text=%s]",
                getClass().getName(), id, node, created, subject, ArrayUtils.toString(to), text);
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
