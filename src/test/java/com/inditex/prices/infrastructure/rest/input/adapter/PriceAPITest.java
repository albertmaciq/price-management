package com.inditex.prices.infrastructure.rest.input.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.rest.input.handler.GlobalHandlerErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({ @Sql(value = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(value = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) })
class PriceAPITest {

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 35455;
    private static final String endpointFormat = "/api/prices/brands/%d/products/%d";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    //- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_10_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PriceResponse response = objectMapper.readValue(responseSt, PriceResponse.class);

        then(response).isNotNull();
        then(response.getBrandId()).isEqualTo(1);
        then(response.getProductId()).isEqualTo(35455);
        then(response.getPrice()).isEqualTo(new BigDecimal("35.50"));
        then(response.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
        then(response.getEndDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
    }

    //- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_16_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PriceResponse response = objectMapper.readValue(responseSt, PriceResponse.class);

        then(response).isNotNull();
        then(response.getBrandId()).isEqualTo(1);
        then(response.getProductId()).isEqualTo(35455);
        then(response.getPrice()).isEqualTo(new BigDecimal("25.45"));
        then(response.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-14T15:00:00"));
        then(response.getEndDate()).isEqualTo(LocalDateTime.parse("2020-06-14T18:30:00"));
    }

    //- Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_21_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PriceResponse response = objectMapper.readValue(responseSt, PriceResponse.class);

        then(response).isNotNull();
        then(response.getBrandId()).isEqualTo(1);
        then(response.getProductId()).isEqualTo(35455);
        then(response.getPrice()).isEqualTo(new BigDecimal("35.50"));
        then(response.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
        then(response.getEndDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
    }

    //- Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_10_on_15th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PriceResponse response = objectMapper.readValue(responseSt, PriceResponse.class);

        then(response).isNotNull();
        then(response.getBrandId()).isEqualTo(1);
        then(response.getProductId()).isEqualTo(35455);
        then(response.getPrice()).isEqualTo(new BigDecimal("30.50"));
        then(response.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-15T00:00:00"));
        then(response.getEndDate()).isEqualTo(LocalDateTime.parse("2020-06-15T11:00:00"));
    }

    //- Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_21_on_16th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PriceResponse response = objectMapper.readValue(responseSt, PriceResponse.class);

        then(response).isNotNull();
        then(response.getBrandId()).isEqualTo(1);
        then(response.getProductId()).isEqualTo(35455);
        then(response.getPrice()).isEqualTo(new BigDecimal("38.95"));
        then(response.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-15T16:00:00"));
        then(response.getEndDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
    }

    // 400 missing request param
    @Test
    void should_responseStatusBadRequest_when_request_price_without_date() throws Exception {
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint))
            .andDo(print())
            .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        GlobalHandlerErrorResponse response = objectMapper.readValue(responseSt, GlobalHandlerErrorResponse.class);

        then(response.getCode()).isEqualTo("400");
        then(response.getLevel()).isEqualTo("ERROR");
        then(response.getMessage()).isEqualTo("Bad Request");
        then(response.getDescription()).isEqualTo("Error found in request");
    }


    // 400 wrong path variable
    @Test
    void should_responseStatusBadRequest_when_request_price_with_wrong_brandId_variable() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        String endpoint = String.format("/api/prices/brands/%s/products/%d", "brandId", PRODUCT_ID);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print())
            .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        GlobalHandlerErrorResponse response = objectMapper.readValue(responseSt, GlobalHandlerErrorResponse.class);

        then(response.getCode()).isEqualTo("400");
        then(response.getLevel()).isEqualTo("ERROR");
        then(response.getMessage()).isEqualTo("Bad Request");
        then(response.getDescription()).isEqualTo("Error found in request");
    }

    // 404 url missing path variable
    @Test
    void should_responseStatusBadRequest_when_request_price_without_productId() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        String endpoint = String.format("/api/prices/brands/products/%d", PRODUCT_ID);

        MvcResult response = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print())
            .andExpect(status().isNotFound()).andReturn();

        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    // 404 data not found in database
    @Test
    void should_responseStatusNotFound_when_request_data_not_found_in_database() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        String endpoint = String.format(endpointFormat, BRAND_ID, -1);

        String responseSt = mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date)))
            .andDo(print()).andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

        GlobalHandlerErrorResponse response = objectMapper.readValue(responseSt, GlobalHandlerErrorResponse.class);

        then(response.getCode()).isEqualTo("404");
        then(response.getLevel()).isEqualTo("ERROR");
        then(response.getMessage()).isEqualTo("Not Found");
        then(response.getDescription()).isEqualTo("Server cannot find the requested resource");
    }
}
