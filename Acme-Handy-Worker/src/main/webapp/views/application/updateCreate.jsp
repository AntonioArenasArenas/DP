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

<script type="text/javascript" src="scripts/jquery.js"></script>

<!-- Ver si falta algún import en la parte de script -->


<security:authorize access="hasRole('CUSTOMER')">

	<p>
		<spring:message code="applications.update.1" />
	</p>

</security:authorize>

<security:authorize access="hasRole('WORKER')">
	<p>
		<spring:message code="applications.create" />
	</p>
</security:authorize>

<!-- Actualizar -->

<form:form modelAttribute="application"
	action="<security:authorize access="hasRole('CUSTOMER')">application/customer/edit.do</security:authorize><security:authorize access="hasRole('WORKER')">application/worker/create.do</security:authorize>">
	<security:authorize access="hasRole('CUSTOMER')">
		<form:hidden path="id" />
		<form:hidden path="version" />
	</security:authorize>
	<form:hidden path="task" />
	<form:hidden path="worker" />


	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="moment">
			<spring:message code="applications.update.date" />
		</form:label>
		<input type="text" name="moment" readonly
			value="${application.moment}" />

	</security:authorize>

	<form:label path="offeredPrize">
		<spring:message code="applications.list.price" />
	</form:label>
	<input type="number" name="offeredPrize"
		<security:authorize access="hasRole('CUSTOMER')">readonly value="${application.offeredPrize}"</security:authorize> />

	<security:authorize access="hasRole('CUSTOMER')">
		<p>
			<spring:message code="applications.list.worker" />
			:
		</p>
		<p>${application.worker.name} ${application.worker.surname}</p>

		<p>
			<spring:message code="applications.update.comments" />

			<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

			<jstl:forEach var="comment" items="${comments}">

				<p>
					<jstl:out value="${comment}"></jstl:out>
				</p>
			</jstl:forEach>
		</p>
	</security:authorize> />

	<security:authorize access="hasRole('WORKER')">
		<form:label path="comments">
			<spring:message code="applications.update.comments" />
		</form:label>
		<form:textarea path="comments" />
	</security:authorize> />

	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="status">
			<spring:message code="applications.update.status" />
		</form:label>
		<form:select path="status">
			<form:options items="${estados}" />
			<form:option value="0" label="----"></form:option>
		</form:select>
	</security:authorize> />



	<input type="submit" name="save"
		value="<spring:message code="applications.update.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="task.cancel" />"
		<security:authorize access="hasRole('WORKER')">onclick="javascript: relativeRedir('task/worker/show.do?taskId=${id}');"</security:authorize>
		<security:authorize access="hasRole('CUSTOMER')">onclick="javascript: relativeRedir('application/customer/list.do');"</security:authorize> />

</form:form>


<script>
	$("select")
			.change(
					function() {

						var val = $("select").val();
						if (val === "ACCEPTED" || val === "REJECTED") {
							var newElem = '<form:label path="comments"> <spring:message code="applications.update.comments" /> </form:label><form:textarea path="comments" id="comentarios"/>';
							$(newElem).insertAfter($("select"));
						} else {
							$("#comentarios").remove();
						}

						if (val === "ACCEPTED") {
							var text = '<p id="texto">Por favor inserte una tarjeta de crédito</p';
							$(text).insertAfter($("#comentarios"));

							var holderName = '<form:label path="holderName"><spring:message code="applications.update.creditcard.name" /></form:label><form:input path="holderName" id="hName"/><form:errors cssClass="error" path="holderName" />';
							$(holderName).insertAfter($("#texto"));

							var brandName = '<form:label path="brandName"><spring:message code="applications.update.creditcard.brandname" /></form:label><form:select id="bName" path="brandName"><form:options items="${brandnames}" itemValue="id" /><form:option value="0" label="----" /></form:select>';
							$(brandName).insertAfter($("#hName"));

							var number = '<form:label path="number"><spring:message code="applications.update.creditcard.number" /></form:label><form:input path="number" id="num" /><form:errors cssClass="error" path="number" />';
							$(number).insertAfter($("#bName"));

							var cvv = '<form:label path="cvv">CVV</form:label><form:input path="cvv" id="cv"/><form:errors cssClass="error" path="cvv" />';
							$(cvv).insertAfter($("#num"));

							var expDate = '<form:label path="expirationDate"><spring:message code="applications.update.creditcard.expdate" /></form:label><form:input path="expirationDate" placeholder="MM/yy" format="MM/yy" id="expDat" /><form:errors cssClass="error" path="expirationDate" />';
							$(expDate).insertAfter($("#cv"));
						} else {
							$("#texto").remove();
							$("#hName").remove();
							$("#bName").remove();
							$("#num").remove();
							$("#cv").remove();
							$("#expDat").remove();
						}

					});
</script>

