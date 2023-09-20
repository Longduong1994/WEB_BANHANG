package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.OrderStatusRequestDto;
import ra.model.dto.response.OrderStatusResponseDto;
import ra.service.impl.orderStatus.IOrderStatusService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/orderStatus")
public class OrderStatusController {
    @Autowired
    private IOrderStatusService orderStatusService;
    @GetMapping
    public ResponseEntity<List<OrderStatusResponseDto>> getOrderStatus(){
        return new ResponseEntity<>(orderStatusService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusResponseDto> findOrderStatusById(@PathVariable int id){
        return new ResponseEntity<>(orderStatusService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderStatusResponseDto> addOrderStatus(@RequestBody @Valid OrderStatusRequestDto orderStatusRequestDto){
        return new ResponseEntity<>(orderStatusService.save(orderStatusRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusResponseDto> updateOrderStatus(@RequestBody @Valid OrderStatusRequestDto orderStatusRequestDto,@PathVariable int id){
        return new ResponseEntity<>(orderStatusService.update(orderStatusRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderStatusResponseDto> deleteOrderStatus(@PathVariable int id){
        return new ResponseEntity<>(orderStatusService.delete(id), HttpStatus.OK);
    }
}
