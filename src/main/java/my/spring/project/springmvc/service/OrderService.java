package my.spring.project.springmvc.service;

import my.spring.project.springmvc.domain.Order;

public interface OrderService {
    long saveOrder(Order order);
}