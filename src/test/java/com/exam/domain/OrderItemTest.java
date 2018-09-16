package com.exam.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.exam.TestUtils;

public class OrderItemTest
{

  private static OrderItem testOrderItem1 = null;
  private static Item materialItem1 = null;
  
  @BeforeClass
  public static void createTestObjects()
  {
    materialItem1 = new MaterialItem(1L, "MaterialItem1", new BigDecimal("5"));
    testOrderItem1 = new OrderItem(2, materialItem1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testQuatityMustBeGreaterThanZero()
  {
    // An OrderItem must have a quantity greater than zero:
    new OrderItem(0, materialItem1);
  }
  
  @Test
  public void testGetTax()
  {
    BigDecimal orderItemTotal = new BigDecimal("10.00");
    BigDecimal taxRate = new BigDecimal(".1");
    BigDecimal expectedTax = new BigDecimal("1.00");
    BigDecimal actualTax = testOrderItem1.getTax(orderItemTotal, taxRate);
    TestUtils.assertBigDecimalsAreEquivalent(expectedTax, actualTax);
    
    //check that the big decimal has a scale of 2 (rounded to the nearest penny):
    assertEquals("Scale should be 2", 2, actualTax.scale());
  }
  
  @Test
  public void testGetOrderItemTotal()
  {
    BigDecimal taxRate = new BigDecimal(".1");
    BigDecimal expectedOrderItemCost = new BigDecimal("11.00");
    BigDecimal actualOrderItemCost = testOrderItem1.getOrderItemTotal(taxRate);
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderItemCost, actualOrderItemCost);
    
    //check that the big decimal has a scale of 2 (rounded to the nearest penny):
    assertEquals("Scale should be 2", 2, actualOrderItemCost.scale());
  }


  @Test
  public void testToString()
  {
    String expectedToStringValue = "OrderItem [quantity=2, item=MaterialItem [key=1, name=MaterialItem1, price=5, isTaxable()=true]]";
    String actualToStringValue = testOrderItem1.toString();
    
    //Uncomment the following line for easy debugging:
    System.out.println(actualToStringValue);
    
    assertEquals("toString is not correct!", expectedToStringValue, actualToStringValue);
  }

}
