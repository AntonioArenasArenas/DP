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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Otros taglib utiles son tiles para textos en tiles y fmt para fechas -->

<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate var="date" value="${date}" pattern="dd/MM/yyyy" />

<display:table name="applications" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="task.ticker"
		titleKey="applications.list.worker.1" />

	<!-- Columnas worker -->

	<display:column property="task.endDate"
		titleKey="applications.list.worker.2" format="{0,date,dd/MM/yyyy}" />

	<!-- Columna customer-->
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column property="worker.name"
			titleKey="applications.list.worker" />
	</security:authorize>

	<display:column property="offeredPrize"
		titleKey="applications.list.price" />

	<display:column titleKey="applications.update.status">
		<jstl:if test="${row.status=='ACCEPTED'}">
			<spring:message code="applications.accepted" />
		</jstl:if>
		<jstl:if test="${row.status=='REJECTED'}">
			<spring:message code="applications.rejected" />
		</jstl:if>
		<jstl:if test="${row.status=='PENDING'}">
			<spring:message code="applications.pending" />
		</jstl:if>
	</display:column>

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
		<jstl:if test="${row.status=='PENDING'}">
			<a href="application/customer/edit.do?applicationId=${row.id}"> <spring:message
					code="applications.list.update" />
			</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	<display:column>
		<a
			href="application/<security:authorize access="hasRole('WORKER')">worker</security:authorize><security:authorize access="hasRole('CUSTOMER')">customer</security:authorize>/show.do?applicationId=${row.id}">
			<spring:message code="applications.list.worker.3" />
		</a>
	</display:column>

</display:table>

<script>
	$(function() {
		$("table td:contains(ACCEPTED)").parents("tr").css("background-color",
				"palegreen");

		$("table td:contains(ACEPTADA)").parents("tr").css("background-color",
				"palegreen");

		$("table td:contains(REJECTED)").parents("tr").css("background-color",
				"orange");

		$("table td:contains(RECHAZADA)").parents("tr").css("background-color",
				"orange");

		var dateCell = $(this).find("td:contains(PENDING)").parents("tr").find(
				"td:eq(1)").html();
		var date = new Date(dateCell);
		var actual = new Date();

		if (date < actual) {
			$("table td:contains(PENDING)").parents("tr").css(
					"background-color", "gainsboro");
		}

		dateCell = $(this).find("td:contains(PENDIENTE)").parents("tr").find(
				"td:eq(1)").html();
		date = new Date(dateCell);
		actual = new Date();

		if (date < actual) {
			$("table td:contains(PENDIENTE)").parents("tr").css(
					"background-color", "gainsboro");
		}

	});
</script>
