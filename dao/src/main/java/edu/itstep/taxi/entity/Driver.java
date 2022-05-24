package edu.itstep.taxi.entity;

import edu.itstep.taxi.entity.parent.BaseEntity;
import edu.itstep.taxi.entity.parent.LogEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class Driver extends LogEntity implements Serializable {

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(length = 100)
    private String name;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @Column(name = "licence", length = 100)
    private String driverLicence;

}
