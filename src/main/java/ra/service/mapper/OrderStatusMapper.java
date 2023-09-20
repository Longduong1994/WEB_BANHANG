package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.OrderStatusRequestDto;
import ra.model.dto.response.OrderStatusResponseDto;
import ra.model.entity.OrderStatus;
import ra.service.IGenericMapper;
@Component
public class OrderStatusMapper implements IGenericMapper<OrderStatus, OrderStatusRequestDto, OrderStatusResponseDto> {
    @Override
    public OrderStatus toEntity(OrderStatusRequestDto orderStatusRequestDto) {
        return OrderStatus.builder().status(orderStatusRequestDto.getName()).build();
    }

    @Override
    public OrderStatusResponseDto toResponse(OrderStatus orderStatus) {
        return OrderStatusResponseDto.builder()
                .id(orderStatus.getId())
                .name(orderStatus.getStatus()).build();
    }
}
