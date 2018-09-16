package com.jackgrove.sonic.orderservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.domain.Item;
import com.exam.domain.MaterialItem;
import com.exam.domain.Order;
import com.exam.domain.OrderItem;
import com.exam.domain.ServiceItem;

@RestController
public class OrderController
{

  /**
   * Just a made up use case where the uri to request an order creates and returns
   * a dummy order so I can have something to upload to the pivotal cloud and try 
   * to access via a get request from curl or a browser.  
   * 
   * Can't do much since the Order object doesn't have an id yet in
   * order to keep the exam code simple.  
   * 
   * TODO: refactor the domain objects into full fledged jpa persistable objects with id's and 
   * implement a true REST api as an example of a simple web service to deploy on Pivotal.  
   * Perhaps try out Postgres and redis as well on pivotal.
   * 
   */
  @RequestMapping(value = "/order", method = RequestMethod.GET)
  public Order getOrder() 
  {

    Item materialItem1 = new MaterialItem(1L, "materialItem1", new BigDecimal("1.33"));
    Item serviceItem1 = new ServiceItem(11L, "serviceItem1", new BigDecimal("2.33"));
    
    OrderItem[] orderitems = new OrderItem[] 
        {
        new OrderItem(1, materialItem1), 
        new OrderItem(1, serviceItem1)};
        
    Order order = new Order(orderitems);
    
    return order;
        
  }
}
