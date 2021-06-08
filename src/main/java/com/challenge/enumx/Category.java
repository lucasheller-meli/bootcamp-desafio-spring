package com.challenge.enumx;

import javax.persistence.criteria.CriteriaBuilder;

public enum Category {

    CADEIRAS(100), TECLADOS(58), MOUSE(27), MESA(12), ILUMINACAO(43);

    private Integer code;

    Category(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
