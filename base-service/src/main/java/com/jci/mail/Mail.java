package com.jci.mail;

import org.apache.commons.lang.ArrayUtils;

import java.util.Date;



/**
 * Message queue to send.
 */
public class Mail extends BaseObject {

    /** The to. */
    private String[] to;

    /** The subject. */
    private String subject;

    /** The text. */
    private String text;

    /** The node. */
    private String node;

    /** The created. */
    private Date created;

    /* (non-Javadoc)
     * @see com.jci.mail.BaseObject#toString()
     */
    @Override
    public String toString() {
        return String.format("%s [id=%s, node=%s, created=%s, subject=%s, to=%s, text=%s]",
                getClass().getName(), id, node, created, subject, ArrayUtils.toString(to), text);
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public String[] getTo() {
        return to;
    }

    /**
     * Sets the to.
     *
     * @param to the new to
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the node.
     *
     * @return the node
     */
    public String getNode() {
        return node;
    }

    /**
     * Sets the node.
     *
     * @param node the new node
     */
    public void setNode(String node) {
        this.node = node;
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the created.
     *
     * @param created the new created
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
