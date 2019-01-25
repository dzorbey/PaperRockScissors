package com.restapi.integration.stepdefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.restapi.integration.Stepdef;

import cucumber.api.java.en.Given;

public class ApiSteps extends Stepdef {
  final static Log LOGGER = LogFactory.getLog(ApiSteps.class);

  @Value("${rest.api.url}")
  String apiBase;

  private RestTemplate client(String source) {
    RestTemplate restTemplate = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add((r, b, e) -> {
      HttpRequest w = new HttpRequestWrapper(r);
      w.getHeaders().set("CallerID", source);
      return e.execute(w, b);
    });
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @Given("^POST (.+) on RTS (?:from (.+) )?into (.+)$")
  public void postRts(String url, String source, String rememberBy, String json) throws IOException {
    LOGGER.info("POSTING: " + url);

    /*
     * 
     * if (json != null && json.trim().isEmpty()) - json = null; - if (json != null) - json =
     * replaceVars(json, EncodeType.JSON); - if (source == null || source.isEmpty()) - source =
     * "TESTING";
     */

    final String urlNew = replaceVars(url, EncodeType.URL);

    RestTemplate restTemplate =
        client(Optional.ofNullable(source).filter(sInnerrr -> !sInnerrr.isEmpty())
            .orElse("TESTING"));

    Optional.ofNullable(json).filter(sInner -> sInner.isEmpty()).ifPresent(sInner -> {
      postOperation(null, urlNew, restTemplate, rememberBy);
    });

    Optional.ofNullable(json).filter(sInner -> !sInner.isEmpty()).ifPresent(sInner -> {
      try {
        postOperation(replaceVars(json, EncodeType.JSON), urlNew, restTemplate, rememberBy);
      } catch (Exception e) {
      }
    });
  }

  public void postOperation(String jsonNew, String url, RestTemplate restTemplate, String rememberBy) {

    Optional.ofNullable(jsonNew).ifPresent(
        s -> {
          try {
            JsonNode parsedJson = mapper.readTree(jsonNew);

            Optional.ofNullable(parsedJson).ifPresent(
                s2 -> {
                  System.out.println("POSTing to " + url);
                  try {
                    System.out.print(mapper.writeValueAsString(parsedJson));
                    try {
                      @SuppressWarnings("unchecked")
                      Object res =
                          restTemplate.postForObject(apiBase + url, parsedJson, Object.class);

                      Optional.ofNullable(res).ifPresent(s3 -> {
                        try {
                          checkResponse(res, rememberBy);
                        } catch (Exception e) {
                        }
                      });

                      Optional.ofNullable(rememberBy).filter(sInner -> !sInner.isEmpty())
                          .ifPresent(sInner -> {
                            data.remember(rememberBy, res);
                          });
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                      throw new IOException("Got response " + e.getStatusCode() + ":\n"
                          + e.getResponseBodyAsString(), e);
                    }
                  } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                  }
                });
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        });
  }



  @Given("^GET (.+) on RTS (?:from (.+) )?into (.+)$")
  public void getRts(String url, String source, String rememberBy) throws IOException {
    url = replaceVars(url, EncodeType.URL);

    // source = Optional.ofNullable(source).filter(sInnerrr ->
    // !sInnerrr.isEmpty()).orElse("TESTING");
    /*
     * if (source == null || source.isEmpty()) source = "TESTING";
     */

    RestTemplate restTemplate =
        client(Optional.ofNullable(source).filter(sInnerrr -> !sInnerrr.isEmpty())
            .orElse("TESTING"));

    System.out.println("GETing to " + url);
    try {
      @SuppressWarnings("unchecked")
      Object res = restTemplate.getForObject(apiBase + url, Object.class);

      Optional.ofNullable(res).ifPresent(s -> {
        try {
          checkResponse(res, rememberBy);
          data.remember(rememberBy, res);
        } catch (Exception e) {
        }
      });
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      throw new IOException("Got response " + e.getStatusCode() + ":\n"
          + e.getResponseBodyAsString(), e);
    }
  }

  public String checkResponse(Object res, String rememberBy) throws JsonProcessingException {
    System.out.println("Response: " + res.getClass());
    System.out.println(mapper.writeValueAsString(res));
    return "";
  }



  @Given("^DELETE (.+) on RTS(?:from (.+))?$")
  public void deleteRts(String url, String source) throws IOException {

    url = replaceVars(url, EncodeType.URL);

    // source = Optional.ofNullable(source).filter(sInnerrr ->
    // !sInnerrr.isEmpty()).orElse("TESTING");
    /*
     * if (source == null || source.isEmpty()) source = "TESTING";
     */
    RestTemplate restTemplate =
        client(Optional.ofNullable(source).filter(sInnerrr -> !sInnerrr.isEmpty())
            .orElse("TESTING"));

    System.out.println("DELETing from " + url);
    try {
      // @SuppressWarnings("unchecked")
      restTemplate.delete(apiBase + url);
      // Object res = restTemplate.exchange(rtsBase + url, HttpMethod.DELETE, null, Object.class);
      // if (res != null)
      // System.out.println("Response: " + res.getClass());
      // System.out.println(mapper.writeValueAsString(res));
      // data.remember(rememberBy, res);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      throw new IOException("Got response " + e.getStatusCode() + ":\n"
          + e.getResponseBodyAsString(), e);
    }
  }

  @Given("^RTS is running$")
  public void rts_is_running() throws Throwable {

  }

  @Given("^the system waits for (\\d+) seconds$")
  public void the_system_waits_for_seconds(int nrOfSeconds) throws Throwable {
    Thread.sleep(nrOfSeconds * 1000);
  }

  @Given("^get timestamp into (.+)$")
  public void get_timestamp(String rememberBy) throws Throwable {

    String timestamp = "" + new Date().getTime();
    Optional.ofNullable(rememberBy).filter(s -> !s.isEmpty()).ifPresent(sInner -> {
      data.remember(rememberBy, timestamp);
    });
  }
}
