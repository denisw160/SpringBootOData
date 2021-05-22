package me.wirries.demo.springodata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * A user in the system.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Entity
@Table(name = "USER")
@ToString(callSuper = true)
public class User extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "USER_ID", unique = true, nullable = false)
    private String userId;
    @Getter
    @Setter
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Getter
    @Setter
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Getter
    @Setter
    @Column(name = "LAST_NAME")
    private String lastName;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "DISABLED", length = 5, nullable = false)
    private BooleanType disabled = BooleanType.FALSE;
    @Getter
    @Setter
    @ManyToMany
    private List<Role> roles;
    @Getter
    @Setter
    @ManyToMany
    private List<Tenant> tenants;

}
