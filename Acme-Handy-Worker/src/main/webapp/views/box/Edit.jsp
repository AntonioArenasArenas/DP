<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="box.edit" /></p>

<form:form action="box/edit.do" modelAttribute="box">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="messages" />
	<form:hidden path="isDefault" />
	
	
	
	<form:label path="box.name">
		<spring:message code="box.name" />:
	</form:label>
	<form:input path="box.name" />
	<form:errors cssClass="error" path="box.name" />
	<br />
	




	<input type="submit" name="save"
		value="<spring:message code="box.save" />" />&nbsp; 
	<jstl:if test="${box.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="box.delete" />"
			onclick="return confirm('<spring:message code="box.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="box.cancel" />"
		onclick="javascript: relativeRedir('box/create.do');" />
	<br />


</form:form>