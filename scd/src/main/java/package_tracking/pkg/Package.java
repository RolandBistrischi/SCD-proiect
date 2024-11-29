package package_tracking.pkg;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import package_tracking.Courier.Courier;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date created_on;
    private String deliveryAddress;

    private boolean pay_on_delivery;
    private Status status=Status.NEW;
    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    private Courier courier;


}
