<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    

<display:table name="phases" id="row" requestURI="phase/list.do" pagesize="5" class="displaytag" >
	
	<display:column property="title" titleKey="phase.title" sortable="true" />
	<display:column property="description" titleKey="phase.description" sortable="true" />
	<display:column property="startDate" titleKey="phase.startDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<display:column property="endDate" titleKey="phase.endDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<display:column>
		<a href="phase/worker/show.do?id=${row.id}">
			<spring:message code="phase.show" />
		</a>
	</display:column>
	<display:column>
		<a href="phase/worker/edit.do?id=${row.id}&taskId=${param['taskId']}">
			<spring:message code="phase.edit" />
		</a>
	</display:column>
	
</display:table>

<jstl:if test="${showCreateButton}">
	<br />
	<input type="button" name="create"
			value="<spring:message code="phase.create" />"
			onclick="javascript: relativeRedir('phase/worker/create.do?taskId=${param['taskId']}');" />
</jstl:if>