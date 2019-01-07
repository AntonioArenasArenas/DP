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


<!-- Ver si falta algún import en la parte de script -->

<!-- Actualizar -->

<form:form modelAttribute="application" action="${requestURI}"
	id="formApplication">
	<security:authorize access="hasRole('CUSTOMER')">
		<form:hidden path="id" />
		<form:hidden path="version" />
	</security:authorize>
	<form:hidden path="task" />
	<form:hidden path="worker" />


	<security:authorize access="hasRole('CUSTOMER')">
		<fmt:formatDate value="${application.moment}" var="dateString"
			pattern="dd/MM/yyyy HH:mm" />
		<form:label path="moment">
			<b><spring:message code="applications.update.date" /></b>
		</form:label>
		<input type="text" name="moment" readonly value="${dateString}" />
		<br>
	</security:authorize>

	<form:label path="offeredPrize">
		<b><spring:message code="applications.list.price" /></b>
	</form:label>
	<input type="number" name="offeredPrize" step=".01"
		<security:authorize access="hasRole('CUSTOMER')">readonly value="${application.offeredPrize}"</security:authorize> />
	<form:errors cssClass="error" path="offeredPrize" />
	<br>

	<security:authorize access="hasRole('CUSTOMER')">

		<b><spring:message code="applications.list.worker" /></b>
			: ${application.worker.name} ${application.worker.surname}
			<br>

		<b> <spring:message code="applications.update.comments" />
		</b>
		<br>

		<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

		<jstl:forEach var="comment" items="${comentarios}">

			<jstl:out value="${comment}"></jstl:out>
			<br>
		</jstl:forEach>
	</security:authorize>

	<security:authorize access="hasRole('WORKER')">
		<form:hidden path="status" />
		<form:label path="comments">
			<b><spring:message code="applications.update.comments" /></b>
		</form:label>
		<form:textarea path="comments" />
		<br>
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="status">
			<b><spring:message code="applications.update.status" /></b>
		</form:label>
		<form:select path="status" id="estados">
			<form:options items="${estados}" />
		</form:select>
		<form:errors cssClass="error" path="status" />
		<br>

		<div id="commentsfield">
			<br>
			<form:label path="comments" id="comentarioslabel">
				<b><spring:message code="applications.update.comments" /></b>
			</form:label>
			<form:textarea path="comments" id="areaComentarios" />

		</div>

		<div id="creditCardFields">

			<p id="texto">
				<b>Por favor inserte una tarjeta de crédito</b>
			</p>

			<form:label path="creditCard.holderName">
				<spring:message code="applications.update.creditcard.name" />
			</form:label>
			<form:input path="creditCard.holderName" id="hName" />
			<form:errors cssClass="error" path="creditCard.holderName" />
			<br>

			<form:label path="creditCard.brandName">
				<spring:message code="applications.update.creditcard.brandname" />
			</form:label>
			<form:select id="bName" path="creditCard.brandName">
				<form:options items="${brandnames}" />
				<form:option value="0" label="----" />
			</form:select>
			<form:errors cssClass="error" path="creditCard.brandName" />
			<br>

			<form:label path="creditCard.number">
				<spring:message code="applications.update.creditcard.number" />
			</form:label>
			<form:input path="creditCard.number" id="num" />
			<form:errors cssClass="error" path="creditCard.number" />
			<br>

			<form:label path="creditCard.cvv">CVV</form:label>
			<form:input path="creditCard.cvv" id="cv" />
			<form:errors cssClass="error" path="creditCard.cvv" />
			<br>

			<form:label path="creditCard.expirationDate">
				<spring:message code="applications.update.creditcard.expdate" />
			</form:label>
			<form:input path="creditCard.expirationDate" placeholder="MM/yy"
				format="MM/yy" id="expDat" />
			<form:errors cssClass="error" path="creditCard.expirationDate" />


		</div>
	</security:authorize>
	<input type="submit" name="save"
		value="<spring:message code="applications.update.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="applications.update.cancel" />"
		<security:authorize access="hasRole('WORKER')">onclick="javascript: relativeRedir('task/worker/show.do?taskId=${id}');"</security:authorize>
		<security:authorize access="hasRole('CUSTOMER')">onclick="javascript: relativeRedir('application/customer/list.do');"</security:authorize> />

</form:form>

<script>
	var val = $("#estados").val();
	if (val === "PENDING") {
		$("#creditCardFields").hide();
		$("#commentsfield").hide();
	}

	$("#formApplication").submit(function() {
		var val = $("#estados").val();
		if (val === "REJECTED" || val === "PENDING") {
			$("#creditCardFields").remove();

		}

		if (val === "PENDING") {
			$("#commentsfield").remove();
		}
	});

	/*	Aquí se puede detectar el idioma pero no se puede traducir algo que no será estático en tiempo de ejecución porque si se cambia ya no valdrá
	
	window.onload = function() {

		var ln = x = window.navigator.language || navigator.browserLanguage;
		if (ln == 'es') {
			window.location.href = 'index_es.html'; // si es es va a español 
		}
	}; */

	$("#estados").change(function() {

		var val = $("#estados").val();

		if (val === "ACCEPTED" || val === "REJECTED") {
			document.getElementById("areaComentarios").value = "";
			$("#commentsfield").show("slow");

		} else {
			$("#commentsfield").hide("slow");
		}

		if (val == "ACCEPTED") {
			$("#creditCardFields").show("slow");

		} else {
			$("#creditCardFields").hide("slow");
		}
	});
</script>
