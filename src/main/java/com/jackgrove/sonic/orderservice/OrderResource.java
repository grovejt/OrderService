package com.jackgrove.sonic.orderservice;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.exam.domain.Order;

@RestController
public class OrderResource
{
  
  @Autowired
  MockOrderRepository orderRepository;

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
  @GetMapping(value = "/orders")
  public List<Order> getOrders() 
  {
    return orderRepository.findAll();
    
        
  }
  
  /**
   * Returns a HATEOAS resource response with the order and a link to get all orders.
   * @param orderId
   * @return a HATEOAS resource response with the order and a link to get all orders.
   */
  @GetMapping("/orders/{orderId}")
  public Resource<Order> getOrder(@PathVariable Long orderId) {
    Order order = orderRepository.findById(orderId);
    if (order == null) {
      throw new OrderNotFoundException("id:" + orderId);
    }
    
    Resource<Order> resource = new Resource<Order>(order);
    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getOrders());
    resource.add(linkTo.withRel("all-users"));
    return resource;
  }
}
