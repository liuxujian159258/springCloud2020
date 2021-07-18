package springCloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(int id){
        return "a线程池： " + Thread.currentThread().getName() + "paymentInfo_OK " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Time(int id) throws InterruptedException {
        int aeg = 10/0;
        TimeUnit.SECONDS.sleep(id);
        return "a线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOut " + id;
    }

    public String paymentInfo_TimeOutHandler(int id){
        return "a线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler " + id + "系统繁忙";
    }

    //   服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(int id) {
        if (id<0) {
            throw new RuntimeException("******id 不能为服务");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" +"调用成功，流水号" + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(int id) {
        return "id 不能为负数，请稍后再试, id" + id;
    }
}
