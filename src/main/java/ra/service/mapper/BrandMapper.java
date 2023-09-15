package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.BrandRequestDto;
import ra.model.dto.response.BrandResponseDto;
import ra.model.entity.Brand;
import ra.service.IGenericMapper;
@Component
public class BrandMapper implements IGenericMapper<Brand, BrandRequestDto, BrandResponseDto> {
    @Override
    public Brand toEntity(BrandRequestDto brandRequestDto) {
        return Brand.builder().name(brandRequestDto.getName()).build();
    }

    @Override
    public BrandResponseDto toResponse(Brand brand) {
        return BrandResponseDto.builder().id(brand.getId()).name(brand.getName()).build();
    }
}
