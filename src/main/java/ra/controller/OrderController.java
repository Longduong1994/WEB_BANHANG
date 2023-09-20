package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.OrderRequestDto;
import ra.model.dto.request.UpdateOrderRequestDto;
import ra.model.dto.response.CartResponseDto;
import ra.model.dto.response.OrderResponseDto;
import ra.model.entity.ProductSamples;
import ra.repository.IProductSimpleRepository;
import ra.security.user_principle.UserPrinciple;
import ra.service.impl.cart.ICartService;
import ra.service.impl.order.IOrderService;
import ra.service.mapper.CartMapper;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/project/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductSimpleRepository productRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ICartService cartService;
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrder(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody @Valid OrderRequestDto orderRequestDto, HttpSession session, Authentication authentication)throws ClassCastException{
        UserPrinciple userPrinciple =null;
       try {
         userPrinciple = (UserPrinciple) authentication.getPrincipal();
       }catch (Exception e){
           throw new ClassCastException("You must be logged in to pay");
       }
        Long userId = userPrinciple.getId();
        List<CartResponseDto> carts = (List<CartResponseDto>) session.getAttribute("carts");
        if (carts==null) {
            throw new ClassCastException("Cart is empty");
        }
        for (CartResponseDto c:carts) {
            Optional<ProductSamples> product = productRepository.findById(c.getProductId());
            if (c.getQuantity()>product.get().getStock()){
                throw new ClassCastException(c.getName() + " size "+ c.getSize()+ " color "+ c.getColor() +" quantity is too much");
            }
        }
        Double total = (Double) session.getAttribute("total");
        OrderResponseDto orderResponseDto = orderService.save(orderRequestDto,userId);

        Long newId = orderResponseDto.getId();
        for (CartResponseDto c:carts){
            Optional<ProductSamples> product = productRepository.findById(c.getProductId());
            cartService.save(cartMapper.toRequest(c), newId);
            product.get().setStock(product.get().getStock()-c.getQuantity());
            productRepository.save(product.get());
        }
        session.removeAttribute("carts");
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@RequestBody @Valid OrderRequestDto orderRequestDto, @PathVariable Long id){
        return new ResponseEntity<>(orderService.updateOrder(orderRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id,@RequestBody UpdateOrderRequestDto updateOrderRequestDto){
        return new ResponseEntity<>(orderService.update( updateOrderRequestDto,id), HttpStatus.OK);
    }
}
