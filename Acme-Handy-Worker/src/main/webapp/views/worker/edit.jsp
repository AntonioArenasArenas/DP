<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="worker/edit.do" modelAttribute="worker">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="make" />
	<form:hidden path="endorsements" />
	<form:hidden path="sentMessages" />
	<form:hidden path="receivedMessages" />
	<form:hidden path="suspicious" />
	<form:hidden path="profiles" />
	<form:hidden path="boxes" />
	<form:hidden path="tutorials" />
	<form:hidden path="applications" />
	<form:hidden path="make" />
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities" value="WORKER" />
	<form:hidden path="userAccount.activated" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	
	
	
	<form:label path="userAccount.username">
		<spring:message code="actor.username" />:
	</form:label>
	<form:input  path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<spring:message code="actor.password" />:
	</form:label>
	<form:input type="password" path="userAccount.password" />
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

	<form:label path="middleName">
		<spring:message code="actor.middleName" />:
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />

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
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('actor/create.do');" />
	<br />

</form:form>


<jstl:if test="${worker.id !=0}">
			<input type="button" value="<spring:message code="actor.Profiles" />" name="button" onClick="javascript: relativeRedir('profile/list.do');"/>
</jstl:if> 
