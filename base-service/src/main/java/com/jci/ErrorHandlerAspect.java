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
/**
 * The Class ErrorHandlerAspect.
 */
@Aspect
@Service
public class ErrorHandlerAspect { // NO_UCD (unused code)

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlerAspect.class);
    
    
    /** The mail templates. */
    Map<MailEnum, MailTemplate> mailTemplates;

    /** The journal service. */
    @Autowired
    private JournalService journalService;

    /** The mail service. */
    @Autowired
    private MailService mailService;

    /** The mail template storage. */
    @Autowired
    private MailTemplateStorage mailTemplateStorage;

    /** The err journalables. */
    @Value("${error.journalables:}")
    private String errJournalables;

    /** The err mailables. */
    @Value("${error.mailables:}")
    private String errMailables;

    /** The err emails. */
    @Value("${error.emails:}")
    private String errEmails;

    /** The node name. */
    @Value("${node.name:}")
    private String nodeName;

    /** The journalables. */
    private Set<String> journalables;

    /** The mailables. */
    private Set<String> mailables;

    /** The emails. */
    private String[] emails;

    /**
     * Inits the.
     *
     * @throws ClassNotFoundException the class not found exception
     */
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

    /**
     * In service layer.
     */
    @Pointcut("execution(* com.jci..*(..))")//Sunil: Check this
    public void inServiceLayer() {
    	LOG.info("#### Starting Ending ErrorHandlerAspect inServiceLayer #####");
    }

    /**
     * Do after throwing.
     *
     * @param joinPoint the join point
     * @param e the e
     */
    @AfterThrowing(pointcut = "inServiceLayer()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    	 LOG.info("#### Starting ErrorHandlerAspect doAfterThrowing #####");
        LOG.error("An exception " + e + " has been thrown in " + joinPoint.getSignature().getName() + "()", e);

        if (e instanceof Journalable || (journalables != null && journalables.contains(e.getClass().getName()))) {
            try {
                journalService.addError(e);
                LOG.debug("Add exception " + e + " to journal");
            } catch (Exception e1) {
                LOG.error("Could not write exception to journal", e1);
            }
        }
        
        if (e instanceof Mailable || (mailables != null && mailables.contains(e.getClass().getName()))) {
            try {
                String stackTrace = StringUtils.join(ExceptionUtils.getStackFrames(e), "<br />");
                MailTemplate mailTemplate = mailTemplateStorage.getMailTemplate(MailEnum.ERROR);
                //LOG.info(" mailTemplate---"+mailTemplate);
                String subject = String.format(mailTemplate.getSubject(LanguageUtils.getEnglishLocale()), nodeName);
                String text = String.format(mailTemplate.getText(LanguageUtils.getEnglishLocale()), nodeName, stackTrace);
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