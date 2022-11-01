package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.BookData;
import com.bridgelabz.bookstore.model.OrderData;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private EmailSenderService mailService;

    @Override
    public OrderData placeOrder(int userId, OrderDTO orderDto) {
        UserRegistrationData user = userRegistrationRepository.findById(userId).orElse(null);
        BookData book = bookRepository.findById(orderDto.getBookId()).orElse(null);
        if (user != null) {
            int orderPrice = book.getPrice() * orderDto.getQuantity();
            book.setQuantity(book.getQuantity()-orderDto.getQuantity());

            OrderData order = new OrderData(user,book,orderPrice,orderDto);
            orderRepository.save(order);
            cartRepository.deleteAll();
            mailService.sendEmail(user.getEmail(),"Order Placed",
                    "Book Name :"+order.getBook().getBookName()+
                            "\n" +"Book Description :"+order.getBook().getBookDescription()+
                            "\n" +"Book Price :"+order.getBook().getPrice()+
                            "\n" +"Order Quantity :"+orderDto.getQuantity() +
                            "\n" +"Order Price :"+orderPrice);
            return order;
        }
        return null;
    }

    @Override
    public String cancelOrder(int orderId, int userId) {
        UserRegistrationData user = userRegistrationRepository.findById(userId).orElse(null);
        if (user != null) {
            OrderData order = orderRepository.findById(orderId).orElse(null);
            if (order != null) {
                order.setCancel(true);
                BookData book = bookRepository.findById(order.getBook().getBookId()).orElse(null);
                book.setQuantity(book.getQuantity() + order.getQuantity());
                mailService.sendEmail(user.getEmail(),"Order Cancelled","Order Id " + orderId+"\n"+order);
                orderRepository.save(order);
                return "Order Cancelled";
            }
        }
        return "User Not Found !!";
    }

    @Override
    public List<OrderData> userOrders(int userId) {
        UserRegistrationData user = userRegistrationRepository.findById(userId).orElse(null);
        if (user != null) {
            List<OrderData> order = orderRepository.findAllByUserId(userId);
            return order;
        }
        return null;
    }


}
