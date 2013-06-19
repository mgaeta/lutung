/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillInboundDomain;
import com.microtripit.mandrillapp.lutung.view.MandrillMailboxRoute;
import junit.framework.Assert;

/**
 * @author rschreijer
 * @since Mar 22, 2013
 */
public final class MandrillInboundApiTest extends LutungApiTest {

	@Test
	public final void testDomains() throws IOException, MandrillApiError {
		MandrillInboundDomain[] domains = mandrillApi.inbound().domains();
		Assert.assertNotNull(domains);
		for(MandrillInboundDomain d : domains) {
			Assert.assertNotNull(d.getDomain());
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testRoutesWithoutDomain() throws IOException, MandrillApiError {
		mandrillApi.inbound().routes(null);
		Assert.fail();
	}

	@Test
	public final void testRoutes() throws IOException, MandrillApiError {
		MandrillInboundDomain[] domains = mandrillApi.inbound().domains();
		Assert.assertNotNull(domains);
		if(domains.length > 0) {
			MandrillMailboxRoute[] routes = mandrillApi.inbound().routes(
					domains[0].getDomain());
			Assert.assertNotNull(routes);
		}
	}
}
