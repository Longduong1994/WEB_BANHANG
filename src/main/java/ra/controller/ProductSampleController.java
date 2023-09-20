package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ProductSimpleRequestDto;
import ra.model.dto.response.ProductSimpleResponseDto;
import ra.service.impl.productSample.IProductSimpleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/productSample")
public class ProductSampleController {
    @Autowired
    private IProductSimpleService productSimpleService;

    @GetMapping
    public ResponseEntity<List<ProductSimpleResponseDto>> getProductSample() {
        return new ResponseEntity<>(productSimpleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<List<ProductSimpleResponseDto>> getProductDetails(@PathVariable Long id){
        return new ResponseEntity<>(productSimpleService.findAllByProduct(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSimpleResponseDto> findProductSampleById(@PathVariable Long id) {
        return new ResponseEntity<>(productSimpleService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/best/seller")
    public ResponseEntity<ProductSimpleResponseDto> findBestSeller() {
        return new ResponseEntity<>(productSimpleService.productBestSeller(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductSimpleResponseDto> addProductSample(@RequestBody @Valid ProductSimpleRequestDto productSimpleRequestDto) {
        return new ResponseEntity<>(productSimpleService.save(productSimpleRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSimpleResponseDto> updateProductSample(@RequestBody @Valid ProductSimpleRequestDto productSimpleRequestDto, @PathVariable Long id) {
        return new ResponseEntity<>(productSimpleService.update(productSimpleRequestDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSimpleResponseDto> deleteProductSample(@PathVariable Long id) {
        return new ResponseEntity<>(productSimpleService.delete(id), HttpStatus.OK);
    }
}
