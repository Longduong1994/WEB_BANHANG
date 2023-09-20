package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.SizeRequestDto;
import ra.model.dto.response.SizeResponseDto;
import ra.repository.ISizeRepository;
import ra.service.impl.size.ISizeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/size")
public class SizeController {
    @Autowired
    private ISizeService sizeService;
    @GetMapping
    public ResponseEntity<List<SizeResponseDto>> getSize(){
        return new ResponseEntity<>(sizeService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SizeResponseDto> findSizeById(@PathVariable Long id){
        return new ResponseEntity<>(sizeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SizeResponseDto> addSize(@RequestBody @Valid SizeRequestDto sizeRequestDto){
        return new ResponseEntity<>(sizeService.save(sizeRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeResponseDto> updateSize(@RequestBody @Valid SizeRequestDto sizeRequestDto,@PathVariable Long id){
        return new ResponseEntity<>(sizeService.update(sizeRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SizeResponseDto> deleteSize(@PathVariable Long id){
        return new ResponseEntity<>(sizeService.delete(id), HttpStatus.OK);
    }
}
