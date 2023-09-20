package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String comment;
    private Double rate;
    private String writer;

    @ManyToOne
    @JoinColumn(name="order_detail_id")
    private Cart cart;
    private boolean status;
}
