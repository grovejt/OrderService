package com.exam.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

public class MaterialItemTest
{

  private static Item materialItem = null;
  
  @BeforeClass
  public static void createTestObjects()
  {
    materialItem = new MaterialItem(1L, "MaterialItem1", new BigDecimal("5"));
  }
  
  @Test
  public void testThatMaterialItemsAreTaxable()
  {
    assertTrue("All material items should be taxable!", materialItem.isTaxable());
  }
  
  @Test
  public void testToString()
  {
    String expectedToStringValue = "MaterialItem [key=1, name=MaterialItem1, price=5, isTaxable()=true]";
    String actualToStringValue = materialItem.toString();
    
    //Uncomment the following line for easy debugging:
    System.out.println(actualToStringValue);
    
    assertEquals("toString is not correct!", expectedToStringValue, actualToStringValue);
  }

}
