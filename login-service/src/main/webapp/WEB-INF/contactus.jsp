<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Us</title>
<link rel="stylesheet" type="text/css" href="/css/contactus.css">
<script src="/js/validation.js"></script>
</head>
<body>
	  <div class="contact-container">
        <h1>Contact Us</h1>
        <p>If you have any questions or feedback, please feel free to reach out to us.</p>
        <form onsubmit="contactSucesspopup()" action="/submitForm" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="message">Message:</label>
            <textarea id="message" name="message" required></textarea>
            <button type="submit">Submit</button><a href="/back"> <input id="button" type="button" value="back" /></a>
        </form>
    </div>
</body>
</html>
