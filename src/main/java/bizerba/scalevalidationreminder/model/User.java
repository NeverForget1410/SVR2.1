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
@Table(name="user")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id_roli"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_store",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"),
                inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id_store"))
    private Set<Store> stores;



}
