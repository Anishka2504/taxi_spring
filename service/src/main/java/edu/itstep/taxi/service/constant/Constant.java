package edu.itstep.taxi.service.constant;

import javax.persistence.criteria.CriteriaBuilder;

public class Constant {

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    public static final String TIME_PATTERN = "HH.mm.ss";

    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " +  TIME_PATTERN;

    public static final Integer PAGE_SIZE = 5;

    public static final String SIZE = String.valueOf(PAGE_SIZE);
}
