package ra.service.impl.orderStatus;

import ra.model.dto.request.OrderStatusRequestDto;
import ra.model.dto.response.OrderStatusResponseDto;
import ra.service.IGenericService;

public interface IOrderStatusService extends IGenericService<OrderStatusResponseDto, OrderStatusRequestDto,Integer> {
}
