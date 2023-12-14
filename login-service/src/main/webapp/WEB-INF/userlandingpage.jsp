<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User landing Page</title>
<link rel="stylesheet" type="text/css" href="/css/userlandingpage.css">
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/event.js"></script>
<script type="text/javascript" src="/js/eventfilter.js"></script>


</head>
<body>
	<div class="logo" id="logo">
		<img
			src="https://boomi.com/wp-content/uploads/changepond-partner-logo-300x188.jpg"
			alt="Logo" class="img-fluid">
	</div>

	<span id="user"> <img class="small-icon"
		src="https://static-00.iconduck.com/assets.00/user-icon-2048x2048-ihoxz4vq.png"
		alt="Image description"> <b>${role} : ${User} </b>
	</span>


	<div id="logout">
		<div>
			<form:form action="logout">
				<input type="submit" id="logout" value="logout" />
			</form:form>
		</div>

	</div>



	<div>
		<input type="text" id="role" value="${role}" />
	</div>
	<div id=addeventandsearch>
		<!-- <div id="addevent"> -->
		<a href="/events/new"> <input type="button" id="addevent"
			value="Add Event" /></a>
		<!-- 	</div> -->
		Search : <select id="searchBy" onchange="getSearchValue()">
			<option value="id">By event Id</option>
			<option value="eventname">By event name</option>
			<option value="startdate">By date</option>
			<option value="duration">By duration</option>
			<option value="status">By status</option>
		</select>
	</div>
	<div id=search1>
		<input type="search" id="myInput" onsearch="clearText()" /> <input
			type="submit" value="Search" onclick="filterFunction()" />
	</div>

	<div class="divTable" id="table">
		<div class="headerRow">
			<div class="divCell">
				<b>Event Id</b>
			</div>
			<div class="divCell">
				<b>Event name</b>
			</div>
			<div class="divCell">
				<b>Start Date</b>
			</div>

			<div class="divCell">
				<b>Duration</b>
			</div>
			<div class="divCell">
				<b>Status</b>
			</div>
			<div class="divCell" id="updatehead">
				<b>Update</b>
			</div>
			<div class="divCell"></div>
			<div class="divCell">
				<b>Attachment</b>
			</div>
		</div>

		<div class="dataRow" id="row"></div>

		<div id="paginationContainer" class="pagination-container"></div>
		<div>
			<button class="delete-button" id="delete" onclick="deleteAction()">delete</button>
		</div>
	</div>

	<div id="eventopt">
		Events per page:<select id="eventno" onchange="getPageSize()">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
		</select>
	</div>
	<div id="selectanddeselect">
		<div class="divCell" id="selectALL">
			<button onclick="selectAll()">
				<b>SelectAll</b>
			</button>
		</div>
		<div class="divCell">
			<button onclick="deselectAll()">
				<b>DeselectAll</b>
			</button>
		</div>
	</div>
	<div id=totalrecords></div>

	<div id="overlay"></div>
	<div id="popup">
		<div class="popupcontrols">
			<span id="popupclose">X</span>
		</div>
		<div class="popupcontent"></div>
	</div>
	</div>
	<!-- <div id="paginationContainer" class="pagination-container"></div>
	 -->
	<div id="footer"><jsp:include page="footer.jsp" /></div>
</body>
</html>