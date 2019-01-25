package com.restapi.integration.stepdefs;

import com.restapi.integration.Stepdef;

import cucumber.api.java.en.Given;

public class IntegrationSteps extends Stepdef {
  @Given("^Breakable Step With Id (.+)$")
  public void breakAbleStepWithId(int breakId) {
    switch (breakId) {
      case 1:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 2:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 3:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 4:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 5:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 6:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 7:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 8:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 9:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 10:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 11:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 12:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 13:
        System.out.println("Breakpoint on id " + breakId);
        break;
      case 14:
        System.out.println("Breakpoint on id " + breakId);
        break;
    }
  }
}
