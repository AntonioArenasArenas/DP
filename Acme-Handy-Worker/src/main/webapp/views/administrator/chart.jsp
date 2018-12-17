<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="administrator.action.2" /></p>



<div style="width:30%!important"><canvas id="bar-chart" width="200" height="100"></canvas></div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<script>new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    responsive: true,
    maintainAspecRatio: false,
    data: {
      labels: ["All shouts", "Short shouts", "Long Shouts"],
      datasets: [
        {
          label: "Number",
          backgroundColor: ["#8e5ea2", "#8e5ea2","#8e5ea2"],
          data: [value="${statistics.get('count.all.shouts')}",value="${statistics.get('count.short.shouts')}",value="${statistics.get('count.long.shouts')}",0]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Indicators'
      }
    }
});</script>