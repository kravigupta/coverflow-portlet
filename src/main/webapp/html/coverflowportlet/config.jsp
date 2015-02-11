<%@include file="./init.jsp"%>
<liferay-ui:success key="successFolderId"
	message="FolderId saved successfully"></liferay-ui:success>

<%
	ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	String folderId = (String)request.getAttribute(CoverFlowConstants.FOLDER_ID);

	long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	long repositoryId = themeDisplay.getScopeGroupId();
	List<Folder> folders = DLAppLocalServiceUtil.getFolders(repositoryId, parentFolderId);
%>
<form id="folderIdSubmitForm"
	action='<liferay-portlet:actionURL portletConfiguration="true" ></liferay-portlet:actionURL>'
	method="post">
	<div>
		<h3>Select Document Library Folder ID</h3>
		<p>Note: Please check if the folder and images under the folder
			have proper permissions.</p>
		<table class="tableForm">
			<tr>
				<th>Name</th>
				<th>Action</th>
			</tr>
			<%
			if(folders.size()==0){
				%>
				<tr><td  colspan="2"><div class='portlet-description  aui-liferaymessage-content lfr-message-content lfr-message-help'>There is no folder present in the repository. Please create one to store images for cover flow.</div></td></tr>
				<%
			}
				for(Folder f: folders){
			%>
			<tr title="<%=f.getFolderId()%>">
				<td><b><%=f.getName()%></b></td>
				<td><%
				if(!(StringPool.BLANK.equalsIgnoreCase(folderId)) && Long.toString(f.getFolderId()).equalsIgnoreCase(folderId)){
					%>Current Selected Folder<%
				}else{
					%><input class='btn btn-default folderIdBtn' value="Select"
					type="button" /><%
				}
				%></td>
			</tr>
			<%
				}
			%>
		</table>

		<input type="hidden" name='<portlet:namespace/>folderId' value=""
			id="folderId" />
	</div>
</form>

<hr />
<liferay-ui:success key="successImageLimit"
	message="Image Limit stored successfully"></liferay-ui:success>
<liferay-ui:error key="improperNumber"
	message="Image limit was not a proper number"></liferay-ui:error>

<%
String imageLimit =  (String)request.getAttribute(CoverFlowConstants.CF_IMAGES_LIMIT);
%>
<h3>Please enter a limit for the images in Cover Flow</h3>
<span>Note: Please make sure that the limit is not a big number, else the content/cover flow will be slow.</span>
<form 
	action='<liferay-portlet:actionURL portletConfiguration="true" ></liferay-portlet:actionURL>'
	method="post">
	
	<input type="text" name="<%=CoverFlowConstants.CF_IMAGES_LIMIT %>" value="<%=imageLimit %>" />
	<input type="submit" value="Save Image Limit" /> 

</form>

<script type="text/javascript">
	
</script>
<hr />