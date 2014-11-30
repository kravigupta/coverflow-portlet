package me.rkg.plugins.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import me.rkg.plugins.util.CoverFlowConstants;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

/**
 * @author Ravi Kumar Gupta
 *
 */
public class CoverFlowCongurationActionImpl extends DefaultConfigurationAction {
	
	/*
	 * To load page with previously selected folder id and images limit
	 * 
	 * @param renderRequest
	 * 
	 * @param renderRequest
	 * 
	 * @param renderResponse
	 * 
	 * @return
	 */
	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse)
			throws SystemException, IOException, PortletException {
		final PortletPreferences preferences = getPortletPreferences(renderRequest);
		if (Validator.isNotNull(preferences)) {
			String folderId = preferences.getValue(CoverFlowConstants.FOLDER_ID,
					StringPool.BLANK);
			renderRequest.setAttribute(CoverFlowConstants.FOLDER_ID, folderId);
			String cfImagesLimit = preferences.getValue(CoverFlowConstants.CF_IMAGES_LIMIT,
					StringPool.BLANK);
			renderRequest.setAttribute(CoverFlowConstants.CF_IMAGES_LIMIT, cfImagesLimit);
		}

		return CoverFlowConstants.CONFIGURATIONPAGE;
	}

	@Override
	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		final PortletPreferences preferences = getPortletPreferences(actionRequest);

		String folderId = ParamUtil.get(actionRequest, CoverFlowConstants.FOLDER_ID,
				StringPool.BLANK);

		if(!StringPool.BLANK.equalsIgnoreCase(folderId)){
			preferences.setValue(CoverFlowConstants.FOLDER_ID, folderId.toString());
			
			LOG.info("FolderId :"+folderId +" stored to preferences");
			SessionMessages.add(actionRequest, CoverFlowConstants.SUCCESS_FOLDER_ID);
		}
		
		String cfImagesLimit = ParamUtil.get(actionRequest, CoverFlowConstants.CF_IMAGES_LIMIT,
				StringPool.BLANK);
		if(!StringPool.BLANK.equalsIgnoreCase(cfImagesLimit)){
			try{
				Integer.parseInt(cfImagesLimit);
				preferences.setValue(CoverFlowConstants.CF_IMAGES_LIMIT, cfImagesLimit.toString());
				LOG.info("Image Limit :"+cfImagesLimit +" stored to preferences");
				SessionMessages.add(actionRequest, CoverFlowConstants.SUCCESS_IMAGE_LIMIT);
			}catch(NumberFormatException nfe){
				SessionErrors.add(actionRequest, CoverFlowConstants.IMPROPER_NUMBER);
				LOG.error("Image Limit was not a proper number, reverting to image limit from properties");
				preferences.setValue(CoverFlowConstants.CF_IMAGES_LIMIT, Integer.toString(CoverFlowConstants.IMAGES_LIMIT));
				LOG.info("Image Limit :"+CoverFlowConstants.IMAGES_LIMIT +" stored to preferences");
			}
		}
		preferences.store();
		SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");

		
	}
	
	/***
	 * 
	 * Get Portlet Preferences
	 * 
	 * @param portletRequest
	 * @return
	 */
	private PortletPreferences getPortletPreferences(final PortletRequest portletRequest) {
		final String portletResource = ParamUtil.getString(portletRequest, "portletResource");

		PortletPreferences preferences = null;
		try {
			preferences = PortletPreferencesFactoryUtil.getPortletSetup(portletRequest, portletResource);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}

		if (preferences == null) {
			preferences = portletRequest.getPreferences();
		}

		return preferences;
	}
	private static final Log LOG = LogFactoryUtil.getLog(CoverFlowCongurationActionImpl.class);

}
