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

<h2>
	<spring:message code="administrator.fixUpTasksPerUserStatistics" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumpu}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumpu}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagepu}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevpu}
</p>

<h2>
	<spring:message code="administrator.statisticsPerTask" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumpt}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumpt}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagept}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevpt}
</p>


<h2>
	<spring:message code="administrator.priceOfferedStatistics" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumpo}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumpo}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagepo}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevpo}
</p>

	<h2><spring:message code="administrator.pendingexpired" /></h2>
		${ratio}



