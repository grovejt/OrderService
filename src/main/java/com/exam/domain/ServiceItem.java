package com.exam.domain;

import java.math.BigDecimal;

/**
 * An order item that is not taxable.
 * 
 * @author John Grove
 * @version 1.0.1
 *
 */
public class ServiceItem extends AbstractItem
{
  private static final long serialVersionUID = 1L;

  public ServiceItem(Long key, String name, BigDecimal price)
  {
    super(key, name, price);
  }

  @Override
  public boolean isTaxable()
  {
    return false;
  }

  @Override
  public String toString()
  {
    return String.format("ServiceItem [key=%s, name=%s, price=%s, isTaxable()=%s]", key, name, price, isTaxable());
  }

  
}
