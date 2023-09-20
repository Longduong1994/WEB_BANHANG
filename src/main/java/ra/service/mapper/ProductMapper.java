package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.request.UpdateProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.model.entity.Brand;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.repository.IBrandRepository;
import ra.repository.ICategoryRepository;
import ra.repository.IProductSimpleRepository;
import ra.service.IGenericMapper;
import ra.service.upload_file.StorageService;



@Component
public class ProductMapper implements IGenericMapper<Product, ProductRequestDto, ProductResponseDto> {

    @Override
    public Product toEntity(ProductRequestDto productRequestDto) {
        return Product.builder().name(productRequestDto.getName())
                .importPrice(productRequestDto.getImportPrice())
                .exportPrice(productRequestDto.getExportPrice())
                .brand(productRequestDto.getBrand())
                .categories(productRequestDto.getCategories())
                .build();
    }

    @Override
    public ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getExportPrice())
                .brandName(product.getBrand().getName())
                .categories(product.getCategories())
                .build();
    }

    public Product toEntity(UpdateProductRequestDto updateProductRequestDto) {
        return Product.builder()
                .name(updateProductRequestDto.getName())
                .importPrice(updateProductRequestDto.getImportPrice())
                .exportPrice(updateProductRequestDto.getExportPrice())
                .categories(updateProductRequestDto.getCategories())
                .brand(updateProductRequestDto.getBrand()).build();
    }
}
