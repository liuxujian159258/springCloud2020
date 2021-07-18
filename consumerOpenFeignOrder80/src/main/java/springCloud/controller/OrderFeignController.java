package springCloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springCloud.entities.CommonResult;
import springCloud.entities.Payment;
import springCloud.service.PaymentFeignService;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    };

    @GetMapping("/consumer/payment/feign/time")
    public String paymentFeignTimeout(){
        // OpenFeign集成了ribbon，默认等待1秒、
        return paymentFeignService.paymentFeignTimeout();
    }
}
