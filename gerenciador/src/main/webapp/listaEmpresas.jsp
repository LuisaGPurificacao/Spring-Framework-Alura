<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, br.com.alura.gerenciador.model.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- JSTL - JavaStandard TagLib -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista empresas</title>
</head>
<body>

	<ul>
		<c:forEach items="${ lista }" var="empresa">
			<li>${ empresa.getNome() }- <fmt:formatDate
					value="${ empresa.getDataAbertura() }" pattern="dd/MM/yyyy" /></li>
		</c:forEach>
	</ul>

</body>
</html>