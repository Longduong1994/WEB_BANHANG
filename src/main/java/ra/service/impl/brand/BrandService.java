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
    public BrandResponseDto findById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            return brandMapper.toResponse(brand.get());
        }
        return null;
    }

    @Override
    public BrandResponseDto save(BrandRequestDto brandRequestDto) {
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
    public BrandResponseDto delete(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.findById(id);
            return brandMapper.toResponse(brand.get());
        }
        return null;
    }
}
