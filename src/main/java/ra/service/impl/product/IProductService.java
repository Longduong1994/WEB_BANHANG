package ra.service.impl.product;

import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.model.entity.Product;
import ra.service.IGenericService;

public interface IProductService extends IGenericService<ProductResponseDto, ProductRequestDto,Long > {
}
