package com.gtug.shaircard;

import static com.stanfy.serverapi.request.OperationType.SIMPLE_GET;
import static com.stanfy.serverapi.request.OperationType.SIMPLE_POST;

import com.stanfy.serverapi.request.Operation;

/**
 * Enumerate your operations here.
 */
public enum OurOperation implements Operation {

  GET_ALL_EVENTS(SIMPLE_GET, "http://shaircard.appspot.com/get_all_events"),
  GET_FILTERED_EVENTS(SIMPLE_GET, "http://shaircard.appspot.com/get_filtered_events"),
  GET_ALL_VCARDS_BY_EVENTID(SIMPLE_GET, "http://shaircard.appspot.com/get_all_vcards_by_eventid"),
  POST_VCARD(SIMPLE_POST, "http://shaircard.appspot.com/add_vcard"),
  POST_EVENT(SIMPLE_POST, "http://shaircard.appspot.com/add_event"),
  REFRESH_EVENTS(SIMPLE_POST, "http://shaircard.appspot.com/refresh_events"),;

  /** Type */
  private final int type;
  /** URL. */
  private String url;

  private OurOperation(final int type, final String url) {
    this.type = type;
    this.url = url;
  }

  @Override
  public int getCode() { return ordinal(); }
  @Override
  public int getType() { return type; }
  @Override
  public String getUrlPart() { return url; }

  public static OurOperation byCode(final int code) { return OurOperation.values()[code]; }

}
