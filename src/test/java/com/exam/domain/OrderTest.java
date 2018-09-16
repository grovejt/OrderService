package com.exam.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.exam.TestUtils;

/**
 * Tests for the Order object.
 * 
 * TODO: calculating the proper cost of an order is critical and certain business rules regarding rounding
 * are not yet fully specified. Business rules should be formally clarified and documented and more tests will be 
 * needed to fully validate this class.  It is likely that this test case will need to be refactored into separate
 * test cases just for doing extensive order total calculation testing.
 * 
 * @author grove
 *
 */
public class OrderTest
{
  private static Order testOrder1 = null;

  // testOrder1 has items with names in it that would sort differently if the sort order was case sensitive and is purposely constructed with
  // somewhat strange names.  
  private static final String ITEM_NAME_1 = "materialItem1";
  private static final String ITEM_NAME_2 = "MATERIALITEM2";
  private static final String ITEM_NAME_3 = "MATERIALITEM3";
  private static final String ITEM_NAME_4 = "materialItem4";
  private static final String ITEM_NAME_5 = "serviceItem1";
  private static final String ITEM_NAME_6 = "SERVICEITEM2";
  private static final String ITEM_NAME_7 = "SERVICEITEM3";
  private static final String ITEM_NAME_8 = "serviceItem4";
  
  @BeforeClass
  public static void createTestObjects()
  {
    // Note: Orders are immutable so we only need to create them once, not before every test.
    Item materialItem1 = new MaterialItem(1L, ITEM_NAME_1, new BigDecimal("1.33"));
    Item materialItem2 = new MaterialItem(2L, ITEM_NAME_2, new BigDecimal("2.33"));
    Item materialItem3 = new MaterialItem(3L, ITEM_NAME_3, new BigDecimal("3.33"));
    Item materialItem4 = new MaterialItem(4L, ITEM_NAME_4, new BigDecimal("4"));
    Item serviceItem1 = new ServiceItem(11L, ITEM_NAME_5, new BigDecimal("1.33"));
    Item serviceItem2 = new ServiceItem(12L, ITEM_NAME_6, new BigDecimal("2.33"));
    Item serviceItem3 = new ServiceItem(13L, ITEM_NAME_7, new BigDecimal("3.33"));
    Item serviceItem4 = new ServiceItem(14L, ITEM_NAME_8, new BigDecimal("4"));
    
    //The items are added to the initialized array in unsorted order to better test the sort functionality specified for the getItems function:
    OrderItem[] orderitems = new OrderItem[] {
        new OrderItem(1, serviceItem4), 
        new OrderItem(1, materialItem2), 
        new OrderItem(1, materialItem3), 
        new OrderItem(1, materialItem4), 
        new OrderItem(1, serviceItem1), 
        new OrderItem(1, serviceItem2), 
        new OrderItem(1, serviceItem3), 
        new OrderItem(1, materialItem1)};
    
    testOrder1 = new Order(orderitems);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOrderMustHaveAtLeastOneItem()
  {
    // An OrderItem must have a quantity greater than zero:
    OrderItem[] emptyOrderItems = new OrderItem[] {};
    new Order(emptyOrderItems);
  }
  
  @Test 
  public void testGetOrderTotalForSimpleOrder() {
    
    // just a quick simple test with an item that costs $5.00 with a quantity of 2 and a tax of 10%, total should be $11.00
    
    // Create a really simple order for a simple test:
    Item materialItem1 = new MaterialItem(1L, ITEM_NAME_1, new BigDecimal("5"));
    OrderItem[] orderItems = new OrderItem[] {new OrderItem(2, materialItem1)};
    Order testOrder = new Order(orderItems);
    
    BigDecimal taxRate = new BigDecimal(".1");
    BigDecimal expectedOrderCost = new BigDecimal("11.00");
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderCost, testOrder.getOrderTotal(taxRate));
    
    //check that the big decimal has a scale of 2 (rounded to the nearest penny):
    assertEquals("Scale should be 2", 2, testOrder.getOrderTotal(taxRate).scale());
  }
  
  @Test
  public void testGetOrderTotalForBigOrder()
  {
    // Test with a zero tax rate:
    BigDecimal taxRate = BigDecimal.ZERO;
    BigDecimal expectedOrderCost = new BigDecimal("21.98");
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderCost, testOrder1.getOrderTotal(taxRate));

    // Test with a simple 3% tax rate:
    taxRate = new BigDecimal(".03");
    expectedOrderCost = new BigDecimal("22.31");
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderCost, testOrder1.getOrderTotal(taxRate));
  }
  
  @Test
  public void testGetOrderTotalRoundingEdgeCases()
  {
    // Note: for testOrder1 a .005 tax rate will give a different result depending on whether rounding is done for each item cost and then 
    // summed (total will be 22.04) vs summing all the unrounded item cost values and then rounding the final sum (total would be 22.03) 
    // so this is an important test to keep and may need to be updated if business rules change.
    BigDecimal taxRate = new BigDecimal(".005");
    BigDecimal expectedOrderCost = new BigDecimal("22.04");
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderCost, testOrder1.getOrderTotal(taxRate));
    
    // Test with a really small rate:  (should give same result as a zero tax rate)
    taxRate = new BigDecimal(".0000000000001");
    expectedOrderCost = new BigDecimal("21.98");
    TestUtils.assertBigDecimalsAreEquivalent(expectedOrderCost, testOrder1.getOrderTotal(taxRate));
    }

  @Test
  public void testGetItems()
  {
    // testOrder1 has items with names in it that would sort differently if the sort order was case sensitive 
    // so this test makes sure the sort works as specified (case insensitive sort).
    List<Item> items = new ArrayList<Item>(testOrder1.getItems());
    
    // for debuging sort order uncomment this line to print the names to the console:
    for (Item item : items) {
      System.out.println(item.getName());
    }
    
    assertEquals(ITEM_NAME_1, items.get(0).getName());
    assertEquals(ITEM_NAME_2, items.get(1).getName());
    assertEquals(ITEM_NAME_3, items.get(2).getName());
    assertEquals(ITEM_NAME_4, items.get(3).getName());
    assertEquals(ITEM_NAME_5, items.get(4).getName());
    assertEquals(ITEM_NAME_6, items.get(5).getName());
    assertEquals(ITEM_NAME_7, items.get(6).getName());
    assertEquals(ITEM_NAME_8, items.get(7).getName());
  }
  
  @Test
  public void testToString()
  {
    String expectedToStringValue = "Order [orderItems=[OrderItem [quantity=1, item=ServiceItem [key=14, name=serviceItem4, price=4, isTaxable()=false]], OrderItem [quantity=1, item=MaterialItem [key=2, name=MATERIALITEM2, price=2.33, isTaxable()=true]], OrderItem [quantity=1, item=MaterialItem [key=3, name=MATERIALITEM3, price=3.33, isTaxable()=true]], OrderItem [quantity=1, item=MaterialItem [key=4, name=materialItem4, price=4, isTaxable()=true]], OrderItem [quantity=1, item=ServiceItem [key=11, name=serviceItem1, price=1.33, isTaxable()=false]], OrderItem [quantity=1, item=ServiceItem [key=12, name=SERVICEITEM2, price=2.33, isTaxable()=false]], OrderItem [quantity=1, item=ServiceItem [key=13, name=SERVICEITEM3, price=3.33, isTaxable()=false]], OrderItem [quantity=1, item=MaterialItem [key=1, name=materialItem1, price=1.33, isTaxable()=true]]]]";
    String actualToStringValue = testOrder1.toString();
    
    //Uncomment the following line for easy debugging:
    System.out.println(actualToStringValue);
    
    assertEquals("toString is not correct!", expectedToStringValue, actualToStringValue);
  }
}
