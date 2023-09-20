package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.Cart;
import ra.model.entity.Payment;
import ra.model.entity.Users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    @NotBlank(message="Cannot be left blank")
    private String address;
    @Pattern(regexp ="\\b\\d{10,11}\\b" ,message = "The phone number is not in the correct format")
    private String phone;
    private String other;
    @NotNull(message= "Please complete the payment method")
    private Long paymentId;
}
