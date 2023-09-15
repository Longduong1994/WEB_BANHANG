package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.SizeRequestDto;
import ra.model.dto.response.SizeResponseDto;
import ra.model.entity.Size;
import ra.service.IGenericMapper;
@Component
public class SizeMapper implements IGenericMapper<Size, SizeRequestDto, SizeResponseDto> {
    @Override
    public Size toEntity(SizeRequestDto sizeRequestDto) {
        return Size.builder().name(sizeRequestDto.getName()).build();
    }

    @Override
    public SizeResponseDto toResponse(Size size) {
        return SizeResponseDto.builder().id(size.getId()).name(size.getName()).build();
    }
}
