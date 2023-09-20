package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Brand;
import ra.model.entity.Category;

import javax.validation.constraints.Min;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequestDto {
    private String name;
    private MultipartFile files;
    @Min(value = 1, message = "price must be greater than 0 ")
    private double importPrice;
    @Min(value = 1, message = "price must be greater than 0 ")
    private double exportPrice;
    private List<Category> categories;
    private Brand brand;
}
