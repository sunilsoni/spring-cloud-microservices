package com.jci.mail;

import java.util.Map;

import com.jci.enums.MailEnum;

public class MailTemplateStorage {

    private Map<MailEnum, MailTemplate> mailTemplates;

    public void setMailTemplates(Map<MailEnum, MailTemplate> mailTemplates) {
        this.mailTemplates = mailTemplates;
    }

    public MailTemplate getMailTemplate(MailEnum messageType) {
        return mailTemplates.get(messageType);
    }

}
