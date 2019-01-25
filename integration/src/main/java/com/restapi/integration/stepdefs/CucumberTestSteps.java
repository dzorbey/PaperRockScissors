package com.restapi.integration.stepdefs;

import java.util.List;

import com.restapi.integration.Stepdef;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CucumberTestSteps extends Stepdef {

  @Before("@first")
  public void beforeFirst() {
    System.out.println("Before first");
  }

  // @PostConstruct
  public void test() {
    System.out.println("First postconstruct");
  }

  // @PostConstruct
  public void initialize() {
    System.out.println("Second postconstruct");
  }

  @Given("^first$")
  public void first() throws Throwable {
    System.out.println("First scenario");
  }

  @Then("^ok$")
  public void ok() throws Throwable {
    System.out.println("OK");
  }

  class Params {
    public String param1;
    public String param2;
  }

  @Given("^second$")
  public void second(List<Params> params) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)

    for (Params param : params) {
      System.out.println(param.param1 + ", " + param.param2);
    }
  }

  @Given("^repeatme (\\d+)$")
  public void repeatme(int arg1) {
    System.out.println("Counter: " + arg1);
  }

}
