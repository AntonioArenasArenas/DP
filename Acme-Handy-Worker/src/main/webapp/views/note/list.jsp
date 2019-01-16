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
<p><spring:message code="note.list" /></p>
 
  
  
 <display:table pagesize="5" class="displaytag" name="reports" requestURI="${requestURI}" id="row">
	
	
	<security:authorize access="hasRole('CUSTOMER')">

	<display:column>
			<a href="note/customer/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>

	<security:authorize access="hasRole('WORKER')">

	
	<display:column>
			<a href="note/worker/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>

	

	<security:authorize access="hasRole('REFEREE')">

	<display:column>
			<a href="note/referee/edit.do?id=${row.id}">
				<spring:message	code="report.edit.note" />
			</a>
	</display:column>
	</security:authorize>
	
	
	<display:column property="workerComment" titleKey="report.workerComment" sortable="true" />
	<display:column property="customerComment" titleKey="report.customerComment" sortable="true" />
	<display:column property="refereeComment" titleKey="report.refereeComment" sortable="true" />
	<display:column property="moment" titleKey="report.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	
	
	
</display:table>

