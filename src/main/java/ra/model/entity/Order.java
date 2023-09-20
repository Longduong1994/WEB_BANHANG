package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "export_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date exportDate;
    private String address;
    private String phone;
    private String other;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id",referencedColumnName = "id")
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

}
