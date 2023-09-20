package ra.service.impl.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.ColorRequestDto;
import ra.model.dto.response.ColorResponseDto;
import ra.model.entity.Color;
import ra.repository.IColorRepository;
import ra.service.mapper.ColorMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorService implements IColorService {
    @Autowired
    private IColorRepository colorRepository;
    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<ColorResponseDto> findAll() {
        return colorRepository.findAll().stream().map(c -> colorMapper.toResponse((c))).collect(Collectors.toList());
    }

    @Override
    public ColorResponseDto findById(Long id) throws ClassCastException {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return colorMapper.toResponse(color.get());
        }
        throw new ClassCastException("Color not found");
    }

    @Override
    public ColorResponseDto save(ColorRequestDto colorRequestDto) throws ClassCastException {
        if (colorRepository.existsByName(colorRequestDto.getName())){
            throw new ClassCastException("Color already exists");
        }
        Color color = colorMapper.toEntity(colorRequestDto);
        return colorMapper.toResponse(colorRepository.save(color));
    }

    @Override
    public ColorResponseDto update(ColorRequestDto colorRequestDto, Long id) {
        Color color = colorMapper.toEntity(colorRequestDto);
        color.setId(id);
        return colorMapper.toResponse(colorRepository.save(color));
    }

    @Override
    public ColorResponseDto delete(Long id) throws ClassCastException{
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            colorRepository.deleteById(id);
            return colorMapper.toResponse(color.get());
        }
        throw new ClassCastException("Could not find color");
    }
}
