<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    

<display:table name="tasks" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="ticker" titleKey="task.ticker" sortable="true" />
	<display:column property="moment" titleKey="task.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="maxPrice" titleKey="task.maxPrice" sortable="true" />
	<display:column property="startDate" titleKey="task.startDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<display:column property="endDate" titleKey="task.endDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="task/customer/show.do?id=${row.id}">
				<spring:message code="task.show" />
			</a>
		</display:column>
		<display:column>
			<a href="task/customer/edit.do?id=${row.id}">
				<spring:message code="task.edit" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('WORKER')">
		<display:column>
			<a href="task/worker/show.do?id=${row.id}">
				<spring:message code="task.show" />
			</a>
		</display:column>
		<display:column>
			<a href="customer/worker/show.do?id=${row.customer.id}">
				<spring:message code="task.viewCustomer" />
			</a>
		</display:column>
		<display:column>
			<jstl:if test="${!tasksAlreadyApplied.contains(row)}">
				<a href="application/worker/create.do?taskId=${row.id}">
					<spring:message code="task.createApplication" />
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${acceptedTasks.contains(row)}">
				<a href="phase/worker/list.do?taskId=${row.id}">
					<spring:message code="task.listPhases" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('CUSTOMER')">	
	<input type="button" name="create"
			value="<spring:message code="task.create" />"
			onclick="javascript: relativeRedir('task/customer/create.do');" />
</security:authorize>