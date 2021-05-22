package me.wirries.demo.springodata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the base class of an entity. An entity is identified by the uuid.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@ToString
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    /**
     * The uuid of the entity. This uuid is unique and the
     * identifier of the entity - also for equals and hashCode.
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UUID")
    private String uuid;

    // Audit information
    @Getter
    @Setter
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @CreatedDate
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Getter
    @Setter
    @ToString.Exclude
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Getter
    @Setter
    @ToString.Exclude
    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    @Getter
    @Setter
    @ToString.Exclude
    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;

}
