package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.jpa.OrderRepository;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService{

    OrderRepository orderRepository;
    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        // Id 생성
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());
        System.out.println(orderDto);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

        //저장
        orderRepository.save(orderEntity);

        //반환
        OrderDto returnDto = mapper.map(orderEntity, OrderDto.class);

        return returnDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {

        // 찾기
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        // 변환
        ModelMapper mapper = new ModelMapper();
        OrderDto orderDto = mapper.map(orderEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {

        //찾기
        Iterable<OrderEntity> orderEntities = orderRepository.findByUserId(userId);

        return orderEntities;
    }
}
