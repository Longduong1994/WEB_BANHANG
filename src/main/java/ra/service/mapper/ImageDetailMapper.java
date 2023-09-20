package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.ImagesProductRequestDto;
import ra.model.dto.response.ImageProductResponseDto;
import ra.model.entity.ImageDetails;
import ra.service.IGenericMapper;
@Component
public class ImageDetailMapper implements IGenericMapper<ImageDetails, ImagesProductRequestDto, ImageProductResponseDto> {

    @Override
    public ImageDetails toEntity(ImagesProductRequestDto imagesProductRequestDto) {
        return ImageDetails.builder()
                .product(imagesProductRequestDto.getProduct()).build();
    }

    @Override
    public ImageProductResponseDto toResponse(ImageDetails imageDetails) {
        return ImageProductResponseDto.builder()
                .id(imageDetails.getId())
                .image(imageDetails.getImage()).build();
    }
}
