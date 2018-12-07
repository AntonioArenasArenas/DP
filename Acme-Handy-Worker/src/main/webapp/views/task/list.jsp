<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    

<display:table name="tasks" id="row" requestURI="task/list.do" pagesize="5" class="displaytag" >
	
	<display:column property="ticker" titleKey="task.ticker" sortable="true" />
	<display:column property="moment" titleKey="task.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="maxPrice" titleKey="task.maxPrice" sortable="true" />
	<display:column property="startDate" titleKey="task.startDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<display:column property="endDate" titleKey="task.endDate" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="task/customer/edit.do?taskId=${row.id}">
				<spring:message code="task.edit" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('WORKER')">
		<display:column>
			<a href="customer/profile.do?id=${row.id}"> <!--  TODO: No sé cómo llamar a un método de un servicio aquí -->
				<spring:message code="task.edit" />
			</a>
		</display:column>
	</security:authorize>
	
</display:table>
	

</body>
</html>