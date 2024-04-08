package bizerba.scalevalidationreminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_descryption")
    private String customerDescryption;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_has_store",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id_store"))
    private Set<Store> stores;
}
