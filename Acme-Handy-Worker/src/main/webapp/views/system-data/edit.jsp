<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%> 

<form:form action="systemData/edit.do"
		modelAttribute="systemData">
		
		<form:hidden path="systemData.banner" />
		
		<form:label path="name">
			<spring:message code="systemData.name" />
		</form:label>
		<form:input path="systemData.name" />
		<form:errors cssClass="error" path="name" />
		<br/>
		
		<form:label path="bannerHeader">
			<spring:message code="systemData.bannerHeader" />
		</form:label>
		<form:input path="systemData.bannerHeader" />
		<form:errors cssClass="error" path="bannerHeader" />
		<br/>
		
		<form:label path="welcomePageMsg">
			<spring:message code="systemData.welcomePageMsg" />
		</form:label>
		<form:textarea path="systemData.welcomePageMsg" />
		<form:errors cssClass="error" path="welcomePageMsg" />
		<br/>
		
		<form:label path="phoneCode">
			<spring:message code="systemData.phoneCode" />
		</form:label>
		<form:textarea path="systemData.phoneCode" />
		<form:errors cssClass="error" path="phoneCode" />
		<br/>
		
		<form:label path="spamWords">
			<spring:message code="systemData.spamWords" />
		</form:label>
		<form:textarea path="systemData.spamWords" />
		<form:errors cssClass="error" path="spamWords" />
		<br/>
		
		<form:label path="creditCards">
			<spring:message code="systemData.creditCards" />
		</form:label>
		<form:textarea path="systemData.spamWords" />
		<form:errors cssClass="error" path="spamWords" />
		<br/>
		
		<form:label path="vatPercentage">
			<spring:message code="systemData.vatPercentage" />
		</form:label>
		<form:input path="systemData.vatPercentage" />
		<form:errors cssClass="error" path="vatPercentage" />
		<br/>
		
		<form:label path="cache">
			<spring:message code="systemData.cache" />
		</form:label>
		<form:input path="systemData.cache" />
		<form:errors cssClass="error" path="cache" />
		<br/>
		
		<form:label path="maxPrice">
			<spring:message code="systemData.maxPrice" />
		</form:label>
		<form:input path="systemData.maxPrice" />
		<form:errors cssClass="error" path="maxPrice" />
		<br/>
		
		
		<input type="submit" name="save"
		value="<spring:message code="systemData.save" />" />&nbsp;
		<input type="submit" name="delete"
			value="<spring:message code="systemData.delete" />"
			onclick="return confirm('<spring:message code="systemData.confirm.delete" />')" />&nbsp;
		
	</form:form>
