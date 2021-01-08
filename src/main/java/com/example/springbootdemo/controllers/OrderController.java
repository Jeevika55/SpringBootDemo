package com.example.springbootdemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.entities.Order;
import com.example.springbootdemo.entities.User;
import com.example.springbootdemo.exceptions.OrderNotFoundException;
import com.example.springbootdemo.exceptions.UserNotFoundException;
import com.example.springbootdemo.repositories.OrderRepository;
import com.example.springbootdemo.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		return optionalUser.get().getOrders();
	}

	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found");

		User user = optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
	}

	@GetMapping("/{userId}/orders/{orderId}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId)
			throws UserNotFoundException, OrderNotFoundException {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(!optionalOrder.isPresent())
			throw new OrderNotFoundException("Order not found");

		return orderRepository.findById(orderId);
	}
}
