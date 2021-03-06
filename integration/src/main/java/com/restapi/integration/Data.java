package com.restapi.integration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.restapi.integration.enums.Preferences;

@Component
@Scope("cucumber-glue")
public class Data {
  public List<Object> resultList = new ArrayList<>();
  public Map<String, Object> results = new HashMap<>();
  public Object lastResult = new HashMap<>();

  public void remember(String rememberBy, Object data) {
    
	  if ("data".equals(rememberBy))
      throw new UnsupportedOperationException("Illegal value for rememberBy: " + rememberBy);

    results.put(rememberBy, data);
    resultList.add(data);
    lastResult = data;
  }
}
