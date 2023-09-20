package ra.service.impl.imageDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.model.dto.request.ImagesProductRequestDto;
import ra.model.dto.response.ImageProductResponseDto;
import ra.model.entity.ImageDetails;
import ra.model.entity.Product;
import ra.repository.IImageDetailRepository;
import ra.repository.IProductRepository;
import ra.service.mapper.ImageDetailMapper;
import ra.service.upload_file.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageDetailService implements IImageDetailsService{
    @Autowired
    private IImageDetailRepository imageDetailRepository;
    @Autowired
    private ImageDetailMapper imageDetailMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private StorageService storageService;
    @Override
    public List<ImageProductResponseDto> findAll() {
        return imageDetailRepository.findAll().stream().map(i->imageDetailMapper.toResponse(i)).collect(Collectors.toList());
    }

    @Override
    public ImageProductResponseDto findById(Long id) throws ClassCastException {
        Optional<ImageDetails> imageDetails = imageDetailRepository.findById(id);
        if (imageDetails.isPresent()) {
            return imageDetailMapper.toResponse(imageDetails.get());
        }
        throw new ClassCastException("ImageDetails not found");
    }

    @Override
    public ImageProductResponseDto save(ImagesProductRequestDto imagesProductRequestDto) {
        ImageDetails imageDetails = imageDetailMapper.toEntity(imagesProductRequestDto);
       String url = storageService.uploadFile(imagesProductRequestDto.getFile());
       imageDetails.setImage(url);
        return imageDetailMapper.toResponse(imageDetailRepository.save(imageDetails));
    }

    @Override
    public ImageProductResponseDto update(ImagesProductRequestDto imagesProductRequestDto, Long id) {
        ImageDetails imageDetails = imageDetailMapper.toEntity(imagesProductRequestDto);
        imageDetails.setId(id);
        return imageDetailMapper.toResponse(imageDetailRepository.save(imageDetails));
    }

    @Override
    public ImageProductResponseDto delete(Long id) throws ClassCastException {
        Optional<ImageDetails> imageDetails = imageDetailRepository.findById(id);
        if (imageDetails.isPresent()) {
            imageDetailRepository.deleteById(id);
            return imageDetailMapper.toResponse(imageDetails.get());
        }
      throw new ClassCastException("ImageDetail not found");
    }

    @Override
    public List<ImageProductResponseDto> getAllByProduct(Long id) throws ClassCastException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return imageDetailRepository.findAllByProduct(product.get()).stream().map(i->imageDetailMapper.toResponse(i)).collect(Collectors.toList());
        }
        throw new ClassCastException("Product not found");
    }
}
