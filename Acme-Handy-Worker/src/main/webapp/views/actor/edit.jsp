<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><security:authorize access="hasRole('WORKER')">
<spring:message code="actor.worker" />
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
<spring:message code="actor.customer" />
</security:authorize>

<security:authorize access="hasRole('SPONSOR)">
<spring:message code="actor.sponsor" />
</security:authorize>
</h1>


<form:form action="actor/create.do" modelAttribute="actor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<security:authorize access="hasRole('WORKER') || hasRole('CUSTOMER')">
	<form:hidden path="actorments" />
	</security:authorize>
	
	<security:authorize access="hasRole('SPONSOR')">
	<form:hidden path="sponsorships" />
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
	<form:hidden path="tasks" />
	</security:authorize>
	
	<security:authorize access="hasRole('WORKER')">
	<form:hidden path="applications" />
	</security:authorize>
	
	<form:hidden path="boxes" />
	<form:hidden path="sentMessages" />
	<form:hidden path="receivedMessages" />
	
	
	<form:label path="userAccount.username">
		<spring:message code="actor.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<spring:message code="actor.password" />:
	</form:label>
	<form:input path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />
	

	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="middlename">
		<spring:message code="actor.middlename" />:
	</form:label>
	<form:input path="middlename" />
	<form:errors cssClass="error" path="middlename" />
	<br />
	
	<security:authorize access="hasRole('WORKER')">
	<form:label path="make">
		<spring:message code="actor.make" />:
	</form:label>
	<form:input path="make" />
	<form:errors cssClass="error" path="make" />
	<br />
	</security:authorize>
	
	
	
	<form:label path="photo">
		<spring:message code="actor.photo" />:
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />
	
	<form:label path="email">
		<spring:message code="actor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />
	
	<form:label path="address">
		<spring:message code="actor.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />



	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
	<jstl:if test="${actor.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="actor.delete" />"
			onclick="return confirm('<spring:message code="actor.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('actor/create.do');" />
	<br />

</form:form>


<jstl:if test="${actor.id !=0}">
			<input type="button" value="<spring:message code="actor.Profiles" />" name="button" onClick="javascript: relativeRedir('profile/list.do');"/>
</jstl:if>
