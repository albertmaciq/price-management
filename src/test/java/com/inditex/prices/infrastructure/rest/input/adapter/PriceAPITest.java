/*
package com.inditex.prices.infrastructure.rest.input.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;


import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({ @Sql(value = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(value = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) })
class PriceAPITest {

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 35455;
    private static final String endpointFormat = "/api/prices/brands/%d/products/%d";
    @MockBean
    private PriceInputPort priceInputPort;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    //- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void should_responseStatusOk_when_request_35455_product_at_10_on_14th_for_ZARA() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        MvcResult result = this.mockMvc
            .perform(MockMvcRequestBuilders.get(endpoint).contentType(MediaType.APPLICATION_JSON).queryParam("date", String.valueOf(date)))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


        then(result).isNotNull();
        then(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String bodyResponse = result.getResponse().getContentAsString();

        PriceResponse response1 = objectMapper.readValue(bodyResponse, PriceResponse.class);

        then(response1).isNotNull();
        then(response1.getPrice()).isEqualTo(1);
        //then(response1.getPrice()).isEqualTo(35.50);

        //when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
       //mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andDo(print());
    }

   */
/* //- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
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

    // 400 request param
    @Test
    void should_responseStatusBadRequest_when_request_price_without_date() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String endpoint = String.format(endpointFormat, BRAND_ID, PRODUCT_ID);

        MvcResult response = this.mockMvc
            .perform(MockMvcRequestBuilders.get(endpoint).contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn();


        then(response).isNotNull();
        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        //when(priceInputPort.obtainProductPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(PriceResponse.builder().build());
        //mockMvc.perform(get(endpoint).queryParam("date", String.valueOf(date))).andDo(print());
    }

    // 400 path variable*//*

}
*/
