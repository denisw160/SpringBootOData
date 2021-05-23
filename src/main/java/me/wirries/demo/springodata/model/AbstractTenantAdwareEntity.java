package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * This is the base class for all entity with tenant dependency.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@MappedSuperclass
@ToString(callSuper = true)
public abstract class AbstractTenantAdwareEntity extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_UUID")
    private Tenant tenant;

}
