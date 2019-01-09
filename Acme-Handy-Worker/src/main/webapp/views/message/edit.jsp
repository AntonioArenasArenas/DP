<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="mensaje.new" /></p>

<form:form action="message/edit.do" modelAttribute="messaged">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	<form:hidden path="moment" />
	
	
	
	
	<form:label path="recipients">
		<spring:message code="mensaje.recipients" />:
	</form:label>
	<form:select multiple="true" id="recipients" path="recipients">
	<form:options items="${recipients}" itemLabel="email" itemValue="id" />
	</form:select>
	
	<br />
	
	
	<form:label path="subject">
		<spring:message code="mensaje.subject" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="priority">
		<spring:message code="mensaje.priority" />:
	</form:label>      
    <form:select path="priority" >
   		<form:option value="HIGH" label="High" />
   		<form:option value="LOW" label="Low" />
   		<form:option value="NEUTRAL" label="Neutral" />
   </form:select>
	<form:errors cssClass="error" path="priority" />
 	<br />
	
	<form:label path="body">
		<spring:message code="mensaje.body" />:
	</form:label>
	<form:textarea path="body" type="textarea"/>
	<form:errors cssClass="error" path="body" />
	<br />
	
	<form:label path="tags">
		<spring:message code="mensaje.tag" />:
	</form:label>
	<form:input path="tags" type="textarea"/>
	<form:errors cssClass="error" path="tags" />
	<br />
	






	<input type="submit" name="save"
		value="<spring:message code="mensaje.save" />" />&nbsp; 
	<%-- <jstl:if test="${message.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="mensaje.delete" />"
			onclick="return confirm('<spring:message code="mensaje.confirm.delete" />')" />&nbsp;
	</jstl:if> --%>
	<input type="button" name="cancel"
		value="<spring:message code="mensaje.cancel" />"
		onclick="javascript: relativeRedir('message/create.do');" />
	<br />


</form:form>