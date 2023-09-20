package ra.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.OrderRequestDto;
import ra.model.dto.request.UpdateOrderRequestDto;
import ra.model.dto.response.OrderResponseDto;
import ra.model.entity.Order;
import ra.model.entity.OrderStatus;
import ra.model.entity.Payment;
import ra.model.entity.Users;
import ra.repository.IOrderRepository;
import ra.repository.IOrderStatusRepository;
import ra.repository.IPaymentRepository;
import ra.repository.IUserRepository;
import ra.service.impl.orderStatus.IOrderStatusService;
import ra.service.mapper.OrderMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    @Autowired
    private IPaymentRepository paymentRepository;
    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll().stream().map(o->orderMapper.toResponse(o)).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto findById(Long id) throws ClassCastException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return orderMapper.toResponse(order.get());
        }
        throw  new ClassCastException("Order not found");
    }

    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
      return null;
    }

    @Override
    public OrderResponseDto update(OrderRequestDto orderRequestDto, Long id) throws ClassCastException {
        Order order = orderMapper.toEntity(orderRequestDto);
        order.setId(id);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto delete(Long id) throws ClassCastException{
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return orderMapper.toResponse(order.get());
        }
        throw new ClassCastException("Order not found");
    }

    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto, Long id){
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(1);
        Optional<Users> user = userRepository.findById(id);
        Order order = orderMapper.toEntity(orderRequestDto);
        order.setUsers(user.get());
        order.setExportDate(new Date());
        order.setOrderStatus(orderStatus.get());
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto update(UpdateOrderRequestDto updateOrderRequestDto, Long id)throws  ClassCastException{
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(updateOrderRequestDto.getStatusId());
       if (!orderStatus.isPresent()){
           throw new ClassCastException("OrderStatus not found" );
       }
        Optional<Order> order = orderRepository.findById(id);
       if (order.get().getOrderStatus().getStatus().equals("CANCEL")){
           throw  new ClassCastException("Order is canceled");
       }
        if (order.isPresent()) {
            order.get().setOrderStatus(orderStatus.get());
            return orderMapper.toResponse(orderRepository.save(order.get()));
        }
        throw new ClassCastException("Order not found");
    }

    @Override
    public OrderResponseDto cancel(Long id, Long userId) throws ClassCastException {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(5);
        Optional<Users> users = userRepository.findById(userId);
        Optional<Order> order = orderRepository.findById(id);
        if (order.get().getOrderStatus().getStatus().equals("CANCEL")){
            throw new ClassCastException("Order is canceled");
        }
        if (!order.get().getOrderStatus().getStatus().equals("PENDING")||!order.get().getOrderStatus().getStatus().equals("PREPARE")){
            throw new ClassCastException("Order is in progress. You cannot cancel at this time");
        }
        if(orderRepository.existsByUsers(users.get())){
            order.get().setOrderStatus(orderStatus.get());
            return orderMapper.toResponse(orderRepository.save(order.get()));
        }
       throw new ClassCastException("Order not found");
    }

    @Override
    public List<OrderResponseDto> viewOrder(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return orderRepository.findAllByUsers(user.get()).stream().map(o->orderMapper.toResponse(o)).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrder(OrderRequestDto orderRequestDto, Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.get().getOrderStatus().getStatus().equals("CANCEL")){
            throw  new ClassCastException("Order is canceled");
        }
        if (!order.get().getOrderStatus().getStatus().equals("PENDING") && !order.get().getOrderStatus().getStatus().equals("PREPARE")){
            throw new ClassCastException("Order is in progress. You cannot change inform at this time");
        }
        Optional<Payment> payment = paymentRepository.findById(orderRequestDto.getPaymentId());
        if (!payment.isPresent()) {
            throw new ClassCastException("Payment not found");
        }
        order.get().setId(id);
        order.get().setAddress(orderRequestDto.getAddress());
        order.get().setPhone(orderRequestDto.getPhone());
        order.get().setPayment(payment.get());
        orderRepository.save(order.get());
        return orderMapper.toResponse(order.get());
    }


}
