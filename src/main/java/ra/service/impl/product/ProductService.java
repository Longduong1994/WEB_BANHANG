package ra.service.impl.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.request.UpdateProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.model.entity.Brand;
import ra.model.entity.Category;
import ra.model.entity.ImageDetails;
import ra.model.entity.Product;
import ra.repository.IBrandRepository;
import ra.repository.IProductRepository;
import ra.service.mapper.ProductMapper;
import ra.service.upload_file.StorageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StorageService storageService;
    @Autowired
    private IBrandRepository brandRepository;

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
    public ProductResponseDto save(ProductRequestDto productRequestDto) throws ClassCastException{
        if (productRequestDto.getBrand()==null) {
            throw new ClassCastException("Brand not found");
        }

        for (Category category : productRequestDto.getCategories()) {
            if (category==null) {
                throw new ClassCastException("Category not found");
            }
        }
        Product product = productMapper.toEntity(productRequestDto);
        List<String> listUrl = new ArrayList<>();
        for (MultipartFile m : productRequestDto.getProductFiles()) {
            listUrl.add(storageService.uploadFile(m));
        }
        product.setImage(listUrl.get(0));
        List<ImageDetails> images = new ArrayList<>();
        for (String url : listUrl) {
            images.add(ImageDetails.builder().image(url).product(product).build());
        }
        product.setImageDetails(images);
        product.setImportDate(new Date());
        product.setStatus(true);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequest,Long id) {
        if (updateProductRequest.getBrand()==null) {
            throw new ClassCastException("Brand not found");
        }

        for (Category category : updateProductRequest.getCategories()) {
            if (category==null) {
                throw new ClassCastException("Category not found");
            }
        }
        Product product = productMapper.toEntity(updateProductRequest);
        String img = storageService.uploadFile(updateProductRequest.getFiles());
        product.setImage(img);
        product.setId(id);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, Long id) {
        return null;
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
