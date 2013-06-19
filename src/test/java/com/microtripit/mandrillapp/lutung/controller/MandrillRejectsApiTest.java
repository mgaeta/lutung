/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import junit.framework.Assert;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillRejectsApiTest extends LutungApiTest {

	@Test
	public final void testList() throws IOException, MandrillApiError {
		Assert.assertNotNull( mandrillApi.rejects().list(null, null) );

	}

	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.rejects().delete(null);
		Assert.fail();
	}

}
