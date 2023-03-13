package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PathVariableException extends GlobalHandlerErrorException {

    private Integer index;
    private String pathVariable;

    public PathVariableException(final String code, final String level, final Integer indexConst,
                                 final String pathVariableConst) {
        super(code, level);
        this.index = indexConst;
        this.pathVariable = pathVariableConst;
    }
}
