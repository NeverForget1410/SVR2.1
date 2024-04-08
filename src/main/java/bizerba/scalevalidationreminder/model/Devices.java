package bizerba.scalevalidationreminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="devices")
public class Devices {

    @Id
    @Column(name = "id_device")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevice;

    @ManyToOne
    @JoinColumn(name = "device_model_id")
    private DeviceModel deviceModel;

    @Column(name = "device_serial_no")
    private String deviceSerialNo;

    @Column(name = "device_date_legalization")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deviceDateLegalization;

    @Column(name = "device_date_next_legalization")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deviceDateNextLegalization;

    @Column(name = "device_range_weight_max")
    private String deviceRangeWeightMax;

    @Column(name = "device_range_weight_min")
    private String deviceRangeWeightMin;

    @Column(name = "device_value_legalization_plot_e")
    private String deviceValueLegalizationPlotE;

    @Column(name = "device_value_plot_reading_d")
    private String deviceValuePlotReadingD;

    @ManyToOne
    @JoinColumn(name = "weight_class_id")
    private WeightClass weightClass;

    @Column(name = "device_border_range_tare")
    private String deviceBorderRangeTare;

    @Column(name = "device_conformity")
    private String deviceConformity;

    @Column(name = "device_descryption")
    private String deviceDescryption;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

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
