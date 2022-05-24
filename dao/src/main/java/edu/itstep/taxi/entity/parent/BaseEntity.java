package edu.itstep.taxi.entity.parent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@SuppressWarnings("PMD")
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
