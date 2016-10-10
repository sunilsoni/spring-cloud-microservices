package com.jci.enums;



/**
 * Enumeration of all the collections.
 */
public enum CollectionEnum {
    
    /** The po. */
    PO("po"),
    
    /** The poitem. */
    POITEM("poitem"),
    
    /** The item. */
    ITEM("item"),
    
    /** The supplier. */
    SUPPLIER("supplier"),
    
    /** The gr. */
    GR("gr"),
    
    /** The misc. */
    MISC("misc"),
    
    /** The journal. */
    JOURNAL("journal"),
    
    /** The mails. */
    MAILS("mails");

    /** The name. */
    private String name;

    /**
     * Instantiates a new collection enum.
     *
     * @param name the name
     */
    private CollectionEnum(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
