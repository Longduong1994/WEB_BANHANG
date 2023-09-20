package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.CartRequestDto;
import ra.model.dto.request.UpdateCartDto;
import ra.model.dto.response.CartResponseDto;
import ra.service.impl.cart.ICartService;
import ra.service.impl.productSample.IProductSimpleService;
import ra.service.mapper.CartMapper;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/project/cart")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductSimpleService warehouseService;
    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getCart(HttpSession session){
        List<CartResponseDto> carts = (List<CartResponseDto>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> findCartById(@PathVariable Long id){
        return new ResponseEntity<>(cartService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addCart(@RequestBody @Valid CartRequestDto cartRequestDto,HttpSession session) throws ClassCastException{
        List<CartResponseDto> carts = (List<CartResponseDto>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        CartResponseDto cartItem = cartMapper.toResponse(cartMapper.toEntity(cartRequestDto));
        if (cartService.findCartByWarehouse(carts,cartItem.getProductId())!=null) {
            throw new ClassCastException("Products already in the cart ");
        }
        Long newId = cartService.newId(carts);
        cartItem.setId(newId);
        carts.add(cartItem);
        session.setAttribute("carts",carts);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@RequestBody @Valid UpdateCartDto updateCartDto, @PathVariable Long id,HttpSession session) throws ClassCastException{
        List<CartResponseDto> carts = (List<CartResponseDto>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        boolean cartUpdated = false;

        for (CartResponseDto c : carts) {
            if (c.getId() == id) {
                c.setQuantity(updateCartDto.getQuantity());
                cartUpdated = true;
                break;
            }
        }

        if (!cartUpdated) {
            return new ResponseEntity<>("Cart Not Found", HttpStatus.NOT_FOUND);
        }
        session.setAttribute("carts", carts);

        return new ResponseEntity<>("Update Susses", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id,HttpSession session)throws ClassCastException{
        List<CartResponseDto> carts = (List<CartResponseDto>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        CartResponseDto cartToRemove = null;
        for (CartResponseDto c : carts) {
            if (c.getId() == id) {
                cartToRemove = c;
                break;
            }
        }
        if (cartToRemove != null) {
            carts.remove(cartToRemove);
            session.setAttribute("carts", carts);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
