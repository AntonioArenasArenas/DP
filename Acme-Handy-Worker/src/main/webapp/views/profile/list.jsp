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
	pagesize="5" name="profiles" id="row"
	requestURI="profile/list.do" >
	
	<display:column >
		<strong>
			<a href="profile/edit.do?profileId=${row.id}" >
				<jstl:out value="<spring:message code="profile.nick" />" />
			</a>
		</strong>
		 <br/>	
	</display:column>
	<display:column >
				<jstl:out value="${row.socialNetwork}" />
		 <br/>	
	</display:column>
	<display:column >
				<jstl:out value="${row.link}" />
		 <br/>	
	</display:column>
	<display:column >
		<strong>
			<a href="profile/delete.do?porfileId=${row.id}" >
				<jstl:out value="<spring:message code="profile.delete" />" />
			</a>
		</strong>
		 <br/>	
	</display:column>
	
</display:table>

<input type="button" value="<spring:message code="profile.newProfile" />" name="button" onClick="javascript: relativeRedir('profile/create.do');"/>