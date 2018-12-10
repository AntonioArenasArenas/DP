<%--
 * action-1.jsp
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

<!-- Otros taglib utiles son tiles para textos en tiles y fmt para fechas -->

<p>
	<spring:message code="application.list.1" />
	${ticker}
</p>

<!-- Ver el tema de los colores (hay una opcion con el background color)-->
<display:table name="applications" id="row"
	requestURI="application/customer/list.do?taskId=${taskId}"
	pagesize="${pagination}" class="displaytag">

	<display:column property="worker" titleKey="applications.list.worker" />
	<display:column property="offeredPrize"
		titleKey="applications.list.price" />
	<display:column titleKey="applications.list.edit">
		<a href="application/customer/edit.do?applicationId=${row.id}"> <spring:message
				code="application.list.update" />
		</a>
	</display:column>

</display:table>