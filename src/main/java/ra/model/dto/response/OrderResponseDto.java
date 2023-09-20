package ra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.Cart;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private String address;
    private String phone;
    private double total;
    private String payment;
    private String status;
    private String buyer;
}
