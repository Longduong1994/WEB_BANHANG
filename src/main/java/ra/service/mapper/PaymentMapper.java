package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.PaymentRequestDto;
import ra.model.dto.response.PaymentResponseDto;
import ra.model.entity.Payment;
import ra.service.IGenericMapper;
@Component
public class PaymentMapper implements IGenericMapper<Payment, PaymentRequestDto, PaymentResponseDto> {
    @Override
    public Payment toEntity(PaymentRequestDto paymentRequestDto) {
        return Payment.builder().name(paymentRequestDto.getName()).build();
    }

    @Override
    public PaymentResponseDto toResponse(Payment payment) {
        return PaymentResponseDto.builder().id(payment.getId()).name(payment.getName()).build();
    }
}
