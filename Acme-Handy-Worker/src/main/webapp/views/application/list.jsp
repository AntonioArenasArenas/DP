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
<fmt:formatDate value="${date}" pattern="dd/MM/yyyy" />

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

	<display:column property="status" titleKey="applications.update.status" />

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="application/customer/edit.do?applicationId=${row.id}"> <spring:message
					code="applications.list.update" />
			</a>
		</display:column>
	</security:authorize>
	<display:column>
		<a
			href="application/<security:authorize access="hasRole('WORKER')">worker</security:authorize><security:authorize access="hasRole('CUSTOMER')">customer</security:authorize>/show.do?applicationId=${row.id}">
			<spring:message code="applications.list.worker.3" />
		</a>
	</display:column>

</display:table>

<security:authorize access="hasRole('WORKER')">
	<a href="application/worker/create.do"> <spring:message
			code="applications.list.worker.create" />
	</a>

</security:authorize>

<script>
	$(function() {
		$("table td:contains(ACCEPTED)").parents("tr").css("background-color",
				"palegreen");

		$("table td:contains(REJECTED)").parents("tr").css("background-color",
				"orange");

		var dateCell = $(this).find("td:contains(PENDING)").parents("tr").find(
				"td:eq(1)").html();
		var date = new Date(dateCell);
		var actual = new Date();

		if (date < actual) {
			$("table td:contains(PENDING)").parents("tr").css(
					"background-color", "gainsboro");
		}

	});
</script>
