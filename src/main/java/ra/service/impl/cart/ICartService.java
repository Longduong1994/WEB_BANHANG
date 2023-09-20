package ra.service.impl.cart;

import ra.model.dto.request.CartRequestDto;
import ra.model.dto.response.CartResponseDto;
import ra.model.entity.Cart;
import ra.service.IGenericMapper;
import ra.service.IGenericService;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ICartService extends IGenericService<CartResponseDto, CartRequestDto,Long> {
    Long newId(List<CartResponseDto> carts);
    CartResponseDto findCartByWarehouse(List<CartResponseDto> carts,Long warehouseId);
    CartResponseDto save(CartRequestDto cartRequestDto,Long id);
    List<CartResponseDto> findAllByOrder(Long id);
 }
