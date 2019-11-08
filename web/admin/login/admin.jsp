<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema administrativo</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginWS" method="POST">
            Login: <input type="email" required name="txtEmail"><br>
            Senha: <input type="password" required name="txtSenha"><br>
            <input type="submit">                        
        </form>
        
        <c:if test="${param['erro'] == 'logout'}">
            <h1>Você saiu do sistema!</h1>
        </c:if>
        <c:if test="${param['erro'] == 'senha'}">
            <h1 style="color:red;">Senha e/ou login incorretos!</h1>
        </c:if>
        <c:if test="${param['erro'] == 'permissao'}">
            <h1 style="color:red;">Você precisa estar logado para acessar essa página!</h1>
        </c:if>
        
    </body>
</html>
