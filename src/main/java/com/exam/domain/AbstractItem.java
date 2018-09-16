package com.exam.domain;

import java.math.BigDecimal;

/**
 * 
 * Abstract superclass for all order items such as service and material items.
 * 
 * @author grove
 * @see Item
 */
abstract class AbstractItem implements Item
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  protected final Long key;
  protected final String name;
  protected final BigDecimal price;

  public AbstractItem(Long key, String name, BigDecimal price)
  {
    super();
    this.key = key;
    this.name = name;
    this.price = price;
  }

  public Long getKey()
  {
    return key;
  }

  public String getName()
  {
    return name;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractItem other = (AbstractItem) obj;
    if (key == null)
    {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    if (name == null)
    {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (price == null)
    {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return String.format("AbstractItem [key=%s, name=%s, price=%s]", key, name, price);
  }

  
}