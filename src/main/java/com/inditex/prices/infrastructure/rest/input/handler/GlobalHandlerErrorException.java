package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GlobalHandlerErrorException extends RuntimeException {
    private String code;
    private String level;
}
