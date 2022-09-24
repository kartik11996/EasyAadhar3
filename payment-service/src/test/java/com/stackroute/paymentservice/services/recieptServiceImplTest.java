package com.stackroute.paymentservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.paymentservice.exception.RecieptNotFoundException;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {recieptServiceImpl.class})
@ExtendWith(SpringExtension.class)
class recieptServiceImplTest {
    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private recieptServiceImpl recieptServiceImpl;

    /**
     * Method under test: {@link recieptServiceImpl#getPaymentDetails(String)}
     */
    @Test
    void testGetPaymentDetails() {
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
        Optional<Reciept> ofResult = Optional.of(reciept);
        when(paymentRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(reciept, recieptServiceImpl.getPaymentDetails("42"));
        verify(paymentRepository).findById((String) any());
    }

    /**
     * Method under test: {@link recieptServiceImpl#getPaymentDetails(String)}
     */
    @Test
    void testGetPaymentDetails2() {
        when(paymentRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(RecieptNotFoundException.class, () -> recieptServiceImpl.getPaymentDetails("42"));
        verify(paymentRepository).findById((String) any());
    }

    /**
     * Method under test: {@link recieptServiceImpl#getPaymentDetails(String)}
     */
    @Test
    void testGetPaymentDetails3() {
        when(paymentRepository.findById((String) any())).thenThrow(new RecieptNotFoundException("An error occurred"));
        assertThrows(RecieptNotFoundException.class, () -> recieptServiceImpl.getPaymentDetails("42"));
        verify(paymentRepository).findById((String) any());
    }

    /**
     * Method under test: {@link recieptServiceImpl#getAllPayment()}
     */
    @Test
    void testGetAllPayment() {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RecieptNotFoundException.class, () -> recieptServiceImpl.getAllPayment());
        verify(paymentRepository).findAll();
    }

    /**
     * Method under test: {@link recieptServiceImpl#getAllPayment()}
     */
    @Test
    void testGetAllPayment2() {
        Reciept reciept = new Reciept();
        reciept.setBANKNAME("Reciept is not available in the database");
        reciept.setBANKTXNID("Reciept is not available in the database");
        reciept.setCURRENCY("GBP");
        reciept.setGATEWAYNAME("Reciept is not available in the database");
        reciept.setORDERID("Reciept is not available in the database");
        reciept.setPAYMENTMODE("Reciept is not available in the database");
        reciept.setRESPCODE("Reciept is not available in the database");
        reciept.setRESPMSG("Reciept is not available in the database");
        reciept.setSTATUS("Reciept is not available in the database");
        reciept.setTXNAMOUNT("10");
        reciept.setTXNDATE("Reciept is not available in the database");
        reciept.setTXNID("Reciept is not available in the database");

        ArrayList<Reciept> recieptList = new ArrayList<>();
        recieptList.add(reciept);
        when(paymentRepository.findAll()).thenReturn(recieptList);
        List<Reciept> actualAllPayment = recieptServiceImpl.getAllPayment();
        assertSame(recieptList, actualAllPayment);
        assertEquals(1, actualAllPayment.size());
        verify(paymentRepository).findAll();
    }

    /**
     * Method under test: {@link recieptServiceImpl#getAllPayment()}
     */
    @Test
    void testGetAllPayment3() {
        when(paymentRepository.findAll()).thenThrow(new RecieptNotFoundException("An error occurred"));
        assertThrows(RecieptNotFoundException.class, () -> recieptServiceImpl.getAllPayment());
        verify(paymentRepository).findAll();
    }
}

