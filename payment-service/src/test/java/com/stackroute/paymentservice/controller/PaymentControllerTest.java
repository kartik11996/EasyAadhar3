package com.stackroute.paymentservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repository.PaymentRepository;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {PaymentController.class, Payment.class})
@ExtendWith(SpringExtension.class)
class PaymentControllerTest {
    @MockBean
    private Environment environment;

    @Autowired
    private Payment payment;

    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentRepository paymentRepository;

    /**
     * Method under test: {@link PaymentController#getRedirect(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRedirect() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        
        //   In order to prevent getRedirect(String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getRedirect(String, String, String).
        

        (new PaymentController()).getRedirect("42", "10", "42");
    }

    /**
     * Method under test: {@link PaymentController#getResponseRedirect(HttpServletRequest, Model)}
     */
    @Test
    void testGetResponseRedirect() throws Exception {
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
        when(paymentRepository.save((Reciept) any())).thenReturn(reciept);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pgresponse");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("parameters", "result"))
                .andExpect(MockMvcResultMatchers.view().name("report"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("report"));
    }

    /**
     * Method under test: {@link PaymentController#home()}
     */
    @Test
    void testHome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home"));
    }

    /**
     * Method under test: {@link PaymentController#home()}
     */
    @Test
    void testHome2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home"));
    }
}

