<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<c:if test="${ not empty empresa }">
		<h2>Empresa ${ empresa } cadastrada com sucesso!</h2>
	</c:if>
	<c:if test="${ empty empresa }">
		<h2>Nenhuma empresa cadastrada!</h2>
	</c:if>
</body>
</html>

<!-- Java Server Page - JSP -->