<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<b><spring:message code="customer.name" />:</b>
<jstl:out value="${customer.name}"/>
<br />

<b><spring:message code="customer.middleName" />:</b>
<jstl:out value="${customer.middleName}"/>
<br />

<b><spring:message code="customer.surname" />:</b>
<jstl:out value="${customer.surname}"/>
<br />

<b><spring:message code="customer.photo" />:</b>
<jstl:out value="${customer.name}"/>
<br />

<b><spring:message code="customer.email" />:</b>
<jstl:out value="${customer.email}"/>
<br />

<b><spring:message code="customer.phoneNumber" />:</b>
<jstl:out value="${customer.phoneNumber}"/>
<br />

<b><spring:message code="customer.address" />:</b><br />
<jstl:out value="${customer.address}"/>
<br />