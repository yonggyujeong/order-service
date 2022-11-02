package com.example.orderservice.jpa;

import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void findByOrderId() {
    }

    @Test
    void findByUserId() {
        Iterable<OrderEntity> findById = orderRepository.findByUserId("dbfd1616-250c-406b-9fc7-f8c92d44c532");
        Iterator<OrderEntity> result = findById.iterator();
        if(result.hasNext()){
            org.assertj.core.api.Assertions.assertThat(result.next().getOrderId()).isEqualTo("3f479f2b-9c37-4483-b991-2dff2c61ab26");
        }

    }
}