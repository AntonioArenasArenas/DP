<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="tutorial/create.do" modelAttribute="tutorial">

	<form:hidden path="id" />
	<form:hidden path="version" />
	


	<form:label path="tutorial.title">
		<spring:message code="tutorial.title" />:
	</form:label>
	<form:input path="tutorial.title"  />
	<form:errors cssClass="error" path="tutorial.title" />
	<br />
	
	
	<form:label path="tutorial.socialNetwork">
		<spring:message code="tutorial.socialNetwork" />:
	</form:label>
	<form:input path="tutorial.socialNetwork" />
	<form:errors cssClass="error" path="tutorial.socialNetwork" />
	<br />
	

	<form:label path="tutorial.link">
		<spring:message  code="tutorial.link" />:
	</form:label>
	<form:input path="tutorial.link" />
	<form:errors cssClass="error" path="tutorial.link" />
	<br />
	
	

	<input type="submit" name="save"
		value="<spring:message  code="tutorial.save" />" />&nbsp; 
	<jstl:if test="${tutorial.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message  code="tutorial.delete" />"
			onclick="return confirm('<spring:pmessage  code="tutorial.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message  code="tutorial.cancel" />"
		onclick="javascript: relativeRedir('welcome/back.do');" />
	<br />

</form:form>