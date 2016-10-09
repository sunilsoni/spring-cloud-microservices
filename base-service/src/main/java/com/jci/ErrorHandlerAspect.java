package com.jci;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jci.enums.MailEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.locale.LanguageUtils;
import com.jci.mail.JournalService;
import com.jci.mail.MailService;
import com.jci.mail.MailTemplate;
import com.jci.mail.MailTemplateStorage;

@Aspect
@Service
public class ErrorHandlerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlerAspect.class);
    
    
    Map<MailEnum, MailTemplate> mailTemplates;

    @Autowired
    private JournalService journalService;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailTemplateStorage mailTemplateStorage;

    @Value("${error.journalables:}")
    private String errJournalables;

    @Value("${error.mailables:}")
    private String errMailables;

    @Value("${error.emails:}")
    private String errEmails;

    @Value("${node.name:}")
    private String nodeName;

    private Set<String> journalables;

    private Set<String> mailables;

    private String[] emails;

    @PostConstruct
    public void init() throws ClassNotFoundException {
        LOG.info("#### Starting ErrorHandlerAspect initialization #####");

        // set journalables
        if (StringUtils.isNotBlank(errJournalables)) {
            String[] arr = StringUtils.split(errJournalables, ",");
            if (ArrayUtils.isNotEmpty(arr)) {
                journalables = new HashSet<>(arr.length);
                for (String errClassName : arr) {
                    journalables.add(StringUtils.trim(errClassName));
                }
            }
        }
        if (journalables == null) {
            LOG.info("journalable exceptions is not defined");
        } else {
            LOG.info("journalable exceptions: {}", ArrayUtils.toString(journalables.toArray()));
        }

        // set mailables
        if (StringUtils.isNotBlank(errMailables)) {
            String[] arr = StringUtils.split(errMailables, ",");
            if (ArrayUtils.isNotEmpty(arr)) {
                mailables = new HashSet<>(arr.length);
                for (String errClassName : arr) {
                    mailables.add(StringUtils.trim(errClassName));
                }
            }
        }
        if (mailables == null) {
            LOG.info("mailable exceptions is not defined");
        } else {
            LOG.info("mailable exceptions: {}", ArrayUtils.toString(mailables.toArray()));
        }

        // set emails
        if (StringUtils.isNotBlank(errEmails)) {
            emails = StringUtils.split(errEmails, ",");
        }
        if (ArrayUtils.isEmpty(emails)) {
            LOG.info("email addresses for send exceptions is not defined");
        } else {
            LOG.info("email addresses for send exceptions: {}", ArrayUtils.toString(emails));
        }

        LOG.info("#### Ending ErrorHandlerAspect initialization #####");
    }

    @Pointcut("execution(* com.jci..*(..))")//Sunil: Check this
    public void inServiceLayer() {
    	LOG.info("#### Starting Ending ErrorHandlerAspect inServiceLayer #####");
    }

    @AfterThrowing(pointcut = "inServiceLayer()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    	 LOG.info("#### Starting ErrorHandlerAspect doAfterThrowing #####");
        LOG.error("An exception " + e + " has been thrown in " + joinPoint.getSignature().getName() + "()", e);

        if (e instanceof Journalable || (journalables != null && journalables.contains(e.getClass().getName()))) {
        	 LOG.info(" Journalable---");
            try {
                journalService.addError(e);
                LOG.debug("Add exception " + e + " to journal");
            } catch (Exception e1) {
                LOG.error("Could not write exception to journal", e1);
            }
        }
        LOG.info(" getName---"+e.getClass().getName());
        LOG.info(" mailables---"+mailables);
        LOG.info(" instanceof---"+(e instanceof Mailable));
        
        if (e instanceof Mailable || (mailables != null && mailables.contains(e.getClass().getName()))) {
        	 LOG.info(" Mailable---");
            try {
                String stackTrace = StringUtils.join(ExceptionUtils.getStackFrames(e), "<br />");
                MailTemplate mailTemplate = mailTemplateStorage.getMailTemplate(MailEnum.ERROR);
                //LOG.info(" mailTemplate---"+mailTemplate);
                String subject = String.format(mailTemplate.getSubject(LanguageUtils.getEnglishLocale()), nodeName);
                String text = String.format(mailTemplate.getText(LanguageUtils.getEnglishLocale()), nodeName, stackTrace);
                mailService.sendNoreplyMessage(emails, subject, text);
                mailService.sendNoreplyMessage(emails, subject, text);
                LOG.debug("Send exception " + e + " by email");
            } catch (Exception e1) {
                LOG.debug("Could not send exception message by e-mail", e1);
                e1.printStackTrace();
            }
        }
        LOG.info("#### Ending ErrorHandlerAspect doAfterThrowing #####");
    }
}