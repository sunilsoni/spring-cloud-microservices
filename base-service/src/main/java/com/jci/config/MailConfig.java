package com.jci.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.jci.enums.MailEnum;
import com.jci.mail.MailTemplate;
import com.jci.mail.MailTemplateStorage;



/**
 * The Class MailConfig.
 */
@Configuration 
@RefreshScope
public class MailConfig { // NO_UCD (unused code)
	
	/** The host. */
	@Value("${email.host}")
    private String host;

    /** The port. */
    @Value("${email.port}")
    private Integer port;
    
    /** The username. */
    @Value("${email.username}")
    private String username;
    
    /** The password. */
    @Value("${email.password}")
    private String password;
    
    /** The node name. */
    @Value("${node.name:}")
    private String nodeName;

    /**
     * Java mail service.
     *
     * @return the java mail sender
     */
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    /**
     * Gets the mail properties.
     *
     * @return the mail properties
     */
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
	    properties.put("mail.smtp.auth", true);
	    properties.put("mail.smtp.starttls.enable", true);
	    properties.put("mail.debug", true);
	    
        return properties;
    }
    
    /**
     * Task executor adapter.
     *
     * @return the task executor adapter
     */
    @Bean
    public TaskExecutorAdapter taskExecutorAdapter() {
        return new TaskExecutorAdapter(Executors.newCachedThreadPool());
    }
    
    /**
     * Email template resolver.
     *
     * @return the class loader template resolver
     */
    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver(){
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/mails/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }
    
    /**
     * Gets the mail template.
     *
     * @return the mail template
     */
    @Bean
    public MailTemplateStorage getMailTemplate() {    	
    	Map<MailEnum, MailTemplate> mailTemplates  = new HashMap<>();
    	
    	MailTemplate template = new MailTemplate();
    	
        Map<String, String> subjectMap  = new HashMap<>();
        Map<String, String> textMap  = new HashMap<>();
        
        subjectMap.put("en", "Error on the node %s");
        textMap.put("en", "<p><strong>At node %s the following error occurred:</strong></p><p>%s</p>");
        
    	template.setSubject(subjectMap);
    	template.setText(textMap);
    	
    	mailTemplates.put(MailEnum.ERROR, template);
    	
    	//mailTemplates.put(MailEnum.ERROR, errorMailTemplate());
    	MailTemplateStorage storage = new MailTemplateStorage();
    	storage.setMailTemplates(mailTemplates);
    	return storage;
    }
    
}
