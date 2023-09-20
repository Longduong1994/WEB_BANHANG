package ra.service.impl.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.CartRequestDto;
import ra.model.dto.response.CartResponseDto;
import ra.model.entity.Cart;
import ra.model.entity.Order;
import ra.repository.ICartRepository;
import ra.repository.IOrderRepository;
import ra.service.mapper.CartMapper;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IOrderRepository orderRepository;
    @Override
    public List<CartResponseDto> findAll() {
        return cartRepository.findAll().stream().map(c->cartMapper.toResponse(c)).collect(Collectors.toList());
    }

    @Override
    public CartResponseDto findById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return cartMapper.toResponse(cart.get());
        }
        return null;
    }

    @Override
    public CartResponseDto save(CartRequestDto cartRequestDto) {
        Cart cart = cartMapper.toEntity(cartRequestDto);
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto update(CartRequestDto cartRequestDto, Long id) {
        Cart cart = cartMapper.toEntity(cartRequestDto);
        cart.setId(id);
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto delete(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()){
            cartRepository.deleteById(id);
            return cartMapper.toResponse(cart.get());
        }
        return null;
    }

    @Override
    public Long newId(List<CartResponseDto> carts) {
        // Tìm ID lớn nhất trong danh sách
        Long maxId = carts.stream()
                .map(CartResponseDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        return maxId + 1;
    }

    @Override
    public CartResponseDto findCartByWarehouse(List<CartResponseDto> carts,Long productId) {
        for (CartResponseDto c:carts) {
            if (c.getProductId() == productId){
                return c;
            }
        }
        return null;
    }

    @Override
    public CartResponseDto save(CartRequestDto cartRequestDto, Long id) {
        Optional<Order> order = orderRepository.findById(id);
        Cart cart = cartMapper.toEntity(cartRequestDto);
        cart.setOrder(order.get());
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    public List<CartResponseDto> findAllByOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return cartRepository.findAllByOrder(order.get()).stream().map(c->cartMapper.toResponse(c)).collect(Collectors.toList());
    }

}
