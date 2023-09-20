package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.ProductSimpleRequestDto;
import ra.model.dto.response.ProductDetailResponseDto;
import ra.model.dto.response.ProductSimpleResponseDto;
import ra.model.entity.*;
import ra.repository.IColorRepository;
import ra.repository.IImageDetailRepository;
import ra.repository.IProductRepository;
import ra.repository.ISizeRepository;
import ra.service.IGenericMapper;
import ra.service.impl.imageDetails.IImageDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductSampleMapper implements IGenericMapper<ProductSamples, ProductSimpleRequestDto, ProductSimpleResponseDto> {
  @Autowired
  private IProductRepository productRepository;
  @Autowired
  private IColorRepository colorRepository;
  @Autowired
  private ISizeRepository sizeRepository;
  @Autowired
  private IImageDetailRepository imageDetailRepository;
    @Override
    public ProductSamples toEntity(ProductSimpleRequestDto productSimpleRequestDto) {
        Optional<Product> product = productRepository.findById(productSimpleRequestDto.getProductId());
        Optional<Color> color = colorRepository.findById(productSimpleRequestDto.getColorId());
        Optional<Size> size = sizeRepository.findById(productSimpleRequestDto.getSizeId());
        return ProductSamples.builder().product(product.get())
                .color(color.get())
                .size(size.get())
                .stock(productSimpleRequestDto.getStock()).build();
    }

    @Override
    public ProductSimpleResponseDto toResponse(ProductSamples productSamples) {
        List<String> images = productSamples.getProduct()
                .getImageDetails()
                .stream()
                .map(ImageDetails::getImage)
                .collect(Collectors.toList());

        return ProductSimpleResponseDto.builder()
                .id(productSamples.getId())
                .productName(productSamples.getProduct().getName())
                .listImages(images)
                .colorName(productSamples.getColor().getName())
                .sizeName(productSamples.getSize().getName())
                .stock(productSamples.getStock())
                .status(productSamples.isStatus()).build();
    }
}
