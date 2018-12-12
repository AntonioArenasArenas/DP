<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<p><spring:message code="tutorial.title" />:<jstl:out value="${row.title}" />
	<br>
	<spring:message code="tutorial.summary" />:<jstl:out value="${row.summary}" />
	<br>
	
	<jstl:forEach var="section" items="${row.sections}" > 
		<a href="section/edit.do?sectionId=${row.id}" >
			<jstl:out value="${section.title}" />
		</a>
		<jstl:out value="${section.text}" />
		<jstl:out value="${section.photo}" />
	</jstl:forEach>
	
	
	<input type="button" value="<spring:message code="tutorial.cancel" />" name="button" onClick="javascript: relativeRedir('tutorial/list.do');"/>
		
	