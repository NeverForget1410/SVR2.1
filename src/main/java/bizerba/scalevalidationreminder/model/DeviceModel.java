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
@Table(name = "device_model")
public class DeviceModel {

    @Id
    @Column(name = "id_device_model")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDeviceModel;

    @Column(name = "device_model_name")
    private String deviceModelName;

    @Column(name = "device_descryption")
    private  String deviceDescryption;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

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
