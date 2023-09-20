package ra.service.impl.payment;

import ra.model.dto.request.PaymentRequestDto;
import ra.model.dto.response.PaymentResponseDto;
import ra.service.IGenericService;

public interface IPaymentService extends IGenericService<PaymentResponseDto, PaymentRequestDto,Long> {
}
