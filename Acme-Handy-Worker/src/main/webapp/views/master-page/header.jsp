<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">		<!-- Actualizar aqu� el men�, preguntar a patricia por el enlace caido, NO OLVIDAR CAMBIAR MESSAGES Y TILES EN CONSECUENCIA -->
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="warranty/administrator/list.do"><spring:message code="master.page.warranties" /></a></li>
					<li><a href="administrator/statistics.do"><spring:message code="master.page.administrator.statistics" /></a></li>
					<li><a href="systemData/administrator/edit.do"><spring:message code="master.page.systemData.administrator" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="customer/edit.do"><spring:message code="master.page.personal.data" /></a></li>
					<li><a href="task/customer/list.do"><spring:message code="master.page.tasks" /></a></li>
					<li><a href="application/customer/list.do"><spring:message code="master.page.applications" /></a></li>

				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('WORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.worker" /></a>
				<ul>
					<li class="arrow"></li>

					<li><a href="worker/edit.do"><spring:message code="master.page.personal.data" /></a></li>
					<li><a href="task/worker/list.do"><spring:message code="master.page.tasks" /></a></li>
					<li><a href="application/worker/list.do"><spring:message code="master.page.applications" /></a></li>
					<li><a href="finder/worker/edit.do"><spring:message code="master.page.finder" /></a></li>

				</ul>
			</li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a>
			<li>
				<a class="fNiv">
					<spring:message code="master.page.register" />
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/create.do"><spring:message code="master.page.register.customer" /></a></li>
					<li><a href="worker/create.do"><spring:message code="master.page.register.worker" /></a></li>
				</ul>
			</li>

		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv">
				<spring:message code="master.page.mail" />
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="box/list.do"><spring:message code="master.page.box.list" /></a></li>
					<li><a href="box/create.do"><spring:message code="master.page.box.create" /></a></li>
					<li><a href="message/create.do"><spring:message code="master.page.message.create" /></a></li>

				</ul>
			</li>
			<li>
				<a class="fNiv">
					<spring:message code="master.page.profile" />
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/list.do"><spring:message code="master.page.profiles" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>
