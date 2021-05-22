package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This the discriminator for tenants in the system.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Entity
@Table(name = "TENANT")
@ToString(callSuper = true)
public class Tenant extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "TENANT_ID", unique = true, nullable = false)
    private String tenantId;
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

}
