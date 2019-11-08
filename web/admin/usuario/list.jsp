
<%@include file="../cabecalho.jsp" %>

<div class="card ">
    <div class="card-header">
        <h4 class="card-title">Usuários</h4>
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
                            Email
                        </th>
                        <th>
                            Foto
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
                    <c:forEach items="${usuarios}" var="obj">
                        <tr>
                            <td>
                                ${obj.id}
                            </td>
                            <td>
                                ${obj.nome}
                            </td>
                            <td>
                                ${obj.email}
                            </td>
                            <td>
                                <img style="height: 60px;" src="../../arquivos/${obj.foto}">                          
                            </td>
                            <td>
                                <a href="UsuarioWS?txtAcao=edt&txtId=${obj.id}" rel="tooltip" class="btn btn-success btn-sm btn-icon">
                                    <i class="tim-icons icon-pencil"></i>
                                </a>
                            </td>
                            <td>
                                <a href="UsuarioWS?txtAcao=del&txtId=${obj.id}" rel="tooltip" class="btn btn-danger btn-sm btn-icon">
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
