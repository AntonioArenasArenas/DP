<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<display:table name="finder" id="row"
		requestURI="finder/tasks/list.do"
		pagesize="5"
		class="displaytag" >
		
		<display:column property="ticker" titleKey="task.ticker" sortable="true" />
		<display:column property="category" titleKey="task.category" sortable="true" />
		<display:column property="warranty" titleKey="task.warranty" sortable="true" />
		<display:column property="maxPrice" titleKey="task.maxPrice" sortable="true" />
		<display:column property="startDate" titleKey="task.startDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
		<display:column property="endDate" titleKey="task.endDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
		<display:column property="address" titleKey="task.address" sortable="true" />
		
	</display:table>
	
	<security:authorize access="hasRole('ADMIN')">
	<form:form action="finder/edit.do"
		modelAttribute="finder">
		<form:label path="cache">
			<spring:message code="systemData.cache" />
		</form:label>
		<form:input path="systemData.cache"/>
		<form:errors cssClass="error" path="cache"/>
	</form:form>
	</security:authorize>
	
</html>