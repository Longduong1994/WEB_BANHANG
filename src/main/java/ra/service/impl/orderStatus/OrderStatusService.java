package ra.service.impl.orderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.OrderStatusRequestDto;
import ra.model.dto.response.OrderStatusResponseDto;
import ra.model.entity.OrderStatus;
import ra.repository.IOrderStatusRepository;
import ra.service.mapper.OrderStatusMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderStatusService implements IOrderStatusService {
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    public List<OrderStatusResponseDto> findAll() {
        return orderStatusRepository.findAll().stream().map(o->orderStatusMapper.toResponse(o)).collect(Collectors.toList());
    }

    @Override
    public OrderStatusResponseDto findById(Integer id)throws ClassCastException {
      Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
      if (orderStatus.isPresent()) {
          return orderStatusMapper.toResponse(orderStatus.get());
      }
        throw new ClassCastException("OrderStatus not found");
    }

    @Override
    public OrderStatusResponseDto save(OrderStatusRequestDto orderStatusRequestDto) {
        OrderStatus orderStatus = orderStatusMapper.toEntity(orderStatusRequestDto);
        return orderStatusMapper.toResponse(orderStatusRepository.save(orderStatus));
    }

    @Override
    public OrderStatusResponseDto update(OrderStatusRequestDto orderStatusRequestDto, Integer id) {
        OrderStatus orderStatus = orderStatusMapper.toEntity(orderStatusRequestDto);
        orderStatus.setId(id);
        return orderStatusMapper.toResponse(orderStatusRepository.save(orderStatus));
    }

    @Override
    public OrderStatusResponseDto delete(Integer id)throws ClassCastException {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
        if (orderStatus.isPresent()) {
            orderStatusRepository.deleteById(id);
            return orderStatusMapper.toResponse(orderStatus.get());
        }
        throw new ClassCastException("OrderStatus not found");
    }
}
