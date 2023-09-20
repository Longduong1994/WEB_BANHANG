package ra.model.dto.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import ra.model.entity.Brand;
import ra.model.entity.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    @NotBlank(message = "Cannot be left blank")
    private String name;
    @NotNull(message = "Cannot be left blank")
    private List<MultipartFile> productFiles;
    @NotNull(message = "Cannot be left blank")
    @Min(value = 1, message = "price must be greater than 0 ")
    private double importPrice;
    @NotNull(message = "Cannot be left blank")
    @Min(value = 1, message = "price must be greater than 0 ")
    private double exportPrice;
    @NotNull(message = "Enter Category")
    private List<Category> categories;
    private Brand brand;
}
