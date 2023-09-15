package ra.service.impl.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.model.entity.Product;
import ra.repository.IProductRepository;
import ra.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream().map(p -> productMapper.toResponse(p)).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productMapper.toResponse(product.get());
        }
        return null;
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, Long id) {
        Product product = productMapper.toEntity(productRequestDto);
        product.setId(id);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDto delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return productMapper.toResponse(product.get());
        }
        return null;
    }
}
