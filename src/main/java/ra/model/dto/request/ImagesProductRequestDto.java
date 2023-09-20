package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Product;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImagesProductRequestDto {
    private MultipartFile file;
    private Product product;
}
