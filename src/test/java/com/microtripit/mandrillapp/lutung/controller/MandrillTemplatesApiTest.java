/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTemplate;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;
import junit.framework.Assert;

/**
 * @author rschreijer
 * @since Mar 22, 2013
 */
public final class MandrillTemplatesApiTest extends LutungApiTest {
	private static String templateName;
  private static String defaultContent = "<html><body><h1>Hello World!</h1></body></html>";

	@BeforeClass
	public static final void runBeforeClass() {
		templateName = "lutung_templatename_unit_test_" + System.currentTimeMillis();
	}

	@Test(expected=MandrillApiError.class)
	public final void testAddWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().add(null, defaultContent, null);
		Assert.fail();
	}

	@Test
	public final void testAddWithoutContent() throws IOException, MandrillApiError {
		final MandrillTemplate t = mandrillApi.templates().add(templateName, null, null);
    Assert.assertNotNull(t);
    Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
  }

	@Test
	public final void testAdd() throws IOException, MandrillApiError {
		final MandrillTemplate t = mandrillApi.templates().add(templateName, defaultContent, false);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		for(MandrillTemplate t : templates) {
			Assert.assertNotNull(t.getName());
			Assert.assertNotNull(t.getCreatedAt());
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testInfoWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().info(null);
		Assert.fail();
	}

	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTemplate t = mandrillApi.templates().info(templates[0].getName());
			Assert.assertNotNull(t);
			Assert.assertNotNull(t.getName());
			Assert.assertNotNull(t.getCode());
			Assert.assertNotNull(t.getCreatedAt());
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().update(null, defaultContent, false);
		Assert.fail();
	}

	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutCode() throws IOException, MandrillApiError {
		mandrillApi.templates().update(templateName, null, false);
		Assert.fail();
	}

	@Test
	public final void testUpdate() throws IOException, MandrillApiError {
		final MandrillTemplate t = mandrillApi.templates().add(templateName, defaultContent, false);
		Assert.assertNotNull(t);
		final String updatedCode = "UPDATED_CODE";
    final MandrillTemplate t2 = mandrillApi.templates().update(t.getName(), updatedCode, false);
		Assert.assertNotNull(t2);
		Assert.assertNotNull(t2.getName());
		Assert.assertEquals("L{\"html\":\"" + updatedCode + "\"}", t2.getCode());
		Assert.assertNotNull(t2.getCreatedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t2.getName()) );
	}

	@Test(expected=MandrillApiError.class)
	public final void testPublishWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().publish(null);
		Assert.fail();
	}

	@Test
	public final void testPublish() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(templateName, defaultContent, false);
		Assert.assertNotNull(t);
		Assert.assertNull(t.getPublishedAt());
		t = mandrillApi.templates().publish(t.getName());
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getPublishedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test(expected=MandrillApiError.class)
	public final void testDeleteWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().delete(null);
		Assert.fail();
	}

	@Test
	public final void testDelete() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(templateName, defaultContent, false);
		Assert.assertNotNull(t);
		t = mandrillApi.templates().delete(t.getName());
		Assert.assertNotNull(t);
	}

	@Test(expected=MandrillApiError.class)
	public final void testTimeSeriesWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().timeSeries(null);
		Assert.fail();
	}

	@Test
	public final void testTimeSeries() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTimeSeries[] series =
					mandrillApi.templates().timeSeries(templates[0].getName());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries t : series) {
				Assert.assertNotNull(t.getTime());
			}
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().render(null, new HashMap<String,String>(), null);
		Assert.fail();
	}

	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutContent() throws IOException, MandrillApiError {
		mandrillApi.templates().render(templateName, null, null);
		Assert.fail();
	}

	@Test
	public final void testRender() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(templateName, defaultContent, false);
		Assert.assertNotNull(t);
		final String rendered = mandrillApi.templates().render(
				t.getName(),
				new HashMap<String,String>(),
				null);
		Assert.assertNotNull(rendered);
		Assert.assertEquals(defaultContent, rendered);
	}

}
