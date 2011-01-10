package us.kripet.bartleby;

import java.net.MalformedURLException;
import java.net.URL;

public class BartAPI {
  public final static String API_HOST = "http://api.bart.gov/api/";
  public final static String API_KEY = "MW9S-E7SL-26DU-VV8V";

  protected static URL api(String action, String cmd) {
    StringBuilder url = new StringBuilder(API_HOST);
    url.append(action + ".aspx?");
    url.append("key=" + API_KEY);
    url.append("&cmd=" + cmd);
    try {
      return new URL(url.toString());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
