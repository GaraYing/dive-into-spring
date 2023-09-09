package com.gara.service;

import com.gara.self.spring.Component;
import org.springframework.context.annotation.Conditional;

@Conditional(value = OrderServiceCondition.class)
@Component
public class OrderService {
}
