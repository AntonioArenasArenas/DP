<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="warranty/admin/edit.do" modelAttribute="warranty">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="title">
		<spring:message code="warranty.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="terms">
		<spring:message code="warranty.terms" />:
	</form:label>
	<form:textarea path="terms" />
	<form:errors cssClass="error" path="terms" />
	<br />

	<form:label path="laws">
		<spring:message code="warranty.laws" />:
	</form:label>
	<form:textarea path="laws" />
	<form:errors cssClass="error" path="laws" />
	<br />
	
	<form:label path="draftMode">
		<spring:message code="warranty.draftMode" />:
	</form:label>
	<form:checkbox path="draftMode" /> <!-- TODO: No sé si esto está bien -->
	<form:errors cssClass="error" path="draftMode" />
	<br />
	
	<jstl:if test="${warranty.draftMode == True}">
		<input type="submit" name="save"
			value="<spring:message code="warranty.save" />" />&nbsp; 
		<jstl:if test="${warranty.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="warranty.delete" />"
				onclick="return confirm('<spring:message code="warranty.confirm.delete" />')" />&nbsp;
		</jstl:if>
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="warranty.cancel" />"
		onclick="javascript: relativeRedir('warranty/admin/list.do');" />
	<br />
	
</form:form>