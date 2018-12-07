<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="worker/create.do" modelAttribute="worker">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="endorserments" />
	<form:hidden path="tasks" />
	<form:hidden path="boxes" />
	<form:hidden path="sentMessages" />
	<form:hidden path="receivedtMessages" />
	
	
	<form:label path="userAccount.username">
		<spring:message code="worker.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<spring:message code="worker.password" />:
	</form:label>
	<form:input path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />
	

	<form:label path="name">
		<spring:message code="worker.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="worker.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="middlename">
		<spring:message code="worker.middlename" />:
	</form:label>
	<form:input path="middlename" />
	<form:errors cssClass="error" path="middlename" />
	<br />
	
	<form:label path="make">
		<spring:message code="worker.make" />:
	</form:label>
	<form:input path="make" />
	<form:errors cssClass="error" path="make" />
	<br />
	
	<form:label path="photo">
		<spring:message code="worker.photo" />:
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />
	
	<form:label path="email">
		<spring:message code="worker.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="worker.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />
	
	<form:label path="address">
		<spring:message code="worker.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />



	<input type="submit" name="save"
		value="<spring:message code="worker.save" />" />&nbsp; 
	<jstl:if test="${worker.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="worker.delete" />"
			onclick="return confirm('<spring:message code="worker.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="worker.cancel" />"
		onclick="javascript: relativeRedir('worker/create.do');" />
	<br />


</form:form>