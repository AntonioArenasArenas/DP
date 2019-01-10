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

<!-- Mostrar -->
<fmt:formatDate value="${application.moment}" var="dateString"
	pattern="dd/MM/yyyy HH:mm" />

<p>
	<b><spring:message code="applications.update.date" /></b>
	${dateString}
</p>

<p>
	<b><spring:message code="applications.list.price" /></b>
	${application.offeredPrize} (${application.offeredPrize*VAT})
</p>
<security:authorize access="hasRole('CUSTOMER')">
	<p>
		<b><spring:message code="applications.list.worker" /></b>
		${application.worker.name} ${application.worker.surname}
	</p>
</security:authorize>

<p>
	<b><spring:message code="applications.update.status" /></b>
	<jstl:if test="${application.status=='ACCEPTED'}">
		<spring:message code="applications.accepted" />
	</jstl:if>
	<jstl:if test="${application.status=='REJECTED'}">
		<spring:message code="applications.rejected" />
	</jstl:if>
	<jstl:if test="${application.status=='PENDING'}">
		<spring:message code="applications.pending" />
	</jstl:if>
</p>

<p>
	<b><spring:message code="applications.update.comments" /></b>

	<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

	<jstl:forEach var="comment" items="${comentarios}">

		<p>
			<jstl:out value="${comment}"></jstl:out>
		</p>
	</jstl:forEach>
</p>



