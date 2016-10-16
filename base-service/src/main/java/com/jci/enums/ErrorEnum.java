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
    

    /** The err system create. */
    ERR_SYSTEM_CREATE("err.system.create.exception"),
    
    /** The err system db. */
    ERR_SYSTEM_DB("err.system.bd"),
    
    
    /** The err system send mail. */
    ERR_SYSTEM_SEND_MAIL("err.system.send.mail"),
    
    
    //Table not found in Azure 
  /** The error table creation. */
    ERROR_TABLE_CREATION("error.table.creation"),
    /** The error table not found. */
    ERROR_TABLE_NOT_FOUND("error.table.not.found"),
    
    /** The error po table not found. */
    ERROR_PO_TABLE_NOT_FOUND("error.po.table.not.found"),
    
    /** The error poitem table not found. */
    ERROR_POITEM_TABLE_NOT_FOUND("error.po.item.table.not.found"),
    
    /** The error gr table not found. */
    ERROR_GR_TABLE_NOT_FOUND("error.gr.table.not.found"),
    
    ERROR_GR_ITEM_TABLE_NOT_FOUND("error.gr.item.table.not.found"),
    
    /** The error item table not found. */
    ERROR_ITEM_TABLE_NOT_FOUND("error.item.table.not.found"),
    
    /** The error supp table not found. */
    ERROR_SUPP_TABLE_NOT_FOUND("error.supp.table.not.found"),
    
    /** The error miscdata table not found. */
    ERROR_MISCDATA_TABLE_NOT_FOUND("error.supp.table.not.found"),
    
    
    //Records not found in Azure storage table
    /** The error po data not found. */
    ERROR_PO_ENTITY_NOT_FOUND("error.po.entity.not.found"),
    
    /** The error gr data not found. */
    ERROR_GR_ENTITY_NOT_FOUND("error.gr.entity.not.found"),
    
    /** The error item data not found. */
    ERROR_ITEM_ENTITY_NOT_FOUND("error.item.entity.not.found"),
    
    /** The error supp data not found. */
    ERROR_SUPP_ENTITY_NOT_FOUND("error.supp.entity.not.found"),
    
    /** The error flatfile data not found. */
    ERROR_FLATFILE_ENTITY_NOT_FOUND("error.flatfile.entity.not.found"),
    
    /** The error miscdata entity not found. */
    ERROR_MISCDATA_ENTITY_NOT_FOUND("error.miscdata.entity.not.found"),
    

    //Exception while inserting data in Table
    /** The error podetails insert data. */
    ERROR_PODETAILS_INSERT_DATA("error.po.table.insert.entity"),
    
    /** The error grdetails insert data. */
    ERROR_GRDETAILS_INSERT_DATA("error.gr.table.insert.entity"),
    
    /** The error supplier insert data. */
    ERROR_SUPPLIER_INSERT_DATA("error.supp.table.insert.entity"),
    
    /** The error item insert data. */
    ERROR_ITEM_INSERT_DATA("error.item.table.insert.entity"),
    
    /** The error miscdata insert data. */
    ERROR_MISCDATA_INSERT_DATA("error.miscdata.table.insert.entity"),
    
    
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
    
    
    //Apigee GET request errors
    /** The error apigee po get. */
    ERROR_APIGEE_PO_GET("error.apigee.po.get"),
    
    /** The error apigee gr get. */
    ERROR_APIGEE_GR_GET("error.apigee.gr.get"),
    
    /** The error apigee item get. */
    ERROR_APIGEE_ITEM_GET("error.apigee.item.get"),
    
    /** The error apigee supp get. */
    ERROR_APIGEE_SUPP_GET("error.apigee.supp.get"),

    
    //Apigee PUT request errors   
    /** The error apigee po put. */
    ERROR_APIGEE_PO_PUT("error.apigee.po.put"),
    
    /** The error apigee gr put. */
    ERROR_APIGEE_GR_PUT("error.apigee.gr.put"),
    
    /** The error apigee item put. */
    ERROR_APIGEE_ITEM_PUT("error.apigee.item.put"),
    
    /** The error apigee supp put. */
    ERROR_APIGEE_SUPP_PUT("error.apigee.supp.put"),

    
    //Error in flatfile preparation
    /** The error generate flatfile po. */
    ERROR_GENERATE_FLATFILE_PO("error.supp.table.not.found"),
    
    /** The error generate flatfile gr. */
    ERROR_GENERATE_FLATFILE_GR("error.supp.table.not.found"), 
    
    /** The error generate flatfile item. */
    ERROR_GENERATE_FLATFILE_ITEM("error.supp.table.not.found"), 
    
    /** The error generate flatfile supp. */
    ERROR_GENERATE_FLATFILE_SUPP("error.supp.table.not.found"), 
    
    
    /** The error flatfile po service down. */
    //FLATFILE service is down
    ERROR_FLATFILE_PO_SERVICE_DOWN("error.flatfile.po.service.down"),
    
    /** The error flatfile gr service down. */
    ERROR_FLATFILE_GR_SERVICE_DOWN("error.flatfile.gr.service.down"),
    
    /** The error flatfile item service down. */
    ERROR_FLATFILE_ITEM_SERVICE_DOWN("error.flatfile.item.service.down"),
    
    /** The error flatfile supp service down. */
    ERROR_FLATFILE_SUPP_SERVICE_DOWN("error.flatfile.supp.service.down"),
    
    /** The error flatfile po error service down. */
    ERROR_FLATFILE_PO_ERROR_SERVICE_DOWN("error.flatfile.po.error.service.down"),
    
    /** The error flatfile gr error service down. */
    ERROR_FLATFILE_GR_ERROR_SERVICE_DOWN("error.flatfile.gr.error.service.down"),
    
    /** The error flatfile item error service down. */
    ERROR_FLATFILE_ITEM_ERROR_SERVICE_DOWN("error.flatfile.item.error.service.down"),
    
    /** The error flatfile supp error service down. */
    ERROR_FLATFILE_SUPP_ERROR_SERVICE_DOWN("error.flatfile.supp.error.service.down"),
    
    
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
    
    /** The error user pswd is not correct. */
    ERROR_USER_PSWD_IS_NOT_CORRECT("error.user.pswd.is.not.correct"),
    
    
    /** The error po segmented query. */
    //Segmented query errors
    ERROR_PO_SEGMENTED_QUERY("error.po.segmented.query"),
    
    /** The error poitem segmented query. */
    ERROR_POITEM_SEGMENTED_QUERY("error.po.item.segmented.query"),
    
    /** The error gr segmented query. */
    ERROR_GR_SEGMENTED_QUERY("error.gr.segmented.query"),
    
    /** The error supp segmented query. */
    ERROR_SUPP_SEGMENTED_QUERY("error.supp.segmented.query"),
    
    /** The error item segmented query. */
    ERROR_ITEM_SEGMENTED_QUERY("error.item.segmented.query");


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
