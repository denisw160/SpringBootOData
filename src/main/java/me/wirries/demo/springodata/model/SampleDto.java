package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * An DTO for editing a {@link Sample}.
 *
 * @author denisw
 * @version 1.0
 * @since 24.05.21
 */
@Getter
@Setter
@ToString
public class SampleDto implements Serializable {

    private String uuid;
    private String colString;
    private Integer colInt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date colDate;
    private TenantDto tenant;

    @Getter
    @Setter
    @ToString
    public static class TenantDto {
        private String uuid;
    }
}
