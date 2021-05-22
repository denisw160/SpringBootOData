package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A role for the user.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Entity
@Table(name = "ROLE")
@ToString(callSuper = true)
public class Role extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    private String roleId;
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

}
