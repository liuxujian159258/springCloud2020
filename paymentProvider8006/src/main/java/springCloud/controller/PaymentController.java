package springCloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/consul")
    public String paymentConsul() {
        return "springcloud with consul :" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
