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
@Table(name="address")
public class Address {

    @Id
    @Column(name = "id_address",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAddress;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_no")
    private String addressNo;

    @Column(name = "address_descryption")
    private String addressDescryption;

    @Column(name = "city_zip_code")
    private String cityZipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

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
}
