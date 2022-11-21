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

	<c:if test="${ not empty empresa }">
		<h2>Empresa ${ empresa } cadastrada com sucesso!</h2>
	</c:if>

	<ul>
		<c:forEach items="${ lista }" var="empresa">
			<li>${ empresa.nome }- <fmt:formatDate
					value="${ empresa.dataAbertura }" pattern="dd/MM/yyyy" /> <a
				href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}"
				style="color: blue; text-decoration: none">editar</a> <a
				href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}"
				style="color: red; text-decoration: none">remover</a>
			</li>
		</c:forEach>
	</ul>

</body>
</html>