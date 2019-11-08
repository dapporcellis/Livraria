
<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Edita Livro</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="LivroWS">
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>id</label>
                        <input readonly type="text" name="txtId" required class="form-control" placeholder="Autor" value="${obj.id}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Título</label>
                        <input type="text" name="txtTitulo" required class="form-control" placeholder="Título" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Genero</label>
                        <select class="form-control" name="txtGenero">
                            <c:forEach items="${generos}" var="obj">
                                <option value="${obj.id}">${obj.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Editora</label>
                        <select class="form-control" name="txtEditora">
                            <c:forEach items="${editoras}" var="obj">
                                <option value="${obj.id}">${obj.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Autores</label>
                        <div class="row">
                            <c:forEach items="${autores}" var="obj">
                            <div class="col-md-3">
                                <div class="custom-control custom-checkbox">
                                    <input value="${obj.id}" type="checkbox" class="custom-control-input" id="u${obj.id}" name="txtAutores">
                                    <label class="custom-control-label" for="u${obj.id}">${obj.nome}</label>
                                </div> 
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Páginas</label>
                        <input type="text" name="txtPaginas" required class="form-control" placeholder="`Páginas" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>ISBN</label>
                        <input type="text" name="txtIsbn" required class="form-control" placeholder="ISBN" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Idioma</label>
                        <input type="text" name="txtIdioma" required class="form-control" placeholder="Idioma" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Data de Lançamento</label>
                        <input type="date" name="txtData" required class="form-control" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Sinopse</label>
                        <textarea name="txtSinopse" rows="4" cols="80" class="form-control" placeholder="Sinopse"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group-file">
                        <label for="file">Foto 1</label>
                        <input type="file" name="txtFoto1" required class="form-control form-control-file">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group-file">
                        <label for="file">Foto 2</label>
                        <input type="file" name="txtFoto2" required class="form-control form-control-file">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group-file">
                        <label for="file">Foto 3</label>
                        <input type="file" name="txtFoto3" required class="form-control form-control-file">
                    </div>
                </div>
            </div>
            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button>
            <a class="btn btn-primary btn-round text-center" href="AutorWS?acao=list">
                <i class="tim-icons icon-bullet-list-67"></i> Listar
            </a>
        </form>
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
</div>
<%@include file="../rodape.jsp" %>