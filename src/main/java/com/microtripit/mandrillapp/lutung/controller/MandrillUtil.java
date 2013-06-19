/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillContentWrapper;
import com.microtripit.mandrillapp.lutung.model.MandrillRequest;
import com.microtripit.mandrillapp.lutung.model.MandrillRequestDispatcher;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
final class MandrillUtil {
	protected static final String rootUrl = "https://mandrillapp.com/api/1.0/";

	/**
	 * @param key
	 * @return
	 */
	protected static final HashMap<String,Object> paramsWithKey(final String key) {
		final HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("key",key);
		return params;
	}

	/**
	 * @param url
	 * @param params
	 * @param responseType
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	protected static final <OUT> OUT query(final String url,
			final Map<String,Object> params, Class<OUT> responseType)
					throws MandrillApiError, IOException {

		final MandrillRequest<OUT> requestModel =
				new MandrillRequest<OUT>(url, params, responseType);
		return MandrillRequestDispatcher.execute(requestModel, null);

	}

  /**
   *
   * @param variables
   * @return
   */
  protected static final ArrayList<MandrillContentWrapper> variableMapToParams(final Map<String, String> variables) {

    final ArrayList<MandrillContentWrapper> vars = new ArrayList<MandrillContentWrapper>();
    if(variables != null && !variables.isEmpty()) {
      for(String name : variables.keySet()) {
        vars.add( MandrillContentWrapper.create(name, variables.get(name)) );
      }
    }

    return vars;

  }

}
