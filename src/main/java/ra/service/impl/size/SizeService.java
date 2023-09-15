package ra.service.impl.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.SizeRequestDto;
import ra.model.dto.response.SizeResponseDto;
import ra.model.entity.Size;
import ra.repository.ISizeRepository;
import ra.service.mapper.SizeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public List<SizeResponseDto> findAll() {
        return sizeRepository.findAll().stream().map(s -> sizeMapper.toResponse(s)).collect(Collectors.toList());
    }

    @Override
    public SizeResponseDto findById(Long id) {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            return sizeMapper.toResponse(size.get());
        }
        return null;
    }

    @Override
    public SizeResponseDto save(SizeRequestDto sizeRequestDto) {
        Size size = sizeMapper.toEntity(sizeRequestDto);
        return sizeMapper.toResponse(sizeRepository.save(size));
    }

    @Override
    public SizeResponseDto update(SizeRequestDto sizeRequestDto, Long id) {
        Size size = sizeMapper.toEntity(sizeRequestDto);
        size.setId(id);
        return sizeMapper.toResponse(sizeRepository.save(size));
    }

    @Override
    public SizeResponseDto delete(Long id) {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            sizeRepository.deleteById(id);
            return sizeMapper.toResponse(size.get());
        }
        return null;
    }
}
