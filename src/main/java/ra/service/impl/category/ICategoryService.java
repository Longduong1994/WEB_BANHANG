package ra.service.impl.category;

import ra.model.dto.request.CategoryRequestDto;
import ra.model.dto.response.CategoryResponseDto;
import ra.model.entity.Category;
import ra.service.IGenericService;

public interface ICategoryService extends IGenericService<CategoryResponseDto, CategoryRequestDto,Long > {
}
