package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ColorRequestDto;
import ra.model.dto.response.ColorResponseDto;
import ra.service.impl.color.IColorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/color")
public class ColorController {
    @Autowired
    private IColorService colorService;
    @GetMapping
    public ResponseEntity<List<ColorResponseDto>> getColor(){
        return new ResponseEntity<>(colorService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ColorResponseDto> findColorById(@PathVariable Long id){
        return new ResponseEntity<>(colorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorResponseDto> addColor(@RequestBody @Valid ColorRequestDto colorrequestDto){
        return new ResponseEntity<>(colorService.save(colorrequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorResponseDto> updateColor(@RequestBody @Valid ColorRequestDto colorrequestDto,@PathVariable Long id){
        return new ResponseEntity<>(colorService.update(colorrequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ColorResponseDto> deleteColor(@PathVariable Long id){
        return new ResponseEntity<>(colorService.delete(id), HttpStatus.OK);
    }
}
