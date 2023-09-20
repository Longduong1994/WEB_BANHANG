package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSimpleRequestDto {
    @NotNull(message = "Enter the product Id")
    private Long productId;
    @NotNull(message = "Enter the color Id")
    private Long colorId;
    @NotNull(message = "Enter the size Id")
    private Long sizeId;
    @NotNull(message = "Enter the stock")
    @Min(value = 1, message = "Stock must be greater than 0 ")
    private Long stock;
}
