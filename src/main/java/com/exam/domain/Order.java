package com.exam.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Order that contains a collection of items.
 *
 * This class is immutable since it is sent to other systems for processing that
 * should not be able to change it in any way.
 * 
 * Orders contain a collection of items that may or may not be taxable.  For taxable items
 * the amount of tax to charge on a taxable item is calculated using half-round up with a precision
 * of 2.  
 * 
 * However, the requirements aren't clear on the rounding details on tax calculations 
 * so half-round up is assumed with rounding to a precision of 2 for every order item done before summing
 * the cost of all the items in order to get a total for the order.
 * 
 * Note: If business rules were to instead specify rounding is to be done after done summation then the 
 * rounding differences could result in different total amounts in certain edge cases.
 * 
 * TODO: confirm the business rules for tax calculations as they relate to rounding and calculating 
 * order totals.  Depending on business needs we may need to consider allowing dynamic specification of 
 * rounding business rules (e.g. whether or not to round for each item or just round the total and what 
 * rounding mode and scale to use).
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Exams are us</p>
 * 
 * @author Joe Blow
 * @author John Grove
 * @version 1.0.1
 */
public class Order implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final List<OrderItem> orderItems;

  /**
   * Create a new Order. When creating a new order an array of orderItems is passed in
   * (per the documented requirements) Note: The order class is immutable so items cannot 
   * be added or removed nor can quantities be changed once the class has been instantiated.
   * 
   * Orders must contain at least one item with a quantity greater than zero.
   * 
   * @param orderItemsArray
   *          the array of items to be added to the order.
   */
  public Order(OrderItem[] orderItemsArray)
  {
    if (orderItemsArray.length < 1)
    {
      throw new IllegalArgumentException("An order must have at least one item.");
    }

    this.orderItems = Collections.unmodifiableList(Arrays.asList(orderItemsArray));
  }

  /**
   * @param taxRate
   *          the rate at which taxable items in the order should be taxed.  
   *          Note: this is a rate so for a 3% tax pass in 'new BigDecimal(".03")'
   * 
   * @return the total order cost after the tax has been applied
   */
  public BigDecimal getOrderTotal(BigDecimal taxRate)
  {
    BigDecimal total = BigDecimal.ZERO;
    for (OrderItem orderItem : orderItems)
    {
      total = total.add(orderItem.getOrderItemTotal(taxRate));
    }
    
    return total;
  }
 
  /**
   * Get all the items that make up the order sorted by item name as an unmodifiable collection.  If the same items appear 
   * multiple times on the order it will multiple times in the returned collection.
   * 
   * TODO: confirm whether or not duplicate items should be ignored and only a unique collection of items should be returned
   * and consider using stream processing to collect the items from the order item array.
   * 
   * @return an unmodifiable collection of unique items sorted by item name (case-insensitive)
   */
  public Collection<Item> getItems()
  { 
    List<Item> items = new ArrayList<>(orderItems.size());
    for (OrderItem orderItem : orderItems)
    {
      items.add(orderItem.getItem());
    }
    
    List<Item> sortedItems = items.stream().sorted(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList()); 
    return Collections.unmodifiableList(sortedItems);
  }

  @Override
  public String toString()
  {
    //TODO: consider implementing a prettier toString for the Order object with line breaks between items (possibly using streams with a joining collector):
    return String.format("Order [orderItems=%s]", orderItems);
  }
}
