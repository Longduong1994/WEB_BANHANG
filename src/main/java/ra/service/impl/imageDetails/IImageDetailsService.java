package ra.service.impl.imageDetails;

import ra.model.dto.request.ImagesProductRequestDto;
import ra.model.dto.response.ImageProductResponseDto;
import ra.service.IGenericService;

import java.util.List;

public interface IImageDetailsService extends IGenericService<ImageProductResponseDto, ImagesProductRequestDto,Long> {
    List<ImageProductResponseDto>  getAllByProduct(Long id);
}
