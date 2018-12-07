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
	<spring:message code="applications.update.creditcard" />
</p>

<form:form modelAttribute="application"
	action="application/customer/editCreditCard.do">

	<form:label path="holderName">
		<spring:message code="applications.update.creditcard.name" />
	</form:label>
	<form:input path="holderName" />
	<form:errors cssClass="error" path="holderName" />

	<form:label path="brandName">
	<spring:message code="applications.update.creditcard.brandname" />
	</form:label>
	<form:select id="brandnames" path="brandName">
		<form:options items="${brandnames}" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>

	<form:label path="number">
		<spring:message code="applications.update.creditcard.number" />
	</form:label>
	<form:input path="number" />
	<form:errors cssClass="error" path="number" />

	<form:label path="cvv">
		CVV
	</form:label>
	<form:input path="cvv" />
	<form:errors cssClass="error" path="cvv" />

	<form:label path="expirationDate">
		<spring:message code="applications.update.creditcard.expdate" />
	</form:label>
	<form:input path="expirationDate" placeholder="MM/yy" format="MM/yy" />
	<form:errors cssClass="error" path="expirationDate" />

	<input type="submit" name="save"
		value="<spring:message code="applications.update.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="task.cancel" />"
		onclick="javascript: relativeRedir('application/customer/list.do');" />



</form:form>






