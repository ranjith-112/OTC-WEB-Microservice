<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Event</title>
<link rel="stylesheet" type="text/css" href="/css/eventform.css">
<script src="/js/validation.js"></script>
<script src="/js/event.js"></script>

</head>
<body>
	<!-- Add the logo image -->
	<div class="logo" id="logo">
		<img
			src="https://boomi.com/wp-content/uploads/changepond-partner-logo-300x188.jpg"
			alt="Logo" class="img-fluid">
	</div>
	<div class="event-form">
		<div class="row mt-3"></div>
		<h2>Create Event</h2>

		<form:form action="/events" modelAttribute="eventDTO"
			onsubmit="showSuccessPopup(); return false; " id="eventForm"
			enctype="multipart/form-data">
			<div>
				<form:input path="id" id="eventid" />
				<form:label path="eventname">Event Name:</form:label>
				<form:input path="eventname" id="eventname" name="eventname"
					oninput="validateEventForm()" />
				<div>
					<form:errors path="eventname" cssClass="error" />
				</div>
				<div id="div1"></div>


			</div>
			<div>
				<form:label path="details">Event Details:</form:label>
				<form:textarea path="details" id="details" name="details" rows="4"
					cols="50" oninput="validateEventForm()" required="required"></form:textarea>

				<form:errors path="details" cssClass="error" />
				<div id="div2"></div>

			</div>
			<div>
				<form:label path="startdate">Start Date:</form:label>
				<form:input path="startdate" type="datetime-local" id="startdate"
					name="startdate" required="required" />
				<form:errors path="startdate" cssClass="error" />
				<br>
			</div>
			<div>
				<form:label path="duration">Duration in mins:</form:label>
				<form:input path="duration" id="duration" name="duration"
					oninput="validateEventForm()" required="required" />
				<form:errors path="duration" cssClass="error" />
				<div id="div3"></div>
			</div>

			<div>
				<form:label path="trainer">Trainer:</form:label>
				<form:select path="trainer" required="required">
					<form:options items="${trainerNames}" />
				</form:select>
			</div>
			<div>
				<form:label path="domain">Domain</form:label>
				<form:select path="domain" onChange="checkingDomainDropdown()">
					<form:options items="${dropdownDomainValues}" />
					
				</form:select>
				<input type="text"  id="domaintext" name="domain" /> 
				<form:errors path="domain" cssClass="error" />
			</div>
			<div>
				<form:label path="status">Status:</form:label>
				<form:select path="status" id="status" name="status"
					onclick="checkingStatusDropdown()" required="required">
					<form:options items="${dropdownStatusValues}" />
					<%-- <form:option value="scheduled" selected="selected">Scheduled</form:option>
					<form:option value="canceled" disabled="disabled">Canceled</form:option>
					<form:option value="inprogress" disabled="disabled">In Progress</form:option>
					<form:option value="onhold" disabled="disabled">On-Hold</form:option>
					<form:option value="completed " disabled="disabled">Completed</form:option>
					<form:option value="closed" disabled="disabled">Closed</form:option> --%>
				</form:select>
				<form:errors path="status" cssClass="error" />
			</div>
			<div>
				<form:label path="location">Location:</form:label>
				<form:select path="location" id="location" name="location"
					required="required">
					<form:options items="${location}" />
				</form:select>
				<form:errors path="location" cssClass="error" />
			</div>
			<div>
				<form:label path="multifilename">Choose a file to upload:</form:label>
				<form:input type="file" path="multifilename" id="file" name="file"
					accept="image/png, image/jpeg,.pdf,.txt"  required="required"/>
			</div>


			<!-- Assuming you have a form with the id "eventForm" and appropriate form fields -->

			<div>
				<!-- Show the "Create" button if the form is in create mode -->
				<input id="button" type="submit" value="Create"
					onclick="createEvent()" />
			</div>

			<div>
				<a href="/back"> <input id="button" type="button" value="back" /></a>
			</div>

		</form:form>
	</div>
	 <div id="footer"><jsp:include page="footer.jsp" /></div>
</body>
</html>
