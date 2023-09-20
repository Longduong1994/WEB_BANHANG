package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.OrderRequestDto;
import ra.model.dto.response.CartResponseDto;
import ra.model.dto.response.OrderResponseDto;
import ra.model.entity.Cart;
import ra.model.entity.Order;
import ra.model.entity.Payment;
import ra.repository.ICartRepository;
import ra.repository.IPaymentRepository;
import ra.service.IGenericMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderMapper implements IGenericMapper<Order, OrderRequestDto, OrderResponseDto> {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IPaymentRepository paymentRepository;
    @Override
    public Order toEntity(OrderRequestDto orderRequestDto)throws ClassCastException {
        Optional<Payment> payment = paymentRepository.findById(orderRequestDto.getPaymentId());
        if (!payment.isPresent()) {
            throw new ClassCastException("Payment not found");
        }
        return Order.builder()
                .address(orderRequestDto.getAddress())
                .phone(orderRequestDto.getPhone())
                .other(orderRequestDto.getOther())
                .payment(payment.get())
               .build();
    }

    @Override
    public OrderResponseDto toResponse(Order order) {
        List<CartResponseDto> carts = new ArrayList<>();
        Double total = 0.0;
        for (Cart c:cartRepository.findByOrder(order)) {
        carts.add(cartMapper.toResponse(c));
        total += c.getProductSamples().getProduct().getExportPrice()*c.getQuantity();
        }
        return OrderResponseDto.builder()
                .id(order.getId())
                .address(order.getAddress())
                .phone(order.getPhone())
                .total(total)
                .status(order.getOrderStatus().getStatus())
                .buyer(order.getUsers().getFullName())
                .payment(order.getPayment().getName()).build();
    }
}
