<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form:form modelAttribute="category" action="${requestURI}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="childrenCategories" />


	<form:label path="name">
		<b><spring:message code="administrator.categoryNameEn" /></b>
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br>

	<form:label path="nameEsp">
		<b><spring:message code="administrator.categoryNameEsp" /></b>
	</form:label>
	<form:input path="nameEsp" />
	<form:errors cssClass="error" path="nameEsp" />
	<br>

	<form:label path="parent">
		<b><spring:message code="administrator.category.parent" /></b>
	</form:label>
	<form:select path="parent">
		<form:options items="${categories}" itemLabel="name" itemValue="id" />
	</form:select>
	<form:errors cssClass="error" path="parent" />
	<br>

	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('category/administrator/list.do?categoryId=<jstl:if test="${category.parent.id==null}">0</jstl:if><jstl:if test="${category.parent.id!=null}">${category.parent.id}</jstl:if>');" />
</form:form>

