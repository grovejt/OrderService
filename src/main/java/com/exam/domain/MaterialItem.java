package com.exam.domain;

import java.math.BigDecimal;

/**
 * An order item that is taxable.
 * 
 * @author John Grove
 * @version 1.0.1
 *
 */
public class MaterialItem extends AbstractItem
{
  private static final long serialVersionUID = 1L;

  public MaterialItem(Long key, String name, BigDecimal price)
  {
    super(key, name, price);
  }

  @Override
  public boolean isTaxable()
  {
    return true;
  }


  @Override
  public String toString()
  {
    return String.format("MaterialItem [key=%s, name=%s, price=%s, isTaxable()=%s]", key, name, price, isTaxable());
  }
  
  
}
