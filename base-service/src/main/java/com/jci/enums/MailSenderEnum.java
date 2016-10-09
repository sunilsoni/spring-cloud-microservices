package com.jci.enums;

public enum MailSenderEnum {
    NO_REPLY("suniltct@gmail.com");

    private String address;

    private MailSenderEnum(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
