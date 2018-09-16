package com.exam.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An item to be added to an order including the quantity of the item for the
 * order. Per business rules an order item must contain a non-null item with a
 * quantity. Quantity must be a positive integer value.
 * 
 * The cost for an order item (getOrderItemTotal) is calculated using half-up
 * rounding to the nearest penny. Cost without taxes is calculated first and
 * rounded to the nearest penny then tax on that is calculated and rounded to
 * the nearest penny. Then these two numbers are summed to get the total cost of
 * the order item. TODO: confirm these cost calculation business rules.
 * 
 * TODO: confirm these assumed business rules: * are zero and negative
 * quantities allowed? (assumption is that quantities must be greater than zero)
 * * are quantities restricted to integer values or are decimals allowed?
 * (assumption is that only integer values are allowed) * is cost half-rounded
 * up to the penny after any applicable taxes are applied? (assumption is that
 * this is true)
 * 
 * @author John Grove
 * @version 1.0.1
 *
 */
public class OrderItem implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  private static final int ROUNDING_SCALE = 2;
  private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
   
  private final int quantity;
  private final Item item;

  public OrderItem(int quantity, Item item)
  {
    if (quantity < 1)
    {
      throw new IllegalArgumentException("Quantity must be greater than zero when creating an OrderItem.");
    }
    
    this.quantity = quantity;
    this.item = item;
  }

  /**
   * Calculate the total cost for this item as the price of the item multiplied by
   * the quantity and then adding in the applicable tax if the item is taxable.
   * Rounding is to the nearest penny.
   * 
   * @param taxRate
   *          the rate at which taxable items in the order should be taxed. Note:
   *          this is a rate so for a 3% tax pass in 'new BigDecimal(".03")'
   * 
   * @return the cost after the tax has been applied, rounded to the nearest penny
   *         (2 decimal places)
   */
  public BigDecimal getOrderItemTotal(BigDecimal taxRate)
  {
    BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(quantity)).setScale(ROUNDING_SCALE, ROUNDING_MODE);
    BigDecimal tax = getTax(subtotal, taxRate);
    
    return subtotal.add(tax);
  }

  /**
   * Get the tax on this orderItem rounded to the nearest penny. If the item is
   * not taxable then this will return zero ("0.00")
   * 
   * Note: visibility os package private to make testing easier.
   * 
   * @param subTotal
   *          the amount to calculate the tax for
   * @param taxRate
   *          taxRate as a rate (e.g a 3% tax should be passed in as new BigDecimal("0.03")
   * @return the tax amount for this item rounded to the nearest penny.
   */
  BigDecimal getTax(BigDecimal subTotal, BigDecimal taxRate)
  {
    // if the item is taxable calculate the tax and round the result (tax amounts
    // are zero if the item is not taxable)
    BigDecimal roundedTaxAmount = new BigDecimal("0.00");
    if (item.isTaxable())
    {
      roundedTaxAmount = subTotal.multiply(taxRate).setScale(ROUNDING_SCALE, ROUNDING_MODE);
    }
    return roundedTaxAmount;
  }

  /**
   * @return the quantity
   */
  public int getQuantity()
  {
    return quantity;
  }

  /**
   * @return the item
   */
  public Item getItem()
  {
    return item;
  }

  @Override
  public String toString()
  {
    return String.format("OrderItem [quantity=%s, item=%s]", quantity, item);
  }
  
}
