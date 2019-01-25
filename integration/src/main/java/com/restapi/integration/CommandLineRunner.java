package com.restapi.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.restapi.integration.enums.Preferences;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.formatter.PluginFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;

/*
 * This is an already existing codebase, replaced some existing if conditions with optionals, 
 * but not in all classes of the same project i did so, here done for demonstration purposes.
 */
public class CommandLineRunner {
  public static void main(String[] argsArr) throws IOException {
    runCucumber(argsArr);
  }

  private static void runCucumber(String[] argsArr) throws IOException {
    List<String> args = new ArrayList<>(Arrays.asList(argsArr));
    // args.remove(0); // Pop the "cucumber" command

    
    
    if (!args.contains("--permit-data-loss")) {
      checkUserSure();
    } else {
      args.remove("--permit-data-loss");
    }

    // Force prety plugin to be active
    args.add(0, "--plugin");
    args.add(1, "pretty");

    PluginFactory pluginFactory = new PluginFactory();
    RuntimeOptions runtimeOptions = new RuntimeOptions(pluginFactory, args);

    
    Preferences missingFilter = Preferences.MISSING_FILTER_SIZE;
    Optional.of(missingFilter).filter(y -> y == 
    		Preferences.fromText(String.valueOf(runtimeOptions.getFilters().size()))).
    		ifPresent(s -> System.exit(1));
   	
    
    /*if (runtimeOptions.getFilters().size() == 0) {
      System.out.println("Missing filters (--tags)");
      System.exit(1);
    }*/

    if (!runtimeOptions.getGlue().contains("classpath:com.billinghouse.mallplaza.integration"))
      runtimeOptions.getGlue().add("classpath:com.billinghouse.mallplaza.integration");

    if (runtimeOptions.getFeaturePaths().isEmpty()) {
      for (Resource r : new PathMatchingResourcePatternResolver()
          .getResources("classpath:*.feature")) {
        runtimeOptions.getFeaturePaths().add("classpath:" + r.getFilename());
      }

    }
    
    

    runtimeOptions.addPlugin(pluginFactory.create("html:integration-test-html"));
    runtimeOptions.addPlugin(pluginFactory.create("json:integration-test.json"));

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    ResourceLoader resourceLoader = new MultiLoader(classLoader);
    ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
    Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
    runtime.run();
    System.exit(runtime.exitStatus());
  }

  
  private static void checkUserSure() {
    @SuppressWarnings("resource")
    Scanner scan = new Scanner(System.in);

    Preferences codeword = Preferences.MANUAL_PERMIT;

        System.out.println("WARNING: These integration tests WILL destroy the data in your databases.");
        System.out.println("Please type \"" + codeword.getText()
             + "\" to continue, or provide the --permit-data-loss command line flag.");
    
        Optional.of(codeword).filter(y -> y == Preferences.fromText(scan.nextLine())).orElseThrow(
        		RuntimeException::new);
  }
}
