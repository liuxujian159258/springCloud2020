package springCloud.service;

        import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(int id) {
        return "paymentInfo_OK服务挂了";
    }

    @Override
    public String paymentInfo_TimeOut(int id) {
        return "paymentInfo_TimeOut服务挂了";
    }
}
