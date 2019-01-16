<%--
 * action-1.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Otros taglib utiles son tiles para textos en tiles y fmt para fechas -->

<h3>
	<jstl:if test="${language=='en'}">
		${parentCategory.name}
	</jstl:if>
	<jstl:if test="${language=='es'}">
		${parentCategory.nameEsp}
	</jstl:if>
</h3>


<display:table name="categories" id="row"
	requestURI="category/administrator/list.do" pagesize="5"
	class="displaytag">

	<jstl:if test="${language=='en'}">
		<display:column property="name" titleKey="administrator.categoryName" />
	</jstl:if>
	<jstl:if test="${language=='es'}">
		<display:column property="nameEsp"
			titleKey="administrator.categoryName" />
	</jstl:if>
	<display:column>
		<jstl:if test="${row.childrenCategories.size()!=0}">
			<a href="category/administrator/list.do?categoryId=${row.id}"> <spring:message
					code="administrator.showSubCategories" />
			</a>
		</jstl:if>
	</display:column>


	<display:column>
		<a href="category/administrator/show.do?categoryId=${row.id}"> <spring:message
				code="administrator.showCategory" />
		</a>
	</display:column>

	<display:column>
		<a href="category/administrator/edit.do?categoryId=${row.id}"> <spring:message
				code="administrator.editCategory" />
		</a>
	</display:column>

	<display:column>
		<a href="category/administrator/delete.do?categoryId=${row.id}"> <spring:message
				code="administrator.deleteCategory" />
		</a>
	</display:column>

</display:table>

<a href="category/administrator/create.do"> <spring:message
		code="administrator.newCategory" />
</a>


