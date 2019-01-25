package com.restapi.integration.stepdefs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import com.billinghouse.mallplaza.enums.CreditCardType;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.integration.Stepdef;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class PaymentSteps extends Stepdef {

  @Autowired
  private ApiSteps rtsSteps;

  private ObjectMapper objectMapper;

  public PaymentSteps() {
    objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
  }

  @Before("@add_customer")
  public void beforeFirst() {
    System.out.println("Before first");
  }

  public static class Payment {
    @JsonIgnore
    String url;
    @JsonIgnore
    String into;

    String userId;
    BigDecimal usageAmount;
    BigDecimal balanceBefore;
    BigDecimal balanceAfter;
    String paymentType;

    BigDecimal amount;
    String orderNumber;
    String message;
    String plTransactionId;
    String paymentDate;
    String paymentMode;
    String paymentProviderId;
    String paymentReferenceId;
    String prepaidStatus;
    String resultCode;
    String resultDescription;
    String ccLastFourDigits;

    String folioNumber;
    String authorisationCode;
    //CreditCardType ccType;
    Byte isRead;
    Byte isRetry;
    String invoiceLink;


  }

  @Given("^the following payment notifications are posted$")
  public void the_following_payment_notifications_are_posted(List<Payment> payments)
      throws Throwable {

    for (Payment payment : payments) {
      String jSon = objectMapper.writeValueAsString(payment);
      rtsSteps.postRts(payment.url, null, payment.into, jSon);
    }
  }
}
