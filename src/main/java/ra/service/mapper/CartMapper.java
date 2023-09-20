package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.CartRequestDto;
import ra.model.dto.response.CartResponseDto;
import ra.model.entity.*;
import ra.repository.IColorRepository;
import ra.repository.IProductRepository;
import ra.repository.ISizeRepository;
import ra.repository.IProductSimpleRepository;
import ra.service.IGenericMapper;

import java.util.Optional;

@Component
public class CartMapper implements IGenericMapper<Cart, CartRequestDto, CartResponseDto> {
    @Autowired
    private IProductRepository productDetailRepository;
    @Autowired
    private IProductSimpleRepository productRepository;
    @Autowired
    private IColorRepository colorRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Override
    public Cart toEntity(CartRequestDto cartRequestDto)throws ClassCastException {
        Optional<ProductSamples> product = productRepository.findById(cartRequestDto.getProductSampleId());
        if (product.isPresent()){
            return Cart.builder().productSamples(product.get())
                    .quantity(cartRequestDto.getQuantity()).build();
        }
        throw new ClassCastException("ProductSamples not found");
    }

    @Override
    public CartResponseDto toResponse(Cart cart) {
        return CartResponseDto.builder()
                .id(cart.getId())
                .productId(cart.getProductSamples().getId())
                .name(cart.getProductSamples().getProduct().getName())
                .quantity(cart.getQuantity())
                .price(cart.getProductSamples().getProduct().getExportPrice())
                .color(cart.getProductSamples().getColor().getName())
                .size(cart.getProductSamples().getSize().getName()).build();
    }

    public CartRequestDto toRequest(CartResponseDto cartResponseDto){
        return CartRequestDto.builder()
                .productSampleId(cartResponseDto.getProductId())
                .quantity(cartResponseDto.getQuantity())
                .build();
    }

}
