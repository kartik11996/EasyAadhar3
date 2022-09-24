package com.stackroute.paymentservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.stackroute.paymentservice.exception.RecieptNotFoundException;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.services.recieptService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RecieptController.class})
@ExtendWith(SpringExtension.class)
class RecieptControllerTest {
    @Autowired
    private RecieptController recieptController;

    @MockBean
    private recieptService recieptService;

    /**
     * Method under test: {@link RecieptController#getAllPayment()}
     */
    @Test
    void testGetAllPayment() throws Exception {
        when(recieptService.getAllPayment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/history/allpayment");
        MockMvcBuilders.standaloneSetup(recieptController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RecieptController#getAllPayment()}
     */
    @Test
    void testGetAllPayment2() throws Exception {
        when(recieptService.getAllPayment()).thenThrow(new RecieptNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/history/allpayment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(recieptController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred"));
    }

    /**
     * Method under test: {@link RecieptController#getAllPayment()}
     */
    @Test
    void testGetAllPayment3() throws Exception {
        when(recieptService.getAllPayment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/history/allpayment");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(recieptController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RecieptController#getPaymentDetailes(String)}
     */
    @Test
    void testGetPaymentDetailes() throws Exception {
        Reciept reciept = new Reciept();
        reciept.setBANKNAME("B ANKNAME");
        reciept.setBANKTXNID("B ANKTXNID");
        reciept.setCURRENCY("GBP");
        reciept.setGATEWAYNAME("G ATEWAYNAME");
        reciept.setORDERID("O RDERID");
        reciept.setPAYMENTMODE("P AYMENTMODE");
        reciept.setRESPCODE("R ESPCODE");
        reciept.setRESPMSG("R ESPMSG");
        reciept.setSTATUS("S TATUS");
        reciept.setTXNAMOUNT("10");
        reciept.setTXNDATE("T XNDATE");
        reciept.setTXNID("T XNID");
        when(recieptService.getPaymentDetails((String) any())).thenReturn(reciept);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/history/find/{id}", "42");
        MockMvcBuilders.standaloneSetup(recieptController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"orderid\":\"O RDERID\",\"bankname\":\"B ANKNAME\",\"currency\":\"GBP\",\"gatewayname\":\"G ATEWAYNAME\",\"paymentmode\":\"P"
                                        + " AYMENTMODE\",\"respcode\":\"R ESPCODE\",\"respmsg\":\"R ESPMSG\",\"status\":\"S TATUS\",\"txnid\":\"T XNID\",\"txnamount"
                                        + "\":\"10\",\"txndate\":\"T XNDATE\",\"banktxnid\":\"B ANKTXNID\"}"));
    }

    /**
     * Method under test: {@link RecieptController#getPaymentDetailes(String)}
     */
    @Test
    void testGetPaymentDetailes2() throws Exception {
        when(recieptService.getPaymentDetails((String) any()))
                .thenThrow(new RecieptNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/history/find/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(recieptController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred"));
    }
}

