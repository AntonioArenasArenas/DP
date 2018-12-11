<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>    

	<form:form action="worker/finder/edit.do"
		modelAttribute="finder">
		
		<form:hidden path="finder.id" />
		<form:hidden path="finder.version" />
		<form:hidden path="finder.tasks" />
		
		<form:label path="keyWord">
			<spring:message code="finder.keyWord" />
		</form:label>
		<form:input path="finder.keyWord" />
		<form:errors cssClass="error" path="keyWord" />
		<br/>
		
		<form:label path="category">
			<spring:message code="finder.category.name" />
		</form:label>
		<form:input path="finder.category.name" />
		<form:errors cssClass="error" path="category" />
		<br/>
		
		<form:label path="warranty">
			<spring:message code="finder.warranty.title" />
		</form:label>
		<form:input path="finder.warranty.title" />
		<form:errors cssClass="error" path="warranty" />
		<br/>
		
		<form:label path="maxPrice">
			<spring:message code="finder.maxPrice" />
		</form:label>
		<form:input path="finder.maxPrice" />
		<form:errors cssClass="error" path="maxPrice" />
		<br/>
		
		<form:label path="minPrice">
			<spring:message code="finder.minPrice" />
		</form:label>
		<form:input path="finder.minPrice" />
		<form:errors cssClass="error" path="minPrice" />
		<br/>
		
		<form:label path="startDate">
			<spring:message code="finder.startDate" />
		</form:label>
		<form:input path="finder.startDate" />
		<form:errors cssClass="error" path="startDate" />
		<br/>
		
		<form:label path="endDate">
			<spring:message code="finder.endDate" />
		</form:label>
		<form:input path="finder.endDate" />
		<form:errors cssClass="error" path="endDate" />
		<br/>
		
		
		<input type="submit" name="save"
		value="<spring:message code="finder.save" />" />&nbsp;
		
	</form:form>

