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
@Table(name="role")
public class Role {

    @Id
    @Column(name = "id_roli")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoli;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_descryption")
    private  String roleDescryption;

    @Column(name = "CanSELECT")
    private String CanSelect;

    @Column(name = "CanINSERT")
    private String CanInsert;

    @Column(name = "CanUPDATE")
    private Integer CanUpdate;

    @Column(name = "added_id")
    private Integer addedId;

    @Column(name = "date_addition")
    private Date dateAddition;

    @Column(name = "modified_id")
    private Integer modifiedId;

    @Column(name = "date_modyfication" )
    private Date dateModyfication;

    @Column(name = "active")
    private Integer active;


}
