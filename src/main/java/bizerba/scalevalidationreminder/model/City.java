package bizerba.scalevalidationreminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="city")
public class City {

    @Id
    @Column(name = "id_city")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCity;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "added_id")
    private Integer addedId;

    @Column(name = "date_addition")
    private Date dateAddition;

    @Column(name = "modified_id")
    private Integer modifiedId;

    @Column(name = "date_modyfication")
    private Date dateModyfication;

    @Column(name = "active")
    private Integer active;


    @PrePersist
    public void prePersist() {
        this.dateAddition = new Date();
    }


}
