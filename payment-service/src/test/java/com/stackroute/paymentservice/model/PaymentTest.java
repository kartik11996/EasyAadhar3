package com.stackroute.paymentservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PaymentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Payment#Payment()}
     *   <li>{@link Payment#setChannelId(String)}
     *   <li>{@link Payment#setDetails(Map)}
     *   <li>{@link Payment#setIndustryTypeId(String)}
     *   <li>{@link Payment#setMerchantId(String)}
     *   <li>{@link Payment#setMerchantKey(String)}
     *   <li>{@link Payment#setPaytmUrl(String)}
     *   <li>{@link Payment#setWebsite(String)}
     *   <li>{@link Payment#toString()}
     *   <li>{@link Payment#getChannelId()}
     *   <li>{@link Payment#getDetails()}
     *   <li>{@link Payment#getIndustryTypeId()}
     *   <li>{@link Payment#getMerchantId()}
     *   <li>{@link Payment#getMerchantKey()}
     *   <li>{@link Payment#getPaytmUrl()}
     *   <li>{@link Payment#getWebsite()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Payment actualPayment = new Payment();
        actualPayment.setChannelId("42");
        HashMap<String, String> stringStringMap = new HashMap<>();
        actualPayment.setDetails(stringStringMap);
        actualPayment.setIndustryTypeId("42");
        actualPayment.setMerchantId("42");
        actualPayment.setMerchantKey("Merchant Key");
        actualPayment.setPaytmUrl("https://example.org/example");
        actualPayment.setWebsite("Website");
        String actualToStringResult = actualPayment.toString();
        assertEquals("42", actualPayment.getChannelId());
        assertSame(stringStringMap, actualPayment.getDetails());
        assertEquals("42", actualPayment.getIndustryTypeId());
        assertEquals("42", actualPayment.getMerchantId());
        assertEquals("Merchant Key", actualPayment.getMerchantKey());
        assertEquals("https://example.org/example", actualPayment.getPaytmUrl());
        assertEquals("Website", actualPayment.getWebsite());
        assertEquals(
                "PaytmDetailPojo [merchantId=42, merchantKey=Merchant Key, channelId=42, website=Website, industryTypeId=42,"
                        + " paytmUrl=https://example.org/example, details={}]",
                actualToStringResult);
    }
}

