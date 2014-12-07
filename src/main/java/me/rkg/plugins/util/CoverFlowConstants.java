package me.rkg.plugins.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Ravi Kumar Gupta
 *
 */
public final class CoverFlowConstants {
	private CoverFlowConstants() {
	}
	
	public static String FOLDER_ID = "folderId";
	public static final String SUCCESS = "success";
	public static final String SUCCESS_FOLDER_ID = "successFolderId";
	public static final String SUCCESS_IMAGE_LIMIT = "successImageLimit";
	
	public static final String NOFOLDERID = "nofolderid";
	public static final String IMPROPER_NUMBER = "improperNumber";
	public static final String GROUP_ID = "groupId";
	public static final String CF_IMAGES_LIMIT = "cfImagesLimit";
	
	public static final String MIME_JPEG = "image/jpeg";
	public static final String MIME_JPG = "image/jpg";
	public static final String MIME_PNG = "image/png";
	
	public static final int DEFAULT_IMAGE_LIMIT = 6;
	
	public static final int IMAGES_LIMIT = (PortletProps.contains("images-limit") ? Integer.parseInt(PortletProps
			.get("images-limit")) : DEFAULT_IMAGE_LIMIT);
	public static String CONFIGURATIONPAGE = "/html/coverflowportlet/config.jsp";
}
