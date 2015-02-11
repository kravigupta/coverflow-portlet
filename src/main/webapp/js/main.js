$(document).ready(function() {
		$(".folderIdBtn").click(function(event) {
			event.preventDefault();
			$("#folderId").val($(this).closest("tr").attr("title"));
			$("#folderIdSubmitForm").submit();
		});
	});