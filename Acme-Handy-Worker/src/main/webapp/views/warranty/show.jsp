<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<b><spring:message code="warranty.title" />:</b>
<jstl:out value="${warranty.title}"/>
<br />

<b><spring:message code="warranty.terms" />:</b>
<jstl:out value="${warranty.terms}"/>
<br />

<b><spring:message code="warranty.laws" />:</b>
<jstl:out value="${warranty.laws}"/>
<br />

<b><spring:message code="warranty.draftMode" />:</b>
<jstl:out value="${warranty.draftMode}"/>
<br />