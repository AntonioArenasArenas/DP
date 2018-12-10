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
	<spring:message code="applications.statistics" />
</p>

<p>
	<spring:message code="applications.statisticsPerTask" />
</p>

<p>
	<spring:message code="applications.maximum" />
	: ${maximumpt}
</p>
<p>
	<spring:message code="applications.minimum" />
	: ${minimumpt}
</p>
<p>
	<spring:message code="applications.average" />
	: ${averagept}
</p>
<p>
	<spring:message code="applications.stdev" />
	: ${stdevpt}
</p>


<p>
	<spring:message code="applications.priceOfferedStatistics" />
</p>

<p>
	<spring:message code="applications.maximum" />
	: ${maximumpo}
</p>
<p>
	<spring:message code="applications.minimum" />
	: ${minimumpo}
</p>
<p>
	<spring:message code="applications.average" />
	: ${averagepo}
</p>
<p>
	<spring:message code="applications.stdev" />
	: ${stdevpo}
</p>


<p>
	<spring:message code="applications.pendingexpired" />
	: ${ratio}
</p>


