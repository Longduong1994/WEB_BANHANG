package ra.service.impl.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.BrandRequestDto;
import ra.model.dto.response.BrandResponseDto;
import ra.model.entity.Brand;
import ra.repository.IBrandRepository;
import ra.service.mapper.BrandMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandResponseDto> findAll() {
        return brandRepository.findAll().stream().map(b -> brandMapper.toResponse(b)).collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto findById(Long id) throws ClassCastException {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            return brandMapper.toResponse(brand.get());
        }
        throw  new ClassCastException("Brand not found");
    }

    @Override
    public BrandResponseDto save(BrandRequestDto brandRequestDto) throws ClassCastException {
        if(brandRepository.existsByName(brandRequestDto.getName())) {
            throw new ClassCastException("Brand already exists");
        }
        Brand brand = brandMapper.toEntity(brandRequestDto);
        return brandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponseDto update(BrandRequestDto brandRequestDto, Long id) {
        Brand brand = brandMapper.toEntity(brandRequestDto);
        brand.setId(id);
        return brandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponseDto delete(Long id) throws ClassCastException {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.findById(id);
            return brandMapper.toResponse(brand.get());
        }
        throw new ClassCastException("Could not find brand");
    }
}
