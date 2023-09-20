package ra.service.impl.productSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.ProductSimpleRequestDto;
import ra.model.dto.response.ProductSimpleResponseDto;
import ra.model.entity.Product;
import ra.model.entity.ProductSamples;
import ra.repository.IProductRepository;
import ra.repository.IProductSimpleRepository;
import ra.service.mapper.ProductSampleMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSimpleService implements IProductSimpleService {
    @Autowired
    private IProductSimpleRepository productSampleRepository;
    @Autowired
    private ProductSampleMapper productSampleMapper;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<ProductSimpleResponseDto> findAll() {
        return productSampleRepository.findAll().stream().map(w-> productSampleMapper.toResponse(w)).collect(Collectors.toList());
    }

    @Override
    public ProductSimpleResponseDto findById(Long id) throws ClassCastException {
        Optional<ProductSamples> product = productSampleRepository.findById(id);
        if (product.isPresent()){
            return  productSampleMapper.toResponse(product.get());
        }
       throw new ClassCastException("ProductSamples not found");
    }

    @Override
    public ProductSimpleResponseDto save(ProductSimpleRequestDto productSimpleRequestDto) {
        ProductSamples productSamples = productSampleMapper.toEntity(productSimpleRequestDto);
        productSamples.setStatus(true);
        return productSampleMapper.toResponse(productSampleRepository.save(productSamples));

    }

    @Override
    public ProductSimpleResponseDto update(ProductSimpleRequestDto productSimpleRequestDto, Long id) {
        ProductSamples productSamples = productSampleMapper.toEntity(productSimpleRequestDto);
        productSamples.setId(id);
        return productSampleMapper.toResponse(productSampleRepository.save(productSamples));
    }

    @Override
    public ProductSimpleResponseDto delete(Long id) throws ClassCastException{
        Optional<ProductSamples> product = productSampleRepository.findById(id);
        if(product.isPresent()) {
            productSampleRepository.deleteById(id);
            return productSampleMapper.toResponse(product.get());
        }
        throw new ClassCastException("ProductSamples not found");
    }

    @Override
    public ProductSimpleResponseDto changeStatus(Long id) throws ClassCastException {
        Optional<ProductSamples> product = productSampleRepository.findById(id);
        if (product.isPresent()) {
            product.get().setStatus(!product.get().isStatus());
            return productSampleMapper.toResponse(productSampleRepository.save(product.get()));
        }
       throw new ClassCastException("ProductSamples not found");
    }

    @Override
    public List<ProductSimpleResponseDto> findAllByProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return productSampleRepository.findAllByProduct(product.get()).stream().map(p-> productSampleMapper.toResponse(p)).collect(Collectors.toList());
    }

    @Override
    public ProductSimpleResponseDto productBestSeller() {
        ProductSamples productSamples = productSampleRepository.findProductWithMostSales();
        return productSampleMapper.toResponse(productSamples);
    }
}
