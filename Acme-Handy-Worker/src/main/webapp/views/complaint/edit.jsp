<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="complaint/edit.do" modelAttribute="complaint">



	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="ticker" />
	<form:hidden path="fixUpTask" />
	<form:hidden path="customer" />

	<form:label path="description">
		<spring:message code="complaint.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />


	<form:label path="attachments">
		<spring:message code="complaint.attachments" />:
	</form:label>
	<form:textarea path="attachments" />
	<form:errors cssClass="error" path="attachments" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="complaint.save" />" />
	<spring:message code="complaint.cancel" var="cancel"/>
	<input type="button" name="cancel" value="${cancel}" onclick="javascript:relativeRedir('complaint/customer/list.do?customerId=${customerId}');" />

	
</form:form>