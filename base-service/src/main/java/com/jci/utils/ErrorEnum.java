package com.jci.utils;

/**
 * Error list from:
 * <p/>
 * errors_en.properties
 */
public enum ErrorEnum {

    UNAUTHORIZED("err.unauthorized"),
    LOGIN_FAILURE("err.login.failure"),
    
    //Table not found in Azure 
    ERROR_TABLE_NOT_FOUND("error.table.not.found"),
    ERROR_PO_TABLE_NOT_FOUND("error.po.table.not.found"),
    ERROR_POITEM_TABLE_NOT_FOUND("error.po.item.table.not.found"),
    ERROR_GR_TABLE_NOT_FOUND("error.gr.table.not.found"),
    ERROR_ITEM_TABLE_NOT_FOUND("error.item.table.not.found"),
    ERROR_SUPP_TABLE_NOT_FOUND("error.supp.table.not.found"),
    ERROR_MISCDATA_TABLE_NOT_FOUND("error.supp.table.not.found"),
    
    //Records not found in Azure storage table
    ERROR_PO_DATA_NOT_FOUND("error.po.not.found"),
    ERROR_GR_DATA_NOT_FOUND("error.gr.not.found"),
    ERROR_ITEM_DATA_NOT_FOUND("error.item.not.found"),
    ERROR_SUPP_DATA_NOT_FOUND("error.supp.not.found"),
    ERROR_FLATFILE_DATA_NOT_FOUND("error.flatfile.data.not.found"),
    
    //Exception while inserting data in Table
    ERROR_PODETAILS_INSERT_DATA("error.po.table.not.found"),
    ERROR_GRDETAILS_INSERT_DATA("error.gr.table.not.found"),
    ERROR_SUPPLIER_INSERT_DATA("error.item.table.not.found"),
    ERROR_ITEM_INSERT_DATA("error.supp.table.not.found"),
    ERROR_MISCDATA_INSERT_DATA("error.po.table.not.found"),
    
    //Exception while updating Table data
    ERROR_PODETAILS_UPDATE_DATA("error.po.table.not.found"),
    ERROR_GRDETAILS_UPDATE_DATA("error.gr.table.not.found"),
    ERROR_SUPPLIER_UPDATE_DATA("error.item.table.not.found"),
    ERROR_ITEM_UPDATE_DATA("error.supp.table.not.found"),
    ERROR_MISCDATA_UPDATE_DATA("error.po.table.not.found"),
    
    //Apigee GET request errors
    ERROR_APIGEE_PO_GET("error.supp.table.not.found"),
    ERROR_APIGEE_GR_GET("error.supp.table.not.found"),
    ERROR_APIGEE_ITEM_GET("error.supp.table.not.found"),
    ERROR_APIGEE_SUPP_GET("error.supp.table.not.found"),

    //Apigee PUT request errors    
    ERROR_APIGEE_PO_PUT("error.supp.table.not.found"),
    ERROR_APIGEE_GR_PUT("error.supp.table.not.found"),
    ERROR_APIGEE_ITEM_PUT("error.supp.table.not.found"),
    ERROR_APIGEE_SUPP_PUT("error.supp.table.not.found"),

    //Error in flatfile prepration
    ERROR_GENERATE_FLATFILE_PO("error.supp.table.not.found"),
    ERROR_GENERATE_FLATFILE_GR("error.supp.table.not.found"), 
    ERROR_GENERATE_FLATFILE_ITEM("error.supp.table.not.found"), 
    ERROR_GENERATE_FLATFILE_SUPP("error.supp.table.not.found"), 
    
    //error in flatfile processing
    ERROR_PROCESS_FLATFILE_PO("error.supp.table.not.found"),
    ERROR_PROCESS_FLATFILE_GR("error.supp.table.not.found"), 
    ERROR_PROCESS_FLATFILE_ITEM("error.supp.table.not.found"), 
    ERROR_PROCESS_FLATFILE_SUPP("error.supp.table.not.found"), 
    
    //error in flatfile processing
    ERROR_READING_CONFIG_PO_FILE("error.reading.config.po.file"),
    ERROR_READING_CONFIG_GR_FILE("error.reading.config.gr.file"),
    ERROR_READING_CONFIG_ITEM_FILE("error.reading.config.item.file"),
    ERROR_READING_CONFIG_SUPP_FILE("error.reading.config.supplier.file"),
    
    
    //UI  errors
    ERROR_USER_NOT_FOUND("error.user.not.found"),
    ERROR_USER_NOT_FOUND_BY_EMAIL("error.user.not.found.by.email"),
    ERROR_USER_NOT_ACTIVE("error.user.not.active"),
    ERROR_USER_CURRENT_PSWD_WRONG("error.user.current.pswd.wrong"),
    
    
    ERROR_USER_PSWD_VALUE_IS_WRONG("error.user.pswd.value.is.wrong"),
    ERROR_USER_EMAIL_IS_NOT_CORRECT("error.user.email.is.not.correct"),
    ERROR_USER_PSWD_IS_NOT_CORRECT("error.user.pswd.is.not.correct"),
    ERROR_MORE_THAN_ONE_USER_HAVE_ACTIVE_STATUS("error.more.than.one.user.have.status.active"),
    
    
    ERROR_USER_HAVE_ACTIVE_CONTENT("error.user.have.active.content"),
    ERROR_USER_RESET_PASSWORD("error.user.reset.password"),
    ERROR_USER_CHECK_PASSWORD("error.user.check.password"),
    ERROR_USER_CHANGE_PASSWORD("error.user.change.password"),

    ERR_SYSTEM_CREATE("err.system.create.exception"),
    ERR_SYSTEM_DB("err.system.bd"),
    ERR_SYSTEM_SOLR("err.system.solr"),
    ERR_SYSTEM_REDIS("err.system.redis"),
    ERR_SYSTEM_SEND_MAIL("err.system.send.mail"),

    ERROR_RATING_CALCULATION("error.rating.calculation");

    public String KEY;

    ErrorEnum(String KEY) {
        this.KEY = KEY;
    }
}
