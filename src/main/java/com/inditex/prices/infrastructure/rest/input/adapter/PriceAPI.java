package com.inditex.prices.infrastructure.rest.input.adapter;

import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/prices")
@Slf4j
public class PriceAPI {

    @Autowired
    private PriceInputPort priceInputPort;

    @GetMapping("/status")
    String status() {
        return "Estoy vivo";
    }


    @GetMapping(value = "/brands/{brand_id}/products/{product_id}")
    ResponseEntity<PriceResponse> getProductPrice(@PathVariable("brand_id")  final Integer branId,
                                                  @PathVariable("product_id") final Integer productId,
                                                  @RequestParam(value = "date") final String date)
        throws MissingServletRequestParameterException, MissingPathVariableException, SQLException {

        log.info("Obtaining the product price init");

        checkMissingPathVariable(branId, productId);
        checkMissingRequestParam(date);

        return ResponseEntity.ok(priceInputPort.obtainProductPrice(branId, productId, date));
    }

    private void checkMissingPathVariable(final Integer branId,
                                          final Integer productId) throws MissingPathVariableException {
        if (branId == null) {
            throw new MissingPathVariableException("brandId", new MethodParameter((Method) null, 0));
        } else if (productId == null) {
            throw new MissingPathVariableException("productId", new MethodParameter((Method) null, 1));
        }
        throw new MissingPathVariableException("brandId OR productId", new MethodParameter((Method) null, -1));
    }

    private void checkMissingRequestParam(final String date) throws MissingServletRequestParameterException {
        if (date == null) {
            throw new MissingServletRequestParameterException("date", "LocalDateTime");
        }
    }
}
