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
@Table(name="logs")
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLog;

    @Column(name = "log_date")
    private Date logDate;

    @Column(name = "log_id_record")
    private Integer logIdRecord;

    @Column(name = "log_content")
    private String logContent;

    @Column(name = "log_change_recognition")
    private String logChangeRecognition;

    @Column(name = "log_table_name")
    private String logTableName;
}
