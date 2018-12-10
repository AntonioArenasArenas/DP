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
<p><spring:message code="customer.action.1" /></p>

<jstl:set var="recipents" value="${row.recipents}" />
	
	<p><spring:message code="message.sender" />:<strong><jstl:out value="${row.sender.name}" /></strong></p>
	
	<p><spring:message code="message.recipients" />:
	<jstl:forEach var="recipient"  items="recipents" >
	 <jstl:out value="${recipient.name}" />
	</jstl:forEach></p>
	
	<p><spring:message code="message.subject" />:<strong><jstl:out value="${row.subject}" /></strong></p>
	
	<p><spring:message code="message.cuerpo" />:<jstl:out value="${row.body}" /></p>
	
	<p><spring:message code="message.tag" />:<jstl:out value="${row.tag}" /></p>
		
	