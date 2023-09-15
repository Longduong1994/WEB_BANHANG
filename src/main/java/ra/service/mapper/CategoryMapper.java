package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.CategoryRequestDto;
import ra.model.dto.response.CategoryResponseDto;
import ra.model.entity.Category;
import ra.service.IGenericMapper;
@Component
public class CategoryMapper implements IGenericMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category toEntity(CategoryRequestDto categoryRequestDto) {
        return Category.builder().name(categoryRequestDto.getName()).build();
    }

    @Override
    public CategoryResponseDto toResponse(Category category) {
        return CategoryResponseDto.builder().id(category.getId()).name(category.getName()).build();
    }
}
