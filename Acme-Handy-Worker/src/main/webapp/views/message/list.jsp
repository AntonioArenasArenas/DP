<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->


<display:table
	pagesize="5" name="messages" id="row"
	requestURI="message/list.do" >
	
	<display:column >
		<strong>
			<a href="message/show.do?messageId=${row.id}" >
				<spring:message code="mensaje.open" />
				
			</a>
		</strong>
		 <br/>	
	</display:column>
	<display:column property="subject" titleKey="mensaje.subject" sortable="true" />
	<display:column property="moment" titleKey="mensaje.moment" sortable="true" />
	<display:column >
		<strong>
			<a href="message/delete.do?messageId=${row.id}" >
				<spring:message code="mensaje.delete" />
			</a>
		</strong>
		 <br/>	
	</display:column>
	
	
	
</display:table>