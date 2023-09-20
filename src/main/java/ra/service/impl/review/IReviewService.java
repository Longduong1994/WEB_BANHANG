package ra.service.impl.review;

import ra.model.dto.request.ReviewRequestDto;
import ra.model.dto.request.UpdateReviewRequestDto;
import ra.model.dto.response.ReviewResponseDto;
import ra.service.IGenericService;

import java.util.List;

public interface IReviewService extends IGenericService<ReviewResponseDto, ReviewRequestDto,Long> {

    ReviewResponseDto save(ReviewRequestDto reviewRequestDto,Long id);

    List<ReviewResponseDto> findReviewByOrder(Long id);
    ReviewResponseDto update(UpdateReviewRequestDto updateReviewRequestDto,Long id,Long userId);

    ReviewResponseDto delete(Long id,Long userId);
}
