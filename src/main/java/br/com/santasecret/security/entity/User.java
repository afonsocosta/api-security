package br.com.santasecret.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "sc_security", name = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5651845976377990969L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "ucode")
    private String ucode;

    @Column(name = "role")
    private String role;

}