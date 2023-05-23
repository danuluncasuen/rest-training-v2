package com.endava.restraining.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;
    
    @OneToOne
    @JoinColumn(name = "workplace")
    private WorkplaceEntity workplace;
    
}
