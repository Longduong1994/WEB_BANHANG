package ra.service.impl.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exception.CategoryException;
import ra.model.dto.request.CategoryRequestDto;
import ra.model.dto.response.CategoryResponseDto;
import ra.model.entity.Category;
import ra.repository.ICategoryRepository;
import ra.service.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream().map(c -> categoryMapper.toResponse(c)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            return categoryMapper.toResponse(category.get());
        }
        return null;
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) throws ClassCastException {
        if (categoryRepository.existsByName(categoryRequestDto.getName())){
            throw new ClassCastException("Category already exists");
        }
        Category catalog = categoryMapper.toEntity(categoryRequestDto);
        return categoryMapper.toResponse(categoryRepository.save(catalog));
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id) {
        Category catalog = categoryMapper.toEntity(categoryRequestDto);
        catalog.setId(id);
        return categoryMapper.toResponse(categoryRepository.save(catalog));
    }

    @Override
    public CategoryResponseDto delete(Long id) {
        Optional<Category> catalog = categoryRepository.findById(id);
        if(catalog.isPresent()) {
            categoryRepository.deleteById(id);
            return categoryMapper.toResponse(catalog.get());
        }
        return null;
    }
}
