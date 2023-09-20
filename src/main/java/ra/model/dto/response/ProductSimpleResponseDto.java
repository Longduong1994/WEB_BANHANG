package ra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.ImageDetails;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSimpleResponseDto {
    private Long id;
    private String productName;
    private List<String> listImages;
    private String colorName;
    private String sizeName;
    private Long stock;
    private boolean status;
}
