/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillContentWrapper;
import com.microtripit.mandrillapp.lutung.model.MandrillRequest;
import com.microtripit.mandrillapp.lutung.model.MandrillRequestDispatcher;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;

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

  /**
   *
   * @param mergeVars
   * @return
   */
  protected static MandrillMessage.MergeVar[] mergeVarMapAsArray(final Map<String, String> mergeVars) {
    final MandrillMessage.MergeVar[] varArray = new MandrillMessage.MergeVar[mergeVars.size()];

    int i = 0;
    for(String name : mergeVars.keySet()) {
      final MandrillMessage.MergeVar mergeVar = new MandrillMessage.MergeVar();
      mergeVar.setName(name);
      mergeVar.setContent(mergeVars.get(name));
      varArray[i++] = mergeVar;
    }

    return varArray;
  }

  /**
   *
   * @param mergeVars
   * @return
   */
  protected static List<MandrillMessage.MergeVar> mergeVarMapAsList(final Map<String, String> mergeVars) {
    return new ArrayList(Arrays.asList(mergeVarMapAsArray(mergeVars)));
  }

  /**
   *
   * @param mergeVars
   * @return
   */
  protected static MandrillMessage.MergeVarBucket mergeVarMapAsBucket(final Map<String, String> mergeVars) {
    final MandrillMessage.MergeVarBucket bucket = new MandrillMessage.MergeVarBucket();
    bucket.setVars(mergeVarMapAsArray(mergeVars));
    return bucket;
  }
}
