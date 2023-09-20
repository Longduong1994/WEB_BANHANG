package ra.service.impl.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.PaymentRequestDto;
import ra.model.dto.response.PaymentResponseDto;
import ra.model.entity.Color;
import ra.model.entity.Payment;
import ra.repository.IPaymentRepository;
import ra.service.mapper.PaymentMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;
    @Override
    public List<PaymentResponseDto> findAll() {
        return paymentRepository.findAll().stream().map(p->paymentMapper.toResponse(p)).collect(Collectors.toList());    }

    @Override
    public PaymentResponseDto findById(Long id) throws ClassCastException{
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return paymentMapper.toResponse(payment.get());
        }
        throw new ClassCastException("Payment not found");
    }

    @Override
    public PaymentResponseDto save(PaymentRequestDto paymentRequestDto) {
        if (paymentRepository.existsByName(paymentRequestDto.getName())){
            throw new ClassCastException("Color already exists");
        }
        Payment payment = paymentMapper.toEntity(paymentRequestDto);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponseDto update(PaymentRequestDto paymentRequestDto, Long id) {
        Payment payment = paymentMapper.toEntity(paymentRequestDto);
        payment.setId(id);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponseDto delete(Long id) throws ClassCastException{
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            paymentRepository.deleteById(id);
            return  paymentMapper.toResponse(payment.get());
        }
        throw new ClassCastException("Payment not found");
    }
}
