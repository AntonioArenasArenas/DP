<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="report/edit.do" modelAttribute="report">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	
	
	<form:label path="moment">
		<spring:message code="report.moment" />:
	</form:label>
	<form:input path="moment" />
	<form:errors cssClass="error" path="moment" />
	<br />

	<form:label path="description">
		<spring:message code="report.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="attachments">
		<spring:message code="report.attachments" />:
	</form:label>
	<form:textarea path="attachments" />
	<form:errors cssClass="error" path="attachments" />
	<br />
	
	<security:authorize access="hasRole('REFEREE')">
	<form:label path="finalMode">
		<spring:message code="report.finalMode" />:
	</form:label>
	<form:checkbox path="finalMode" />
	<form:errors cssClass="error" path="finalMode" />
	<br />
	</security:authorize>


	
	<input type="button" name="create"
		value="<spring:message code="report.create" />" />&nbsp; 
		
	
		
	
	<security:authorize access="hasRole('CUSTOMER')">
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('report/customer/list.do');" />
	
	</security:authorize>
	
	<security:authorize access="hasRole('REFEREE')">
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('report/referee/list.do');" />
	
	</security:authorize>
	
	<security:authorize access="hasRole('WORKER')">
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('report/worker/list.do');" />
	
	</security:authorize>
	</form:form>