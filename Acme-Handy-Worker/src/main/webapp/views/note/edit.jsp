<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="note/edit.do" modelAttribute="note">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	
	
	<form:label path="moment">
		<spring:message code="note.moment" />:
	</form:label>
	<form:input path="moment" />
	<form:errors cssClass="error" path="moment" />
	<br />

	<security:authorize access="hasRole('WORKER')">
	<form:label path="workerComment">
		<spring:message code="note.workerComment" />:
	</form:label>
	<form:textarea path="workerComment" />
	<form:errors cssClass="error" path="workerComment" />
	<br />
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<form:label path="customerComment">
		<spring:message code="customerComment" />:
	</form:label>
	<form:textarea path="customerComment" />
	<form:errors cssClass="error" path="customerComment" />
	<br />
	</security:authorize>
	
	<security:authorize access="hasRole('REFEREE')">
	<form:label path="refereeComment">
		<spring:message code="refereeComment" />:
	</form:label>
	<form:textarea path="refereeComment" />
	<form:errors cssClass="error" path="refereeComment" />
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