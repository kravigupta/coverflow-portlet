<%@include file="./init.jsp" %>

<portlet:defineObjects />
<%
PortletPreferences prefs = renderRequest.getPreferences();
String folderIdStr = prefs.getValue(CoverFlowConstants.FOLDER_ID, StringPool.BLANK);
if(StringPool.BLANK.equalsIgnoreCase(folderIdStr)){
%>
	<div>
	<p>Please configure this portlet to use. You, at least, need to provide a folder where all the images are.</p>
	</div>
<%	
}else{

	String imagesLimitStr = prefs.getValue(CoverFlowConstants.CF_IMAGES_LIMIT, StringPool.BLANK);
	long folderId = Long.parseLong(folderIdStr);
	long imagesLimit = 0; 
	if(!StringPool.BLANK.equalsIgnoreCase(imagesLimitStr)){
		imagesLimit =Long.parseLong(imagesLimitStr); 
	}
	if(imagesLimit == 0){
		imagesLimit = CoverFlowConstants.IMAGES_LIMIT;
	}
	ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	         List<FileEntry> images = DLAppLocalServiceUtil.getFileEntries(themeDisplay.getScopeGroupId(), folderId);   
	%>
	<script type="text/javascript">
	$(document).ready(function(){
		var cf = new ContentFlow('contentFlow', {reflectionColor: "#000000"});
	});
	    
	</script>
	<div id="contentFlow" class="ContentFlow">
	    <!-- should be place before flow so that contained images will be loaded first -->
	    <div class="loadIndicator"><div class="indicator"></div></div>
	    <div class="flow">
	        <%
	            for (int idx = 0; idx < imagesLimit; idx++) {
	                FileEntry image = images.get(idx);
	        %>
	        <div class="item">
	            <img class="content" src="/documents/<%=themeDisplay.getScopeGroupId()%>/<%=folderId %>/<%=image.getTitle()%>"/>
	            <div class="caption"><%=image.getDescription() %></div>
	        </div>
	        <%
	            }
	        %>
	    </div>
	    <div class="globalCaption"></div>
	    <div class="scrollbar">
	        <div class="slider"><div class="position"></div></div>
	    </div>
	
	</div>
<% } %>