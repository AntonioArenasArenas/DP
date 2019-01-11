<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="section/create.do" modelAttribute="section">

	<form:hidden path="id" />
	<form:hidden path="version" />
	


	<form:label path="section.title">
		<spring:message code="section.title" />:
	</form:label>
	<form:input path="section.title"  />
	<form:errors cssClass="error" path="section.title" />
	<br />
	
	
	<form:label path="section.text" type="textarea" >
		<spring:message code="section.text" />:
	</form:label>
	<form:input path="section.text" />
	<form:errors cssClass="error" path="section.text" />
	<br />
	

	<form:label path="section.picture">
		<spring:message  code="section.picture" />:
	</form:label>
	<form:input path="section.picture" />
	<form:errors cssClass="error" path="section.picture" />
	<br />
	
	

	<input type="submit" name="save"
		value="<spring:message  code="section.save" />" />&nbsp; 
	<jstl:if test="${section.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message  code="section.delete" />"
			onclick="return confirm('<spring:pmessage  code="section.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message  code="section.cancel" />"
		onclick="javascript: relativeRedir('tutorial/show.do');" />
	<br />

</form:form>