package com.jci.service;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jci.enums.MailSenderEnum;
import com.jci.mail.Mail;
import com.jci.mail.MailService;



/**
 * The Class MailServiceImpl.
 */
@Service
public class MailServiceImpl implements MailService { // NO_UCD (unused code)

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
    
    /** The Constant NO_DATA_WAIT_TIME. */
    private static final long NO_DATA_WAIT_TIME = 5000L;


  /*  @Autowired
    private MailRepository mailRepository;*/

    /** The mail sender. */
    @Autowired
    private JavaMailSender mailSender;

    /** The mail queue. */
    private ConcurrentLinkedQueue<Mail> mailQueue = new ConcurrentLinkedQueue<>();

    /** The is active. */
    private AtomicBoolean isActive = new AtomicBoolean();

    /** The is send mail. */
/*    @Value("${mail.send:true}")
    private boolean isSendMail;*/
    
    @Value("${mail.send:}")
    private boolean isSendMail;

    /** The node name. */
    @Value("${node.name:}")
    private String nodeName;
    

    /** The task executor adapter. */
    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter;

    /**
     * Start.
     */
    @PostConstruct
    private void start() {
    	LOG.info("#### Starting MailServiceImpl.start initialization ... #####");
    	LOG.info("send mail: {}", isSendMail);
        if (isSendMail) {
        	//Sunil: mailQueue.addAll(mailRepository.findByNode(nodeName));
            isActive.set(true);
            taskExecutorAdapter.submit(new MailSender());
        }
        LOG.info("MailServices successfully initialized");
        LOG.info("#### Ending MailServiceImpl.start  #####");
    }

    /**
     * Stop.
     */
    @PreDestroy
    private void stop() {
        if (isSendMail) {
            isActive.set(false);
        }
    }

    /* (non-Javadoc)
     * @see com.jci.mail.MailService#sendNoreplyMessage(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void sendNoreplyMessage(String to, String subject, String text) {
    	LOG.info("#### Starting MailServiceImpl.sendNoreplyMessage  #####");
        Assert.notNull(to, "destination address must not be null");
        Assert.notNull(subject, "subject must not be null");
        Assert.notNull(text, "text must not be null");

        sendNoreplyMessage(new String[]{to}, subject, text);
        LOG.info("#### Ending MailServiceImpl.sendNoreplyMessage  #####");
    }

    /* (non-Javadoc)
     * @see com.jci.mail.MailService#sendNoreplyMessage(java.lang.String[], java.lang.String, java.lang.String)
     */
    @Override
    public void sendNoreplyMessage(String[] to, String subject, String text) {
    	LOG.info("#### Starting MailServiceImpl.sendNoreplyMessage  #####");
        Assert.notNull(to, "destination addresses must not be null");
        Assert.notNull(subject, "subject must not be null");
        Assert.notNull(text, "text must not be null");

        if (!isSendMail) {
            LOG.debug("ignore message because isSendMail=false");
        }

        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);
        mail.setCreated(new Date());
        mail.setNode(nodeName);

      //Sunil: mail = mailRepository.save(mail);
        mailQueue.add(mail);
        LOG.info("#### Ending MailServiceImpl.sendNoreplyMessage  #####");
    }

    /**
     * Send no reply mail.
     *
     * @param mail the mail
     * @throws MessagingException the messaging exception
     */
    public void sendNoReplyMail(Mail mail) throws MessagingException {
    	LOG.info("#### Starting MailServiceImpl.sendNoReplyMail  #####");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(MailSenderEnum.NO_REPLY.getAddress());
        helper.setTo(mail.getTo());
        message.setSubject(mail.getSubject(), "UTF-8");
        message.setContent(mail.getText(), "text/html; charset=utf-8");
        mailSender.send(message);
        LOG.info("#### Ending MailServiceImpl.sendNoReplyMail  #####");
    }

    /**
     * The Class MailSender.
     */
    private class MailSender implements Runnable {
    	
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            try {
                LOG.info("----Thread for send emails is successfully launched----");

                while (isActive.get()) {
                    try {
                        if (mailQueue.isEmpty()) {
                            Thread.sleep(NO_DATA_WAIT_TIME);
                        } else {
                            Mail mail = mailQueue.peek();
                            if (mail != null) {
                                sendNoReplyMail(mail);
                                LOG.debug("send mail: {}", mail);
                               //Sunil: mailRepository.remove(mail);
                                mailQueue.remove();
                            }
                        }
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        try {
                            Thread.sleep(NO_DATA_WAIT_TIME);
                        } catch (InterruptedException e1) {
                            LOG.error(e1.getMessage(), e1);
                        }
                    }
                }

            } finally {
                LOG.info("---Thread for send emails is successfully stopped---");
            }
        }
    }

}

