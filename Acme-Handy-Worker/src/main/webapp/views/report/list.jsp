<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->
<p><spring:message code="report.list" /></p>
 
  
  
 <display:table pagesize="5" class="displaytag" name="reports" requestURI="${requestURI}" id="row">
	
	<jstl:if test="${report.finalMode == true}">
	<security:authorize access="hasRole('CUSTOMER')">

		
		<display:column>
			<a href="note/customer/create.do?id=${row.id}">
				<spring:message	code="report.create.note" />
			</a>
	</display:column>
	<display:column>
			<a href="note/customer/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>
	</jstl:if>
	
	<jstl:if test="${report.finalMode == true}">
	<security:authorize access="hasRole('WORKER')">

		
		<display:column>
			<a href="note/worker/create.do?id=${row.id}">
				<spring:message	code="report.create.note" />
			</a>
	</display:column>
	<display:column>
			<a href="note/worker/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>
	</jstl:if>
	
	<jstl:if test="${report.finalMode == true}">
	<security:authorize access="hasRole('REFEREE')">

		<display:column>
			<a href="note/referee/create.do?id=${row.id}">
				<spring:message	code="report.create.note" />
			</a>
	</display:column>
	<display:column>
			<a href="note/referee/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>
	</jstl:if>
	
	<display:column property="description" titleKey="report.description" sortable="true" />
	<display:column property="moment" titleKey="report.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	
	
	
</display:table>

