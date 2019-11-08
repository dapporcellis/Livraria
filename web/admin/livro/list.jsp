
<%@include file="../cabecalho.jsp" %>

<div class="card ">
    <div class="card-header">
        <h4 class="card-title">Livro</h4>
    </div>
    <div class="card-body">
        <a class="btn btn-primary btn-round text-center" href="add.jsp">
            <i class="tim-icons icon-simple-add"></i> Adiciona
        </a>
        <div class="table-responsive">
            <table class="table tablesorter " id="">
                <thead class=" text-primary">
                    <tr>
                        <th>
                            ID
                        </th>
                        <th>
                            Nome
                        </th>
                        <th>
                            Lançamento
                        </th>
                        <th>
                            Fotos
                        </th>
                        <th>
                            Genero
                        </th>
                        <th>
                            Editora
                        </th>
                        <th>
                            Autores
                        </th>
                        <th>
                            Edita
                        </th>
                        <th>
                            Deleta
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="obj">
                    <tr>
                        <td>${obj.id}</td>
                        <td>${obj.nome}</td>
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${obj.lancamento}"></fmt:formatDate></td>
                        <td>
                            <img src="../../arquivos/${obj.foto1}" style="height: 40px;">
                            <img src="../../arquivos/${obj.foto2}" style="height: 40px;">
                            <img src="../../arquivos/${obj.foto3}" style="height: 40px;">
                        </td>
                        <td>${obj.genero.nome}</td>
                        <td>${obj.editora.nome}</td>
                        <td>
                            <c:forEach items="${obj.autores}" var="autor">
                                ${autor.nome}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <a class="btn btn-info btn-fab btn-icon btn-round" href="LivroWS?txtAcao=edit&txtId=${obj.id}">
                                <i class="tim-icons icon-pencil"></i>
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary btn-fab btn-icon btn-round" href="LivroWS?txtAcao=del&txtId=${obj.id}">
                                <i class="tim-icons icon-trash-simple"></i>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card-footer">
        <c:if test="${msg != null}">
        <div class="alert alert-primary alert-dismissible fade show" role="alert">
            ${msg}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
            </button>
        </div>
        </c:if>
    </div>
</div>

<%@include file="../rodape.jsp" %>