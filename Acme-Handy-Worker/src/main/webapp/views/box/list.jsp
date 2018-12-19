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
<p><spring:message code="box.list" /></p>

<display:table
	pagesize="5" name="boxes" id="row"
	requestURI="box/list.do" >
	
	
	<display:column >
		<strong>
			<a href="message/list.do?boxId=${row.id}" >
				<jstl:out value="${row.name}" />
			</a>
		</strong>
		 <br/>	
	</display:column>
	
	<display:column >
	<jstl:if test="${!rox.isDefault}">
			<a href="box/actor/edit.do?boxId=${row.id}" >
				<spring:message code="box.delete" />
			</a>
		</jstl:if>
	</display:column>
	
</display:table>

<input type="button" value="<spring:message code="box.newBox" />" name="button" onClick="javascript: relativeRedir('box/create.do');"/>