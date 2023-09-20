package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ImagesProductRequestDto;
import ra.model.dto.response.ImageProductResponseDto;
import ra.service.impl.imageDetails.IImageDetailsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/image")
public class ImageProductController {
    @Autowired
    private IImageDetailsService iImageDetailsService;
    @GetMapping
    public ResponseEntity<List<ImageProductResponseDto>> getImage(){
        return new ResponseEntity<>(iImageDetailsService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ImageProductResponseDto> findImageById(@PathVariable Long id){
        return new ResponseEntity<>(iImageDetailsService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ImageProductResponseDto>> getImageProduct(@PathVariable Long id){
        return new ResponseEntity<>(iImageDetailsService.getAllByProduct(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageProductResponseDto> addImage(@ModelAttribute @Valid ImagesProductRequestDto imageProductRequestDto){
        return new ResponseEntity<>(iImageDetailsService.save(imageProductRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageProductResponseDto> updateImage(@ModelAttribute @Valid ImagesProductRequestDto imageProductRequestDto,@PathVariable Long id){
        return new ResponseEntity<>(iImageDetailsService.update(imageProductRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ImageProductResponseDto> deleteImage(@PathVariable Long id){
        return new ResponseEntity<>(iImageDetailsService.delete(id), HttpStatus.OK);
    }
}
