package com.inditex.prices.infrastructure.rest.input.adapter;

import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PriceAPI.class)
class PriceAPITest {

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 35455;
    private static final String endpointFormat = "/api/prices/brands/%d/products/%d";
    @MockBean
    private PriceInputPort priceInputPort;
    @Autowired
    private MockMvc mockMvc;

    //- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_10_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_16_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //- Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_21_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //- Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_10_on_15th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //- Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_21_on_16th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
