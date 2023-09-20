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
    public SizeResponseDto findById(Long id) throws ClassCastException {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            return sizeMapper.toResponse(size.get());
        }
        throw new ClassCastException("Size not found");
    }

    @Override
    public SizeResponseDto save(SizeRequestDto sizeRequestDto)throws ClassCastException {
        if (sizeRepository.existsByName(sizeRequestDto.getName())){
            throw new ClassCastException("Size already exists");
        }
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
    public SizeResponseDto delete(Long id) throws ClassCastException{
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            sizeRepository.deleteById(id);
            return sizeMapper.toResponse(size.get());
        }
       throw new ClassCastException("Could not find size");
    }
}
