package com.stackroute.paymentservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.stackroute.paymentservice.dto.BookingDto;
import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repository.PaymentRepository;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @MockBean
    private RabbitTemplate rabbitTemplate;


    @Test
    @Disabled("TODO: Complete this test")
    void testGetRedirect() throws Exception {


        (new PaymentController()).getRedirect("42", "10", "42");
    }


    @Test
    void testConstructor() {
        PaymentController actualPaymentController = new PaymentController();
        actualPaymentController.saveOperator(new BookingDto(123, "42", "2020-03-01", "Appointment Time", "Customername",
                "Center Name", "Mobile", "42 Main St"));
        BookingDto bookingDto = actualPaymentController.bookingDto;
        assertEquals("42 Main St", bookingDto.getAddress());
        assertEquals("Mobile", bookingDto.getMobile());
        assertEquals("42", bookingDto.getEmailId());
        assertEquals("Customername", bookingDto.getCustomername());
        assertEquals("Center Name", bookingDto.getCenterName());
        assertEquals("Appointment Time", bookingDto.getAppointmentTime());
        assertEquals(123, bookingDto.getAppointmentId());
        assertEquals("2020-03-01", bookingDto.getAppointmentDate());
    }


    @Test
    void testGetResponseRedirect() throws Exception {
        Reciept reciept = new Reciept();
        reciept.setBANKNAME("B ANKNAME");
        reciept.setBANKTXNID("B ANKTXNID");
        reciept.setCURRENCY("GBP");
        reciept.setCustomername("Customername");
        reciept.setEmailId("42");
        reciept.setGATEWAYNAME("G ATEWAYNAME");
        reciept.setMobile("Mobile");
        reciept.setORDERID("O RDERID");
        reciept.setPAYMENTMODE("P AYMENTMODE");
        reciept.setRESPCODE("R ESPCODE");
        reciept.setRESPMSG("R ESPMSG");
        reciept.setSTATUS("S TATUS");
        reciept.setTXNAMOUNT("10");
        reciept.setTXNDATE("T XNDATE");
        reciept.setTXNID("T XNID");
        when(paymentRepository.save((Reciept) any())).thenReturn(reciept);
        doNothing().when(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
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
}

