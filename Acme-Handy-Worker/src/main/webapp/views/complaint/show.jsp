<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

 <b><spring:message code="complaint.ticker" />:</b>
<jstl:out value="${complaint.ticker}"/>
<br />

<b><spring:message code="complaint.moment" />:</b>
<jstl:out value="${complaint.moment}"/>
<br />

<b><spring:message code="complaint.description" />:</b>
<jstl:out value="${complaint.description}"/>
<br />

<b><spring:message code="complaint.attachments" />:</b>
<jstl:out value="${complaint.attachments}"/>
<br />

<%-- <b><spring:message code="complaint.referee" />:</b>
<jstl:out value="${complaint.referee}"/>
<br />

 <b><spring:message code="report.moment" />:</b>
<jstl:out value="${report.moment}"/>
<br />

<b><spring:message code="report.description" />:</b>
<jstl:out value="${report.description}"/>
<br />

<b><spring:message code="report.attachments" />:</b>
<jstl:out value="${report.attachments}"/>
<br />

<b><spring:message code="report.finalMode" />:</b>
<jstl:out value="${report.finalMode}"/>
<br />  --%>


