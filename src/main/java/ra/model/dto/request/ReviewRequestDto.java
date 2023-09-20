package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {
    @NotNull(message = "Cannot be left blank")
    private Long productId;
    @NotBlank(message = "Cannot be left blank")
    private String comment;
    @NotNull(message = "Cannot be left blank")
    @Range(min = 1, max = 10, message = "Value must be between 1 and 10")
    private Double rate;
}
