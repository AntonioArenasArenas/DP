<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="phase/worker/edit.do?taskId=${param['taskId']}" modelAttribute="phase">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="title">
		<spring:message code="phase.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="description">
		<spring:message code="phase.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="startDate">
		<spring:message code="phase.startDate" />:
	</form:label>
	<form:input path="startDate" placeholder="DD/MM/YYYY" />
	<form:errors cssClass="error" path="startDate" />
	<br />

	<form:label path="endDate">
		<spring:message code="phase.endDate" />:
	</form:label>
	<form:input path="endDate" placeholder="DD/MM/YYYY" />
	<form:errors cssClass="error" path="endDate" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="phase.save" />" />&nbsp; 
	<jstl:if test="${phase.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="phase.delete" />"
			onclick="return confirm('<spring:message code="phase.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="phase.cancel" />"
		onclick="javascript: relativeRedir('phase/worker/list.do?taskId=${param['taskId']}');" />
	<br />
	
</form:form>