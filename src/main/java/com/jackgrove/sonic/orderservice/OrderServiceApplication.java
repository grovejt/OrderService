package com.jackgrove.sonic.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO - 
 *   * implement a simple REST based order service to push to Pivotal Cloud
 *   * spring actuator monitoring endpoints
 *   * implement jpa with in-memory test database and connection to PostgreSQL database on Pivotal
 *   * add in redis caching
 *   * secure the service
 *   * create a new simple ui project to show orders (perhaps a simple thymeleaf or angular app) and push to pivotal.
 *   * ...etc... 
 *   
 * @author John Grove
 *
 */
@SpringBootApplication
public class OrderServiceApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}
