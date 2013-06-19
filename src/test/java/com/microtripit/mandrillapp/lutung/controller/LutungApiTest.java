package com.microtripit.mandrillapp.lutung.controller;

import org.junit.Assume;
import org.junit.Before;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.MandrillApiTest;

public class LutungApiTest {
  protected static MandrillApi mandrillApi;
  protected static Boolean ranSetup;

  @Before
  public final void runBefore() {
    if (ranSetup == null) {
      final String key = MandrillApiTest.getMandrillApiKey();

      if(key != null) {
        mandrillApi = new MandrillApi(key);
      } else {
        mandrillApi = null;
      }
      ranSetup = true;
    }

    Assume.assumeNotNull(mandrillApi);
  }

}
