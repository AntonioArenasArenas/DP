<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->


	
	<p><strong><spring:message code="mensaje.sender" /></strong>: <jstl:out value="${mensaje.sender.email}" /></p>
	
	<p><strong><spring:message code="mensaje.recipients" /></strong>:
	<jstl:forEach var="recipient" items="${mensaje.recipients}">
	<jstl:out value="${recipient.email}"></jstl:out>
	</jstl:forEach>
	
	<br>
	<br>
	<strong><spring:message code="mensaje.priority" /></strong>: <jstl:out value="${mensaje.priority}" />
	<br>
	<p><strong><spring:message code="mensaje.subject" /></strong>: <jstl:out value="${mensaje.subject}" /></p>
	<br>
	<p><strong><spring:message code="mensaje.body" /></strong>: <jstl:out value="${mensaje.body}" /></p>
	<br>
	<p><strong><spring:message code="mensaje.tag" /></strong>: <jstl:out value="${mensaje.tags}" /></p>
	
	
   <input type="button" value="<spring:message code="mensaje.back" />" name="button" onClick="javascript: relativeRedir('box/list.do');"/>
		
	 