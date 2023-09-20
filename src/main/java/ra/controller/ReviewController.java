package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ReviewRequestDto;
import ra.model.dto.response.ReviewResponseDto;
import ra.security.user_principle.UserPrinciple;
import ra.service.impl.review.IReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/review")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReview(){
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> findReviewById(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto, Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to pay");
        }
        Long userId = userPrinciple.getId();
        return new ResponseEntity<>(reviewService.save(reviewRequestDto,userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto,@PathVariable Long id){
        return new ResponseEntity<>(reviewService.update(reviewRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.delete(id), HttpStatus.OK);
    }
}
