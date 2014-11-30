package me.rkg.plugins.portlets;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import me.rkg.plugins.util.CoverFlowConstants;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * @author Ravi Kumar Gupta
 *
 */
public class CoverFlowPortlet extends MVCPortlet {

	/***
	 * 
	 * Get Portlet Preferences
	 * 
	 * @param portletRequest
	 * @return
	 */
	private PortletPreferences getPortletPreferences(final PortletRequest portletRequest) {
		PortletPreferences preferences = portletRequest.getPreferences();

		if (preferences == null) {
			try {
				final String portletResource = ParamUtil.getString(portletRequest, "portletResource");
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(portletRequest, portletResource);
			} catch (final Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return preferences;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liferay.util.bridges.mvc.MVCPortlet#doView(javax.portlet.RenderRequest
	 * , javax.portlet.RenderResponse)
	 */
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		final PortletPreferences preferences = getPortletPreferences(renderRequest);

		String viewJSP = viewTemplate;
		
		if (Validator.isNotNull(preferences)) {
			String folderId = preferences.getValue(CoverFlowConstants.FOLDER_ID,
					StringPool.BLANK);
			renderRequest.setAttribute(CoverFlowConstants.FOLDER_ID, folderId);
			String cfImagesLimit = preferences.getValue(CoverFlowConstants.CF_IMAGES_LIMIT,
					StringPool.BLANK);
			renderRequest.setAttribute(CoverFlowConstants.CF_IMAGES_LIMIT, cfImagesLimit);
			LOG.info("Found folderId "+ folderId);
			LOG.info("Found imageLimit "+ cfImagesLimit);
		}else{
			if(LOG.isDebugEnabled()){
				LOG.debug("Preferences for the portlet found null "+ preferences);
			}
		}
		include(viewJSP, renderRequest, renderResponse);
	}
	private static final Log LOG = LogFactoryUtil.getLog(CoverFlowPortlet.class);
}
