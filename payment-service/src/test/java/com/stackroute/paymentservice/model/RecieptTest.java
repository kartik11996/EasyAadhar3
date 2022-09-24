package com.stackroute.paymentservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RecieptTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Reciept}
     *   <li>{@link Reciept#setBANKNAME(String)}
     *   <li>{@link Reciept#setBANKTXNID(String)}
     *   <li>{@link Reciept#setCURRENCY(String)}
     *   <li>{@link Reciept#setGATEWAYNAME(String)}
     *   <li>{@link Reciept#setORDERID(String)}
     *   <li>{@link Reciept#setPAYMENTMODE(String)}
     *   <li>{@link Reciept#setRESPCODE(String)}
     *   <li>{@link Reciept#setRESPMSG(String)}
     *   <li>{@link Reciept#setSTATUS(String)}
     *   <li>{@link Reciept#setTXNAMOUNT(String)}
     *   <li>{@link Reciept#setTXNDATE(String)}
     *   <li>{@link Reciept#setTXNID(String)}
     *   <li>{@link Reciept#toString()}
     *   <li>{@link Reciept#getBANKNAME()}
     *   <li>{@link Reciept#getBANKTXNID()}
     *   <li>{@link Reciept#getCURRENCY()}
     *   <li>{@link Reciept#getGATEWAYNAME()}
     *   <li>{@link Reciept#getORDERID()}
     *   <li>{@link Reciept#getPAYMENTMODE()}
     *   <li>{@link Reciept#getRESPCODE()}
     *   <li>{@link Reciept#getRESPMSG()}
     *   <li>{@link Reciept#getSTATUS()}
     *   <li>{@link Reciept#getTXNAMOUNT()}
     *   <li>{@link Reciept#getTXNDATE()}
     *   <li>{@link Reciept#getTXNID()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Reciept actualReciept = new Reciept();
        actualReciept.setBANKNAME("B ANKNAME");
        actualReciept.setBANKTXNID("B ANKTXNID");
        actualReciept.setCURRENCY("GBP");
        actualReciept.setGATEWAYNAME("G ATEWAYNAME");
        actualReciept.setORDERID("O RDERID");
        actualReciept.setPAYMENTMODE("P AYMENTMODE");
        actualReciept.setRESPCODE("R ESPCODE");
        actualReciept.setRESPMSG("R ESPMSG");
        actualReciept.setSTATUS("S TATUS");
        actualReciept.setTXNAMOUNT("10");
        actualReciept.setTXNDATE("T XNDATE");
        actualReciept.setTXNID("T XNID");
        String actualToStringResult = actualReciept.toString();
        assertEquals("B ANKNAME", actualReciept.getBANKNAME());
        assertEquals("B ANKTXNID", actualReciept.getBANKTXNID());
        assertEquals("GBP", actualReciept.getCURRENCY());
        assertEquals("G ATEWAYNAME", actualReciept.getGATEWAYNAME());
        assertEquals("O RDERID", actualReciept.getORDERID());
        assertEquals("P AYMENTMODE", actualReciept.getPAYMENTMODE());
        assertEquals("R ESPCODE", actualReciept.getRESPCODE());
        assertEquals("R ESPMSG", actualReciept.getRESPMSG());
        assertEquals("S TATUS", actualReciept.getSTATUS());
        assertEquals("10", actualReciept.getTXNAMOUNT());
        assertEquals("T XNDATE", actualReciept.getTXNDATE());
        assertEquals("T XNID", actualReciept.getTXNID());
        assertEquals(
                "PaymentHistory [ORDERID=O RDERID, BANKNAME=B ANKNAME, BANKTXNID=B ANKNAME, CURRENCY=GBP, GATEWAYNAME=G"
                        + " ATEWAYNAME, PAYMENTMODE=B ANKNAME, RESPCODE=B ANKNAME, RESPMSG=R ESPMSG, STATUS=S TATUS, TXNAMOUNT=10,"
                        + " TXNDATE=T XNDATE, TXNID=T XNID]",
                actualToStringResult);
    }
}

