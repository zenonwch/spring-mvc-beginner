package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Order;

public interface OrderRepository {
    long saveOrder(Order order);
}