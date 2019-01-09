<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="profile/edit.do" modelAttribute="profile">

	<form:hidden path="id" />
	<form:hidden path="version" />


	<form:label path="nick">
		<spring:message code="profile.nick" />:
	</form:label>
	<form:input path="nick"  />
	<form:errors cssClass="error" path="nick" />
	<br />
	
	
	<form:label path="socialNetwork">
		<spring:message code="profile.socialNetwork" />:
	</form:label>
	<form:input path="socialNetwork" />
	<form:errors cssClass="error" path="socialNetwork" />
	<br />
	

	<form:label path="link">
		<spring:message  code="profile.link" />:
	</form:label>
	<form:input path="link" />
	<form:errors cssClass="error" path="link" />
	<br />
	
	

	<input type="submit" name="save"
		value="<spring:message  code="profile.save" />" />&nbsp; 
	<%-- <jstl:if test="${profile.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message  code="profile.delete" />"
			onclick="return confirm('<spring:pmessage  code="profile.confirm.delete" />')" />&nbsp;
	</jstl:if> --%>
	<input type="button" name="cancel"
		value="<spring:message  code="profile.cancel" />"
		onclick="javascript: relativeRedir('profile/list.do');" />
	<br />

</form:form>