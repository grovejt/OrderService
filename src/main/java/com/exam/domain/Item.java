package com.exam.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a part or service that can be sold.
 *
 * Instances of this interface should be immutable since they will be sent to
 * other systems for processing and the other systems should not be able to
 * change the OrderItem in any way.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Exams are us</p>
 * 
 * @author John Grove
 * @version 1.0.1
 */
public interface Item extends Serializable
{
  Long getKey();

  String getName();

  BigDecimal getPrice();
  
  boolean isTaxable();
}
