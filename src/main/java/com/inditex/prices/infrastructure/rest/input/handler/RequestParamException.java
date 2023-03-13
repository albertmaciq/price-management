package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestParamException extends GlobalHandlerErrorException {

    private String param;

    public RequestParamException(final String code, final String level, final String paramConst) {
        super(code, level);
        this.param = paramConst;
    }
}
