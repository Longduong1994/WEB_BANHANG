package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.response.UserResponseDto;
import ra.service.impl.user.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/project/admin")
public class AdminController {
    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> lock(@PathVariable Long id){
        return new ResponseEntity<>(userService.lock(id), HttpStatus.OK);
    }

}
