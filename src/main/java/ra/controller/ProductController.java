package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ProductRequestDto;
import ra.model.dto.request.UpdateProductRequestDto;
import ra.model.dto.response.ProductResponseDto;
import ra.service.impl.product.IProductService;
import ra.service.impl.productSample.IProductSimpleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductSimpleService warehouseService;
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProduct(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
   public ResponseEntity<ProductResponseDto> addProduct(@ModelAttribute @Valid ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.save(productRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@ModelAttribute @Valid UpdateProductRequestDto updateProductRequestDto, @PathVariable Long id){
        return new ResponseEntity<>(productService.updateProduct(updateProductRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

}
