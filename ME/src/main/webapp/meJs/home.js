function openPopup() {
	var popupContainer = document.getElementById("popupContainer");
	var popupIframe = document.getElementById("popupIframe");
	var jspPageURL = "login.jsp";

	popupIframe.src = jspPageURL;
	popupContainer.style.display = "block";
}

function closePopup() {
	var popupContainer = document.getElementById("popupContainer");
	var popupIframe = document.getElementById("popupIframe");

	popupIframe.src = "";
	popupContainer.style.display = "none";
}

document.addEventListener("DOMContentLoaded", function() {

var checkbox = document.getElementById("auth");

// Check if the checkbox is checked
if (checkbox != null && checkbox.checked) {
	// Checkbox is checked
	openPageOutsideIframe();
	console.log("Checkbox is checked");
} else {
	// Checkbox is not checked
	console.log("Checkbox is not checked");
}
});


// After a successful login, call this function to open the JSP page outside of the iframe
function openPageOutsideIframe() {
	var popupContainer = document.getElementById("popupContainer");
	var popupIframe = document.getElementById("popupIframe");
	var jspPageURL = "dashboard.jsp";

	// Set the src attribute of the iframe to an empty value to unload the previous page
	popupIframe.src = "";

	// Hide the popup container
	popupContainer.style.display = "none";

	// Open the JSP page in the parent window
	window.open(jspPageURL, "_parent");
	//window.location.href = jspPageURL;
}
