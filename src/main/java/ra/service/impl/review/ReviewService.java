package ra.service.impl.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.ReviewRequestDto;
import ra.model.dto.request.UpdateReviewRequestDto;
import ra.model.dto.response.ReviewResponseDto;
import ra.model.entity.*;
import ra.repository.*;
import ra.service.mapper.ReviewMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductSimpleRepository productSimpleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Override
    public List<ReviewResponseDto> findAll() {
        return reviewRepository.findAll().stream().map(r->reviewMapper.toResponse(r)).collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto findById(Long id) throws ClassCastException{
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            return reviewMapper.toResponse(review.get());
        }
        throw new ClassCastException("Review not found");
    }

    @Override
    public ReviewResponseDto save(ReviewRequestDto reviewRequestDto) {

        Review review = reviewMapper.toEntity(reviewRequestDto);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDto update(ReviewRequestDto reviewRequestDto, Long id) {
        Review review = reviewMapper.toEntity(reviewRequestDto);
        review.setId(id);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDto delete(Long id)throws ClassCastException{
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.deleteById(id);
            return reviewMapper.toResponse(review.get());
        }
        throw new ClassCastException("Couldn't find review'");
    }

    @Override
    public ReviewResponseDto save(ReviewRequestDto reviewRequestDto, Long id) throws ClassCastException {
        Optional<ProductSamples> productSamples = productSimpleRepository.findById(reviewRequestDto.getProductId());
        if (!productSamples.isPresent()) {
            throw new ClassCastException("ProductSamples is not available");
        }
        Optional<Users> users = userRepository.findById(id);
        List<Cart> carts = cartRepository.findByProductSamples(productSamples.get());
        if (carts == null) {
            throw new ClassCastException("You have not purchased this product yet so you cannot review it");
        }
        for (Cart c: carts) {
            Optional<Order> order = orderRepository.findById(c.getOrder().getId());
            if (order.get().getUsers().getId().equals(id) && order.get().getOrderStatus().getStatus().equals("SUCCESS")) {
                Review review = reviewMapper.toEntity(reviewRequestDto);
                review.setWriter(users.get().getFullName());
                review.setCart(c);
                return reviewMapper.toResponse(reviewRepository.save(review));
            }
        }

        throw new ClassCastException("You have not purchased this product yet so you cannot review it");
    }

    @Override
    public List<ReviewResponseDto> findReviewByOrder(Long id) {
        Users user = userRepository.findById(id).get();
        return reviewRepository.findAllByWriter(user.getFullName()).stream().map(r->reviewMapper.toResponse(r)).collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto update(UpdateReviewRequestDto updateReviewRequestDto,Long id, Long userId) throws ClassCastException{
        Users user = userRepository.findById(userId).get();
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()){
            if (!review.get().getWriter().equals(user.getFullName())){
                throw  new ClassCastException("You cannot edit this review");
            }
            review.get().setId(id);
            review.get().setComment(updateReviewRequestDto.getComment());
            review.get().setRate(updateReviewRequestDto.getRate());
            return reviewMapper.toResponse(reviewRepository.save(review.get()));
        }
        throw new ClassCastException("Review not found");
    }

    @Override
    public ReviewResponseDto delete(Long id, Long userId)throws ClassCastException {
        Users user = userRepository.findById(userId).get();
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            if (!review.get().getWriter().equals(user.getFullName())){
                throw new ClassCastException("You cannot delete this review");
            }
            reviewRepository.deleteById(id);
            return reviewMapper.toResponse(review.get());
        }
        throw new ClassCastException("Review not found");
    }
}
