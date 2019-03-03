package my.spring.project.springmvc.service.impl;

import my.spring.project.springmvc.domain.Order;
import my.spring.project.springmvc.domain.repository.OrderRepository;
import my.spring.project.springmvc.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(final OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public long saveOrder(final Order order) {
        return repository.saveOrder(order);
    }
}