<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>Customer List</h2>
<hr>
<table>
	<c:forEach var="cust" items="${customers}">
			<tr>
			<td>${cust.firstName} </td>
			<td>${cust.lastName} </td>
			<td>${cust.email} </td>
			</tr>
	
	</c:forEach>


</table>


</body>
</html>
