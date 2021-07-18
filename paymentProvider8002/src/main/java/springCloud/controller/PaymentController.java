package springCloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springCloud.entities.CommonResult;
import springCloud.entities.Payment;
import springCloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        logger.info("*******插入结果：" + i);
        if (i > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, i);
        }else {
            return new CommonResult(444, "插入数据库失败,serverPort:" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        logger.info("*****根据id查询" + id);
        if (paymentById != null) {
            return new CommonResult(200, "查询数据成功,serverPort:" + serverPort, paymentById);
        }
        return new CommonResult(444, "查询数据失败，无记录，查询id:" + id + "serverPort:" + serverPort, null);
    }
}
