package com.jci.enums;



/**
 * The Enum MailSenderEnum.
 */
public enum MailSenderEnum {
    
    /** The no reply. */
    NO_REPLY("suniltct@gmail.com");

    /** The address. */
    private String address;

    /**
     * Instantiates a new mail sender enum.
     *
     * @param address the address
     */
    private MailSenderEnum(String address) {
        this.address = address;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

}
