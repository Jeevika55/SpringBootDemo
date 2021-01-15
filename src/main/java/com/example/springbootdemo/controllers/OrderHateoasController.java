package com.example.springbootdemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.entities.Order;
import com.example.springbootdemo.entities.User;
import com.example.springbootdemo.exceptions.UserNotFoundException;
import com.example.springbootdemo.repositories.OrderRepository;
import com.example.springbootdemo.repositories.UserRepository;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class OrderHateoasController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		List<Order> allOrders = optionalUser.get().getOrders();
		CollectionModel<Order> finalResources = new CollectionModel<>(allOrders);
		return finalResources;
	}
}
