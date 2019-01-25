package com.restapi.integration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;


@ContextConfiguration(classes = {SpringConfig.class}, loader = SpringApplicationContextLoader.class)
public abstract class Stepdef {
  private final static Pattern paramPattern = Pattern.compile("\\$\\{([^}]+)\\}");

  protected ObjectMapper mapper = new ObjectMapper();

  @Autowired
  protected Data data;

  @SuppressWarnings("unchecked")
  protected void compareMap(String name, Map<String, Object> A, Map<String, Object> B) {
    for (final String key : A.keySet()) {
      Object a = A.get(key);
      Object b = B.get(key);

      if (a instanceof Map) {
        compareMap(name, (Map<String, Object>) a, (Map<String, Object>) b);
      } else if (a instanceof List) {
        compareList(name, (List) a, (List) b);
      } else {
        Assert.assertEquals(name + ": " + key + " should match", a, b);
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected void compareList(String name, List A, List B) {
    Assert.assertEquals("Lists should have same length", A.size(), B.size());

    for (int i = 0; i < A.size(); i++) {
      String n = name + "." + i;
      Object a = A.get(i);
      Object b = B.get(i);

      if (a instanceof List) {
        compareList(n, (List) a, (List) b);
      } else if (a instanceof Map) {
        compareMap(n, (Map<String, Object>) a, (Map<String, Object>) b);
      } else {
        Assert.assertEquals(n + " should match", a, b);
      }
    }
  }

  public enum EncodeType {
    NONE, URL, JSON, SQL
  }

  protected String replaceVars(String value, EncodeType encode) throws UnsupportedEncodingException {
    Matcher regexMatcher;
    while ((regexMatcher = paramPattern.matcher(value)).find()) {
      String result = eval(regexMatcher.group(1));
      switch (encode) {
        case URL:
          result = URLEncoder.encode(result, "UTF-8");
          break;
        case JSON:
          result = StringEscapeUtils.escapeJson(result);
          break;
        case SQL:
          result = org.apache.commons.lang.StringEscapeUtils.escapeSql(result);
        case NONE:
          break;
      }
      value = value.replace(regexMatcher.group(), result);
    }
    return value;
  }

  protected String eval(String value) {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression(value);

    EvaluationContext context = new StandardEvaluationContext(data);
    String result;
    try {
      result = String.valueOf(exp.getValue(context));
    } catch (SpelEvaluationException e) {
      System.out.println(value + " : " + e.toDetailedString());
      throw e;
    }
    System.out.println(value + " -> " + result);
    return result;
  }
}
