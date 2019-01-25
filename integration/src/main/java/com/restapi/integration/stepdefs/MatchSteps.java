package com.restapi.integration.stepdefs;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.springframework.beans.factory.annotation.Value;

import com.restapi.integration.GroovyBaseScript;
import com.restapi.integration.Stepdef;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

public class MatchSteps extends Stepdef {
  @Then("([^ ]+?) should match$")
  public void match(String rememberBy, String json) throws IOException {
    Object orig = data.results.get(rememberBy);
    if (orig == null)
      throw new NullPointerException(rememberBy + " was not remembered");


    @SuppressWarnings("unchecked")
    Object expected = mapper.readValue(replaceVars(json, EncodeType.JSON), Object.class);
    if (expected instanceof Map) {
      compareMap(rememberBy, (Map<String, Object>) expected, (Map<String, Object>) orig);
    } else if (expected instanceof List) {
      compareList(rememberBy, (List) expected, (List) orig);
    } else {
      throw new RuntimeException("Unknown type: " + expected.getClass());
    }
  }

  @Then("(doif|donotif) ([^ ]+?) then ([^ ]+?) should match$")
  public void match(String conditionalKeyword, boolean doit, String rememberBy, String json)
      throws IOException {
    if (conditionalKeyword.equals("doif")) {
      if (!doit)
        return;
    } else if (conditionalKeyword.equals("donotif")) {
      if (doit)
        return;
    } else
      return;

    match(rememberBy, json);
  }

  @Then("(.+) should match-null")
  public void matchNull(String rememberBy) throws Exception {
    Object orig = data.results.get(rememberBy);
    if (orig != null)
      throw new Exception(rememberBy + " does not equal 'null'");
  }

  @Then("(.+) should unconditionally match$")
  public void match2(String rememberBy, String json) throws IOException {
    match(rememberBy, json);
  }

  @Then("if (.+) then (.+) should conditionally match$")
  public void cmatch(boolean condition, String rememberBy, String json) throws IOException {
    if (condition) {
      match(rememberBy, json);
    }
  }

  @Then("groovy should succeed$")
  public void matchGroovy(String groovy) throws IOException {
    matchGroovy("", groovy);
  }

  @Then("^(doif|donotif) (.+) groovy with comment ([^ ]+?) should succeed$")
  public void conditional_matchGroovy(String conditionalKeyword, boolean doit, String comment,
      String groovy) throws IOException {
    if (conditionalKeyword.equals("doif")) {
      if (!doit)
        return;
    } else if (conditionalKeyword.equals("donotif")) {
      if (doit)
        return;
    } else
      return;

    matchGroovy(comment, groovy);
  }

  @Then("groovy with comment ([^ ]+?) should succeed$")
  public void matchGroovy(String comment, String groovy) throws IOException {
    System.out.println(comment);

    CompilerConfiguration compilerConfig = new CompilerConfiguration();
    compilerConfig.setScriptBaseClass(GroovyBaseScript.class.getName());

    ImportCustomizer imports = new ImportCustomizer();

    imports.addStaticStars("org.junit.Assert");

    compilerConfig.addCompilationCustomizers(imports);

    Binding binding = new Binding();
    for (Entry<String, Object> entry : data.results.entrySet()) {
      binding.setVariable(entry.getKey(), entry.getValue());
    }
    binding.setVariable("data", data);

    GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), binding, compilerConfig);
    shell.evaluate(groovy);
  }

  @Value("${cucumber-exit-on-fail}")
  boolean exitOnFail;

  static boolean prevScenarioFailed = false;

  @Before
  public void setup() throws Exception {
    if (prevScenarioFailed && exitOnFail) {
      throw new IllegalStateException("Previous scenario failed!");
    }
  }

  @After
  public void teardown(Scenario scenario) throws Exception {
    prevScenarioFailed = scenario.isFailed();
  }
}
