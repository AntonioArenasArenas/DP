<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="message.edit" /></p>

<form:form action="message/edit.do" modelAttribute="message">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	
	
	
	
	<form:label path="message.recipients">
		<spring:message code="message.recipients" />:
	</form:label>
	<form:input path="message.recipients" type="textarea" />
	<form:errors cssClass="error" path="message.recipients" />
	<br />
	
	
	<form:label path="message.subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input path="message.subject" />
	<form:errors cssClass="error" path="message.subject" />
	<br />
	
	<form:label path="message.priority">
		<spring:message code="message.priority" />:
	</form:label>      
    <input type="radio" name="gender" value="HIGH" />
    <input type="radio" name="gender" value="NEUTRAL" /> 
    <input type="radio" name="gender" value="LOW" />
 	<br />
	
	<form:label path="message.body">
		<spring:message code="message.body" />:
	</form:label>
	<form:input path="message.body" type="textarea"/>
	<form:errors cssClass="error" path="message.body" />
	<br />
	
	<form:label path="message.tag">
		<spring:message code="message.tag" />:
	</form:label>
	<form:input path="message.tag" type="textarea"/>
	<form:errors cssClass="error" path="message.tag" />
	<br />
	




	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<jstl:if test="${message.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="message.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('message/create.do');" />
	<br />


</form:form>