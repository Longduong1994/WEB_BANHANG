package ra.service.impl.product;

import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.request.UpdateProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.service.IGenericService;

public interface IProductService extends IGenericService<ProductResponseDto, ProductRequestDto,Long > {

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequest,Long id);

}
