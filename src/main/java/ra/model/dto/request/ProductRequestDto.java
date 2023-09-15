package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private String name;
    private String image;
    private double importPrice;
    private double exportPrice;
    private Date importDate;
    private List<String> categories;
    private List<String> colors;
    private List<String> sizes;
    private Long brand_id;
}
