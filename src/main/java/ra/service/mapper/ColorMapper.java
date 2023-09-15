package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.request.ColorRequestDto;
import ra.model.dto.response.ColorResponseDto;
import ra.model.entity.Color;
import ra.service.IGenericMapper;

@Component
public class ColorMapper implements IGenericMapper<Color, ColorRequestDto, ColorResponseDto> {
    @Override
    public Color toEntity(ColorRequestDto colorRequestDto) {
        return Color.builder().name(colorRequestDto.getName()).build();
    }

    @Override
    public ColorResponseDto toResponse(Color color) {
        return ColorResponseDto.builder().id(color.getId()).name(color.getName()).build();
    }
}
