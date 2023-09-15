package ra.model.dto.request;

import java.util.Date;
import java.util.List;

public class OrderRequestDto {
    private Date exportDate;
    private double totalPrice;
    private Long userId;
    private List<String> cartItems;
}
