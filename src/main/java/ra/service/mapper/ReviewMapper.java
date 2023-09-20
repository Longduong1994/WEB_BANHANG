package ra.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.dto.request.ReviewRequestDto;
import ra.model.dto.response.ReviewResponseDto;
import ra.model.entity.Product;
import ra.model.entity.Review;
import ra.repository.IProductRepository;
import ra.service.IGenericMapper;

import java.util.Optional;

@Component
public class ReviewMapper implements IGenericMapper<Review, ReviewRequestDto, ReviewResponseDto> {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Review toEntity(ReviewRequestDto reviewRequestDto) {
        return Review.builder()
                .comment(reviewRequestDto.getComment())
                .rate(reviewRequestDto.getRate()).build();
    }

    @Override
    public ReviewResponseDto toResponse(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rate(review.getRate())
                .writer(review.getWriter())
                .build();
    }
}
