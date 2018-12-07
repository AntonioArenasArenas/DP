<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<p>
	<spring:message code="applications.create" />
</p>

<form:form modelAttribute="application"
	action="application/customer/create.do">

	<form:label path="offeredPrize">
		<spring:message code="applications.list.price" />
	</form:label>
	<form:input path="offeredPrize" />
	<form:errors cssClass="error" path="offeredPrize" />

	<form:label path="comments">
		<spring:message code="applications.update.comments" />
	</form:label>
	<form:textarea path="comments" />


	<input type="submit" name="save"
		value="<spring:message code="applications.update.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="task.cancel" />"
		onclick="javascript: relativeRedir('task/worker/show.do?taskId=${id}');" />
</form:form>



