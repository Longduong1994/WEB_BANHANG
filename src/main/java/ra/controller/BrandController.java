package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.BrandRequestDto;
import ra.model.dto.response.BrandResponseDto;
import ra.model.dto.response.CategoryResponseDto;
import ra.service.impl.brand.IBrandService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;
    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getBrand(){
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BrandResponseDto> findBrandById(@PathVariable Long id){
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BrandResponseDto> addBrand(@RequestBody @Valid BrandRequestDto brandRequestDto){
        return new ResponseEntity<>(brandService.save(brandRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BrandResponseDto> updateBrand(@RequestBody @Valid BrandRequestDto brandRequestDto,@PathVariable Long id){
        return new ResponseEntity<>(brandService.update(brandRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BrandResponseDto> deleteBrand(@PathVariable Long id){
        return new ResponseEntity<>(brandService.delete(id), HttpStatus.OK);
    }
}
