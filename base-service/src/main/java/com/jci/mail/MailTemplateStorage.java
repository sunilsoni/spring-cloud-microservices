package com.jci.mail;

import java.util.Map;

import com.jci.enums.MailEnum;



/**
 * The Class MailTemplateStorage.
 */
public class MailTemplateStorage {

    /** The mail templates. */
    private Map<MailEnum, MailTemplate> mailTemplates;

    /**
     * Sets the mail templates.
     *
     * @param mailTemplates the mail templates
     */
    public void setMailTemplates(Map<MailEnum, MailTemplate> mailTemplates) {
        this.mailTemplates = mailTemplates;
    }

    /**
     * Gets the mail template.
     *
     * @param messageType the message type
     * @return the mail template
     */
    public MailTemplate getMailTemplate(MailEnum messageType) {
        return mailTemplates.get(messageType);
    }

}
