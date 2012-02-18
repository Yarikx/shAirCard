package com.gtug.shaircard;

import com.stanfy.serverapi.RequestMethodHelper;

/**
 * @author Roman Mazur (Stanfy - http://www.stanfy.com)
 */
public class SampleRequestMethodHelper extends RequestMethodHelper {

  public SampleRequestMethodHelper() {
    super(TYPE_JSON, shAirCardApp.APP_AUTHORITY);
  }

}
