<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=1">
<link href="/jaehwan/resources/css/style.css" type="text/css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header" />
	
	<div id="body">
		<tiles:insertAttribute name="aside" />
	 	<tiles:insertAttribute name="main" />
 	</div>
 	
	<tiles:insertAttribute name="footer" />
</body>
</html>