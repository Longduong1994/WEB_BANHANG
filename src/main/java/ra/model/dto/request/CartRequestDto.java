package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartRequestDto {
    @NotNull(message = "Enter the product sample you want to buy")
    private Long productSampleId;

    private int quantity = 1;
}
