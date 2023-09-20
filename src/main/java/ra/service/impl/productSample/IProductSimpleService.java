package ra.service.impl.productSample;

import ra.model.dto.request.ProductSimpleRequestDto;
import ra.model.dto.response.ProductSimpleResponseDto;
import ra.service.IGenericService;

import java.util.List;

public interface IProductSimpleService extends IGenericService<ProductSimpleResponseDto, ProductSimpleRequestDto,Long> {
    ProductSimpleResponseDto changeStatus(Long id);
    List<ProductSimpleResponseDto> findAllByProduct(Long id);
    ProductSimpleResponseDto productBestSeller();
}
