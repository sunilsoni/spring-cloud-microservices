package com.jci.enums;


/**
 * Error list from:
 * <p/>
 * errors_en.properties
 */
public enum ErrorEnum {

    /** The unauthorized. */
    UNAUTHORIZED("err.unauthorized"),
    
    /** The login failure. */
    LOGIN_FAILURE("err.login.failure"),
    
    ERROR_TABLE_CREATION("error.table.creation"),
    /** The error table not found. */
    //Table not found in Azure 
    ERROR_TABLE_NOT_FOUND("error.table.not.found"),
    
    /** The error po table not found. */
    ERROR_PO_TABLE_NOT_FOUND("error.po.table.not.found"),
    
    /** The error poitem table not found. */
    ERROR_POITEM_TABLE_NOT_FOUND("error.po.item.table.not.found"),
    
    /** The error gr table not found. */
    ERROR_GR_TABLE_NOT_FOUND("error.gr.table.not.found"),
    
    /** The error item table not found. */
    ERROR_ITEM_TABLE_NOT_FOUND("error.item.table.not.found"),
    
    /** The error supp table not found. */
    ERROR_SUPP_TABLE_NOT_FOUND("error.supp.table.not.found"),
    
    /** The error miscdata table not found. */
    ERROR_MISCDATA_TABLE_NOT_FOUND("error.supp.table.not.found"),
    
    /** The error po data not found. */
    //Records not found in Azure storage table
    ERROR_PO_ENTITY_NOT_FOUND("error.po.not.found"),
    
    /** The error gr data not found. */
    ERROR_GR_ENTITY_NOT_FOUND("error.gr.not.found"),
    
    /** The error item data not found. */
    ERROR_ITEM_ENTITY_NOT_FOUND("error.item.not.found"),
    
    /** The error supp data not found. */
    ERROR_SUPP_ENTITY_NOT_FOUND("error.supp.not.found"),
    
    /** The error flatfile data not found. */
    ERROR_FLATFILE_ENTITY_NOT_FOUND("error.flatfile.data.not.found"),
    
    ERROR_MISCDATA_ENTITY_NOT_FOUND("error.flatfile.data.not.found"),
    

    //Exception while inserting data in Table
    /** The error podetails insert data. */
    ERROR_PODETAILS_INSERT_DATA("error.po.table.not.found"),
    
    /** The error grdetails insert data. */
    ERROR_GRDETAILS_INSERT_DATA("error.gr.table.not.found"),
    
    /** The error supplier insert data. */
    ERROR_SUPPLIER_INSERT_DATA("error.item.table.not.found"),
    
    /** The error item insert data. */
    ERROR_ITEM_INSERT_DATA("error.supp.table.not.found"),
    
    /** The error miscdata insert data. */
    ERROR_MISCDATA_INSERT_DATA("error.po.table.not.found"),
    
    
  //Exception while updating Table data
    /** The error podetails update data. */
    ERROR_PODETAILS_UPDATE_DATA("error.po.table.not.found"),
    
    /** The error grdetails update data. */
    ERROR_GRDETAILS_UPDATE_DATA("error.gr.table.not.found"),
    
    /** The error supplier update data. */
    ERROR_SUPPLIER_UPDATE_DATA("error.item.table.not.found"),
    
    /** The error item update data. */
    ERROR_ITEM_UPDATE_DATA("error.supp.table.not.found"),
    
    /** The error miscdata update data. */
    ERROR_MISCDATA_UPDATE_DATA("error.po.table.not.found"),
    
    /** The error apigee po get. */
    //Apigee GET request errors
    ERROR_APIGEE_PO_GET("error.supp.table.not.found"),
    
    /** The error apigee gr get. */
    ERROR_APIGEE_GR_GET("error.supp.table.not.found"),
    
    /** The error apigee item get. */
    ERROR_APIGEE_ITEM_GET("error.supp.table.not.found"),
    
    /** The error apigee supp get. */
    ERROR_APIGEE_SUPP_GET("error.supp.table.not.found"),

    
    //Apigee PUT request errors   
    /** The error apigee po put. */
    ERROR_APIGEE_PO_PUT("error.supp.table.not.found"),
    
    /** The error apigee gr put. */
    ERROR_APIGEE_GR_PUT("error.supp.table.not.found"),
    
    /** The error apigee item put. */
    ERROR_APIGEE_ITEM_PUT("error.supp.table.not.found"),
    
    /** The error apigee supp put. */
    ERROR_APIGEE_SUPP_PUT("error.supp.table.not.found"),

    
    //Error in flatfile prepration
    /** The error generate flatfile po. */
    ERROR_GENERATE_FLATFILE_PO("error.supp.table.not.found"),
    
    /** The error generate flatfile gr. */
    ERROR_GENERATE_FLATFILE_GR("error.supp.table.not.found"), 
    
    /** The error generate flatfile item. */
    ERROR_GENERATE_FLATFILE_ITEM("error.supp.table.not.found"), 
    
    /** The error generate flatfile supp. */
    ERROR_GENERATE_FLATFILE_SUPP("error.supp.table.not.found"), 
    
    
    //fLATFILE service is down
    ERROR_FLATFILE_PO_SERVICE_DOWN("error.flatfile.po.service.down"),
    ERROR_FLATFILE_GR_SERVICE_DOWN("error.flatfile.gr.service.down"),
    ERROR_FLATFILE_ITEM_SERVICE_DOWN("error.flatfile.item.service.down"),
    ERROR_FLATFILE_SUPP_SERVICE_DOWN("error.flatfile.supp.service.down"),
    
    
    //error in flatfile processing
    /** The error process flatfile po. */
    ERROR_PROCESS_FLATFILE_PO("error.supp.table.not.found"),
    
    /** The error process flatfile gr. */
    ERROR_PROCESS_FLATFILE_GR("error.supp.table.not.found"), 
    
    /** The error process flatfile item. */
    ERROR_PROCESS_FLATFILE_ITEM("error.supp.table.not.found"), 
    
    /** The error process flatfile supp. */
    ERROR_PROCESS_FLATFILE_SUPP("error.supp.table.not.found"), 
    
    //error in flatfile processing
    /** The error reading config po file. */
    ERROR_READING_CONFIG_PO_FILE("error.reading.config.po.file"),
    
    /** The error reading config gr file. */
    ERROR_READING_CONFIG_GR_FILE("error.reading.config.gr.file"),
    
    /** The error reading config item file. */
    ERROR_READING_CONFIG_ITEM_FILE("error.reading.config.item.file"),
    
    /** The error reading config supp file. */
    ERROR_READING_CONFIG_SUPP_FILE("error.reading.config.supplier.file"),
    
  //UI  errors
    /** The error user not found. */
    ERROR_USER_NOT_FOUND("error.user.not.found"),
    
    /** The error user not found by email. */
    ERROR_USER_NOT_FOUND_BY_EMAIL("error.user.not.found.by.email"),
    
    
    /** The error user current pswd wrong. */
    ERROR_USER_CURRENT_PSWD_WRONG("error.user.current.pswd.wrong"),
    
    
    /** The error user pswd value is wrong. */
    ERROR_USER_PSWD_VALUE_IS_WRONG("error.user.pswd.value.is.wrong"),
    
    /** The error user email is not correct. */
    ERROR_USER_EMAIL_IS_NOT_CORRECT("error.user.email.is.not.correct"),
    
    /** The error user pswd is not correct. */
    ERROR_USER_PSWD_IS_NOT_CORRECT("error.user.pswd.is.not.correct"),
    
    /** The error user have active content. */
    ERROR_USER_HAVE_ACTIVE_CONTENT("error.user.have.active.content"),
    
    /** The error user reset password. */
    ERROR_USER_RESET_PASSWORD("error.user.reset.password"),
    
    /** The error user check password. */
    ERROR_USER_CHECK_PASSWORD("error.user.check.password"),
    
    /** The error user change password. */
    ERROR_USER_CHANGE_PASSWORD("error.user.change.password"),

    /** The err system create. */
    ERR_SYSTEM_CREATE("err.system.create.exception"),
    
    /** The err system db. */
    ERR_SYSTEM_DB("err.system.bd"),
    
    
    /** The err system send mail. */
    ERR_SYSTEM_SEND_MAIL("err.system.send.mail");

    /** The key. */
    public String KEY;

    /**
     * Instantiates a new error enum.
     *
     * @param KEY the key
     */
    ErrorEnum(String KEY) {
        this.KEY = KEY;
    }
}
