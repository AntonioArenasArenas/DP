<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<b><spring:message code="phase.title" />:</b>
<jstl:out value="${phase.title}"/>
<br />

<b><spring:message code="phase.description" />:</b>
<jstl:out value="${phase.description}"/>
<br />

<b><spring:message code="phase.startDate" />:</b>
<jstl:out value="${phase.startDate}"/>
<br />

<b><spring:message code="phase.endDate" />:</b>
<jstl:out value="${phase.endDate}"/>
<br />