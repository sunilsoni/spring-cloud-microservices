package com.jci.enums;

/**
 * Enumeration of all the collections
 */
public enum CollectionEnum {
    PO("po"),
    POITEM("poitem"),
    ITEM("item"),
    SUPPLIER("supplier"),
    GR("gr"),
    MISC("misc"),
    JOURNAL("journal"),
    MAILS("mails");

    private String name;

    private CollectionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
