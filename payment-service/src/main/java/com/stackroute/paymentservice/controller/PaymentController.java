package com.stackroute.paymentservice.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.stackroute.paymentservice.config.RabbitMqConfiguration;
import com.stackroute.paymentservice.dto.BookingDto;
import  org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.PaytmChecksum;
import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repository.PaymentRepository;
import com.stackroute.paymentservice.services.recieptService;

@Controller
public class PaymentController {
	
	@Autowired
	private Payment paytmDetailPojo;
	
	@Autowired
	private Environment env;

	@Autowired
	private PaymentRepository paymentrepository;

	@Autowired
	private RabbitTemplate template;

	 BookingDto bookingDto=new BookingDto();

	 //receiving data from slot booking service
	@RabbitListener(queues = RabbitMqConfiguration.QUEUE)
	public void saveOperator(BookingDto booking) {

		 bookingDto=booking;

	}


	@RequestMapping("/history")
//	@GetMapping("/")
//	public String home() {
//
//		return "home";
//	}

	@ResponseBody
	 @PostMapping(value = "/submitPaymentDetail")
	    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
	                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {

	        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetailPojo.getPaytmUrl());
	        TreeMap<String, String> parameters = new TreeMap<>();
	        paytmDetailPojo.getDetails().forEach((k, v) -> parameters.put(k, v));
	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	        parameters.put("EMAIL", env.getProperty("paytm.email"));
	        parameters.put("ORDER_ID", orderId);
	        parameters.put("TXN_AMOUNT", transactionAmount);
	        parameters.put("CUST_ID", customerId);
	        String checkSum = getCheckSum(parameters);
	        System.out.println("CHEKSUM" +checkSum);
	        
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
	        return modelAndView;
	    }

	 
	
	 @PostMapping(value = "/pgresponse")
	    public String getResponseRedirect(HttpServletRequest request, Model model) {

	        Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        
	        String paytmChecksum = "";
	        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
	            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
	                paytmChecksum = requestParamsEntry.getValue()[0];
	            } else {
	            	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
	            }
	        }
	        String result;

	        boolean isValideChecksum = false;
	      //  PaymentHistory.setDetails().forEach((k, v) -> parameters.put(k, v));
	        System.out.println("RESULTS : "+parameters.toString());
	        JSONObject json = new JSONObject(parameters);
	        System.out.println(json);



		 template.convertAndSend(RabbitMqConfiguration.EXCHANGE1,RabbitMqConfiguration.ROUTING_KEY1,bookingDto);

	       Reciept Hist = new Reciept ();

		   Hist.setCustomername(bookingDto.getCustomername());
		   Hist.setEmailId(bookingDto.getEmailId());
		   Hist.setMobile(bookingDto.getMobile());


	      Hist.setORDERID(parameters.get("ORDERID"));
	      Hist.setBANKNAME(parameters.get("BANKNAME"));
	      Hist.setGATEWAYNAME(parameters.get("GATEWAYNAME"));
	      Hist.setCURRENCY(parameters.get("CURRENCY"));
	      Hist.setRESPMSG(parameters.get("RESPMSG"));
	      Hist.setSTATUS(parameters.get("STATUS"));
	      Hist.setPAYMENTMODE(parameters.get("PAYMENTMODE"));
	      Hist.setRESPCODE(parameters.get("RESPCODE"));
	      Hist.setTXNID(parameters.get("TXNID"));
	      Hist.setTXNAMOUNT(parameters.get("TXNAMOUNT"));
	      Hist.setTXNDATE(parameters.get("TXNDATE"));
	      Hist.setBANKTXNID(parameters.get("BANKTXNID"));
	      this.paymentrepository.save(Hist);


	       
	  // Exception Handling
	       
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                } else {
	                    result = "Payment Failed";
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return "report";
	    }

	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return PaytmChecksum.verifySignature(parameters,
	                paytmDetailPojo.getMerchantKey(), paytmChecksum);
	    }


	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetailPojo.getMerchantKey());
	}
 
	
	
}

