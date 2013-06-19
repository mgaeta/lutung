/**
 *
 */
package com.microtripit.mandrillapp.lutung.model;

/**
 * @author rschreijer
 * @since Mar 18, 2013
 */
public final class MandrillHelperClasses {

	/**
	 * @author rschreijer
	 * @since Mar 18, 2013
	 */
	public static class MandrillRejectsDeleted {
		private String email;
		private Boolean deleted;

		/**
		 * @return the email
		 */
		public final String getEmail() {
			return email;
		}
		/**
		 * @return the deleted
		 */
		public final Boolean getDeleted() {
			return deleted;
		}

	}

	/**
	 * <p>The result of rendering the given template with the
	 * content and merge field values injected.</p>
	 * @author rschreijer
	 * @since Mar 18, 2013
	 */
	public static final class MandrillRenderTemplateResponse {
		private String html;

		/**
		 * @return The rendered HTML as a string.
		 */
		public final String getHtml() {
			return html;
		}
	}

}
