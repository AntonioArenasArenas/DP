<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="task/customer/edit.do" modelAttribute="task">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="applications" />
	<form:hidden path="phases" />
	<form:hidden path="complaints" />

	<form:label path="description">
		<spring:message code="task.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="startDate" placeholder="DD/MM/YY HH:MM">
		<spring:message code="task.startDate" />:
	</form:label>
	<form:input path="startDate" />
	<form:errors cssClass="error" path="startDate" />
	<br />

	<form:label path="endDate" placeholder="DD/MM/YY HH:MM">
		<spring:message code="task.endDate" />:
	</form:label>
	<form:input path="endDate" />
	<form:errors cssClass="error" path="endDate" />
	<br />
	
	<form:label path="address">
		<spring:message code="task.address" />:
	</form:label>
	<form:textarea path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="category">
		<spring:message code="task.category" />:
	</form:label>
	<form:select id="categories" path="category">
		<form:options items="${categories}" itemLabel="name" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	<form:label path="warranty">
		<spring:message code="task.warranty" />:
	</form:label>
	<form:select id="warranties" path="warranty">
		<form:options items="${warranties}" itemLabel="title" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	<form:label path="maxPrice">
		<spring:message code="task.maxPrice" />:
	</form:label>
	<form:input path="maxPrice" />
	<form:errors cssClass="error" path="maxPrice" />
	<br />
	
	<form:label path="comments">
		<spring:message code="task.comments" />:
	</form:label>
	<form:textarea path="comments" />
	<form:errors cssClass="error" path="comments" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="task.save" />" />&nbsp; 
	<jstl:if test="${task.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="task.delete" />"
			onclick="return confirm('<spring:message code="task.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="task.cancel" />"
		onclick="javascript: relativeRedir('task/list.do');" />
	<br />
	
</form:form>