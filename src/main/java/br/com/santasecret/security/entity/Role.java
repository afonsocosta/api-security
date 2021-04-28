package br.com.santasecret.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "oauth", name = "role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -5651845976377990969L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name = "name")
    private String name;

}