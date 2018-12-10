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
	${id}
</p>
<!-- Si la variable es true todos los campos como inputs, si no, algunos como messages en p y otros como inputs segun convenga -->


<!-- Ver atributo value y tema readonly y disable -->

<!-- Actualizar -->

<security:authorize access="hasRole('CUSTOMER')">

	<form:form modelAttribute="application"
		action="application/customer/edit.do">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="task" />

		<form:label path="moment">
			<spring:message code="applications.update.date" />
		</form:label>
		<input type="text" name="moment" readonly value="${moment}" />

		<form:label path="offeredPrize">
			<spring:message code="applications.list.price" />
		</form:label>
		<input type="text" name="offeredPrize" readonly
			value="${offeredPrize}" />


		<form:label path="worker">
			<spring:message code="applications.list.worker" />
		</form:label>
		<input type="text" name="worker" readonly
			value="${worker.name} ${worker.surname}}" />

		<p>
			<spring:message code="applications.update.comments" />

			<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

			<jstl:forEach var="comment" items="${comments}">

				<p>
					<jstl:out value="${comment}"></jstl:out>
				</p>
			</jstl:forEach>
		</p>


		<form:label path="status">
			<spring:message code="applications.update.status" />
		</form:label>
		<form:select path="status">
			<form:options items="${estados}" />
			<form:option value="0" label="----"></form:option>
		</form:select>


		<!-- Esto solo aparece si se acepta o rechaza -->

		<form:label path="comments">
			<spring:message code="applications.update.comments" />
		</form:label>
		<form:textarea path="comments" />




		<input type="submit" name="save"
			value="<spring:message code="applications.update.save" />" />

		<input type="button" name="cancel"
			value="<spring:message code="task.cancel" />"
			onclick="javascript: relativeRedir('application/customer/list.do');" />
	</form:form>




</security:authorize>



<!-- Mostrar -->

<security:authorize access="hasRole('WORKER')">

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

</security:authorize>



