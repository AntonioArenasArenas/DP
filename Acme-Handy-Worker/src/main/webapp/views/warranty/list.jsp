<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    

<display:table name="warranties" id="row" requestURI="warranty/list.do" pagesize="5" class="displaytag" >
	
	<display:column property="title" titleKey="warranty.title" sortable="true" />
	<display:column property="draftMode" titleKey="warranty.draftMode" sortable="true" />
	<display:column>
		<a href="warranty/customer/edit.do?id=${row.id}">
			<spring:message code="warranty.edit" />
		</a>
	</display:column>
	
</display:table>
	

</body>
</html>