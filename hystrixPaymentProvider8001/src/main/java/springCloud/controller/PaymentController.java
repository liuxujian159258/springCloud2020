package springCloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springCloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("{server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public  String paymentInfo_OK(@PathVariable int id) {
        String s = paymentService.paymentInfo_OK(id);
        return s;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public  String paymentInfo_TimeOut(@PathVariable int id) throws InterruptedException {
        String s = paymentService.paymentInfo_Time(id);
        return s;
    }

    // 服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") int id){
        String s = paymentService.paymentCircuitBreaker(id);
        System.out.println(s);
        return s;
    }
}
