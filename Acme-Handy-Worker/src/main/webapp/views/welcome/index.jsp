<%--
 * index.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p id="en" >
${data.welcomePageMsg}
</p>

<p id="es" >
${data.welcomePageMsgESP}
</p>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

<script >
function getCookie(cname) {
	try {
	  var name = cname + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "en";
	}
	catch (err) {
		return "en";
	}
}
	
	function checkCookie() {
	  var language = getCookie("language");
	  if (language == 'es' ) {
		  $("#en").remove();
		  
	  } else {
	      $("#es").remove();
	      
	  }
	}
	
	$(function(){checkCookie(); } );
</script>
