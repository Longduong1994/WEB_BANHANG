package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.model.entity.Product;
import ra.repository.IBrandRepository;
import ra.repository.ICategoryRepository;
import ra.repository.IColorRepository;
import ra.repository.ISizeRepository;
import ra.service.IGenericMapper;
@Component
public class ProductMapper implements IGenericMapper<Product, ProductRequestDto, ProductResponseDto> {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IColorRepository colorRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Override
    public Product toEntity(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public ProductResponseDto toResponse(Product product) {
        return null;
    }
}
