package ra.service.impl.order;

import ra.model.dto.request.OrderRequestDto;
import ra.model.dto.request.UpdateOrderRequestDto;
import ra.model.dto.response.OrderResponseDto;
import ra.model.entity.Users;
import ra.service.IGenericService;

import java.util.List;

public interface IOrderService extends IGenericService<OrderResponseDto, OrderRequestDto,Long> {
    OrderResponseDto save(OrderRequestDto orderRequestDto, Long id);
    OrderResponseDto update(UpdateOrderRequestDto updateOrderRequestDto, Long id);
    OrderResponseDto cancel(Long id, Long userId);
    List<OrderResponseDto> viewOrder(Long id);
    OrderResponseDto updateOrder(OrderRequestDto orderRequestDto,Long id);
}
