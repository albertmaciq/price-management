package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalHandlerErrorResponse {
    private String code;
    private String level;
    private String message;
    private String description;
}
