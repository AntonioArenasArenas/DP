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



<p>
	<spring:message code="applications.update.1" />
</p>

<!-- Mostrar -->


<p>
	<spring:message code="applications.update.date" />
	${application.moment}
</p>

<p>
	<spring:message code="applications.list.price" />
	${application.offeredPrize}
</p>

<p>
	<spring:message code="applications.list.worker" />
	${application.worker.name} ${application.worker.surname}
</p>

<p>
	<spring:message code="applications.update.status" />
	${status}
</p>

<p>
	<spring:message code="applications.update.comments" />

	<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

	<jstl:forEach var="comment" items="${comments}">

		<p>
			<jstl:out value="${comment}"></jstl:out>
		</p>
	</jstl:forEach>
</p>



