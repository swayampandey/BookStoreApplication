package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData placeOrder(int userId, OrderDTO orderDto);
    String cancelOrder(int orderId, int userId);
    List<OrderData> userOrders(int userId);
}