package com.jci.mail;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



/**
 * Base object for all entities that are stored in the collection and have a
 * unique identifier.
 */
public abstract class BaseObject {

  /** The id. */
  //Sunil  @Id
    protected String id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BaseObject bo = (BaseObject) obj;
        return getId() != null && bo.getId() != null && new EqualsBuilder().append(getId(), bo.getId()).isEquals();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        }
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[id=" + getId() + "]";
    }
}
