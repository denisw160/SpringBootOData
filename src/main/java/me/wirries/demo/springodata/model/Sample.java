package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * This is a sample model class for this demo.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Entity
@Table(name = "SAMPLE")
@ToString(callSuper = true)
public class Sample extends AbstractTenantAdwareEntity {

    @Getter
    @Setter
    @Column(name = "COL_STRING")
    private String colString;
    @Getter
    @Setter
    @Column(name = "COL_INT")
    private Integer colInt;
    @Getter
    @Setter
    @Column(name = "COL_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date colDate;

}
