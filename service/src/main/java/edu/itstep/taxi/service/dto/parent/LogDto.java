package edu.itstep.taxi.service.dto.parent;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class LogDto extends BaseDto{

    private String dateCreation;

    private String lastModified;

}
