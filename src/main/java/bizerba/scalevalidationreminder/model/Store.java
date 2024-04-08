package bizerba.scalevalidationreminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="store")
public class Store {

    @Id
    @Column(name = "id_store")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStore;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_no")
    private String storeNo;

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



}
