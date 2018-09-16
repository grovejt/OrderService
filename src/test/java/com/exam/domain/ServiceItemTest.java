package com.exam.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ServiceItemTest
{

  @Test
  public void testThatServiceItemsAreNotTaxable()
  {
    Item serviceItem = new ServiceItem(1L, "Some Service Item", BigDecimal.ONE);
    assertFalse("Service items should not be taxable!", serviceItem.isTaxable());
  }
}
