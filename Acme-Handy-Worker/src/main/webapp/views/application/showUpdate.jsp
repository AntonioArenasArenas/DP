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
	<spring:message code="applications.update.1" />
	${id}
</p>
<!-- Si la variable es true todos los campos como inputs, si no, algunos como messages en p y otros como inputs segun convenga -->

<p>
	<spring:message code="applications.update.date" />
	${moment}
</p>
<p>
	<spring:message code="applications.list.price" />
	${offeredPrize}
</p>
<p>
	<spring:message code="applications.update.comments" />
	${comments}
</p>

<jstl:choose>
	<jstl:when test="${update}">
		<form:form modelAttribute="application"
			action="application/customer/edit.do">
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="moment" />
			<form:hidden path="offeredPrize" />

			<form:label path="status">
				<spring:message code="applications.update.status" />
			</form:label>
			<form:input path="status" />
			<form:errors cssClass="error" path="status" />

			<form:label path="comments">
				<spring:message code="applications.update.comments" />
			</form:label>
			<form:textarea path="comments" />
			
			


			<input type="submit" name="save"
				value="<spring:message code="applications.update.save" />" />

			<input type="button" name="cancel"
				value="<spring:message code="task.cancel" />"
				onclick="javascript: relativeRedir('application/customer/list.do');" />
		</form:form>

	</jstl:when>


	<jstl:when test="${!update}">
		<p>
			<spring:message code="applications.update.status" />
			${status}
		</p>

	</jstl:when>
</jstl:choose>


