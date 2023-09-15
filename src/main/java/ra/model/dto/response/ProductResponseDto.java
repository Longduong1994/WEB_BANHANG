package ra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String image;
    private double importPrice;
    private double exportPrice;
    private Date importDate;
    private String brand;
    private List<String> colors;
    private List<String> sizes;
    private List<String> categories;

}
