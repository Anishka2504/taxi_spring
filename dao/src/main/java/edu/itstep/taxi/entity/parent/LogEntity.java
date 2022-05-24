package edu.itstep.taxi.entity.parent;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@SuppressWarnings("PMD")
@MappedSuperclass
public abstract class LogEntity extends BaseEntity {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation", updatable = false, insertable = false)
    private Date dateCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified", insertable = false)
    private Date lastModified;
}
