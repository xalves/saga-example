package com.example.orchestrator.controller;

import com.example.orchestrator.model.Order;
import com.example.orchestrator.model.OrderResponse;
import com.example.orchestrator.model.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestratorController {

    @PostMapping("/order")
    public String processOrder(@RequestBody Order order){
        final RestTemplate restTemplate = new RestTemplate();
        OrderResponse orderResponse = restTemplate.postForObject("http://localhost:8081/createOrder", order, OrderResponse.class);

        //Transaction 1: Order Service
        if(!orderResponse.getStatus().equals("success")){
            return "Order creation failed!";
        }

        //Transaction 2: Payment Service
        PaymentResponse paymentResponse = restTemplate.postForObject("http://localhost:8081/processPayment", order.getPaymentDetails(), PaymentResponse.class);

        if(!paymentResponse.getStatus().equals("success")) {
            // compensate transaction
            restTemplate.patchForObject("http://localhost:8081/cancelOrder", orderResponse.getOrderId(), String.class);
            return "Payment processing failed! Order canceled";
        }

        return "Order placed sucessfully!";
    }

}
