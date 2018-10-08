package com.jackgrove.sonic.orderservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.exam.domain.Item;
import com.exam.domain.MaterialItem;
import com.exam.domain.Order;
import com.exam.domain.OrderItem;
import com.exam.domain.ServiceItem;

/**
 * Just a mock until a real JPA repository is implemented
 * @author grove
 *
 */
@Component
public class MockOrderRepository
{
  
  private static Map<Long, Order> orders = new HashMap<>();
  
  private static long idCounter = 0;

  // create some dummy orders for mock testing:
  static {
    Order order = createDummyOrder(new BigDecimal("1.33"), new BigDecimal("2.33"));
    orders.put(order.getOrderId(), order);
    order = createDummyOrder(new BigDecimal("1.43"), new BigDecimal("2.43"));
    orders.put(order.getOrderId(), order);
    order = createDummyOrder(new BigDecimal("1.53"), new BigDecimal("2.53"));
    orders.put(order.getOrderId(), order);
    order = createDummyOrder(new BigDecimal("1.63"), new BigDecimal("2.63"));
    orders.put(order.getOrderId(), order);
  }

  private static Order createDummyOrder(BigDecimal materialItem1Price, BigDecimal serviceItem1Price)
  {
    Item materialItem1 = new MaterialItem(++idCounter, "materialItem1", materialItem1Price);
    Item serviceItem1 = new ServiceItem(++idCounter, "serviceItem1", serviceItem1Price);
    OrderItem[] orderitems = new OrderItem[] 
        {
        new OrderItem(1, materialItem1), 
        new OrderItem(1, serviceItem1)};
    Order order = new Order(++idCounter, orderitems);
    return order;
  }
  
  public List<Order> findAll(){
    return new ArrayList<Order>(orders.values());
  }
  
  
  public Order findById(Long orderId){
    return orders.get(orderId);
  }
}
