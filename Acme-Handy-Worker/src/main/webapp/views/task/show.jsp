<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<b><spring:message code="task.ticker" />:</b>
<jstl:out value="${task.ticker}"/>
<br />

<b><spring:message code="task.moment" />:</b>
<jstl:out value="${task.moment}"/>
<br />

<b><spring:message code="task.description" />:</b>
<jstl:out value="${task.description}"/>
<br />

<b><spring:message code="task.startDate" />:</b>
<jstl:out value="${task.startDate}"/>
<br />

<b><spring:message code="task.endDate" />:</b>
<jstl:out value="${task.endDate}"/>
<br />

<b><spring:message code="task.address" />:</b>
<jstl:out value="${task.address}"/>
<br />

<b><spring:message code="task.category" />:</b>
<jstl:out value="${task.category}"/>
<br />

<b><spring:message code="task.warranty" />:</b>
<jstl:out value="${task.warranty}"/>
<br />

<b><spring:message code="task.maxPrice" />:</b>
<jstl:out value="${task.maxPrice}"/>
<br />

<b><spring:message code="task.comments" />:</b>
<jstl:out value="${task.comments}"/>
<br />
