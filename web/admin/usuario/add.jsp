<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adiciona Usu�rio</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="UsuarioWS">

            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control" placeholder="Nome" name="txtNome">
                    </div>
                </div>                    
            </div>
            <div class="row">
                <div class="col-md-6 pr-md-1">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" placeholder="Email" name="txtEmail">
                    </div>
                </div>
                <div class="col-md-6 pr-md-1">
                    <div class="form-group">
                        <label>Senha</label>
                        <input type="password" class="form-control" name="txtSenha">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group-file">
                        <label>Foto</label>
                        <input type="file" class="form-control form-control-file" name="txtFoto">
                    </div>
                </div>
            </div>


            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button>
            <a class="btn btn-primary btn-round text-center" href="UsuarioWS?acao=list">
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
