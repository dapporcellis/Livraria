<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file='cabecalho.jsp' %>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <div class="brands_products"><!--brands_products-->
                        <h2>Genero</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${generos}" var="genero">
                                    <li>
                                        <a href="PublicWS?txtAcao=listGenero&id=${genero.id}"><span class="pull-right">${genero.livros.size()}</span>${genero.nome}</a>
                                    </li> 
                                </c:forEach>                                                                       
                            </ul>
                        </div>
                    </div><!--/brands_products-->
                    <br><br>
                    <div class="brands_products"><!--brands_products-->
                        <h2>Autor</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${autores}" var="autor">
                                    <li>
                                        <a href="PublicWS?txtAcao=listAutor&id=${autor.id}"> <span class="pull-right">${autor.livros.size()}</span>${autor.nome}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div><!--/brands_products-->
                    <br><br>
                    <div class="brands_products"><!--brands_products-->
                        <h2>Editora</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${editoras}" var="editora">
                                    <li>
                                        <a href="PublicWS?txtAcao=listEditora&id=${editora.id}"> <span class="pull-right">${editora.livros.size()}</span>${editora.nome}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div><!--/brands_products-->
                    <br><br>
                    <!--propaganda
                    <div class="shipping text-center">
                        <img src="images/home/shipping.jpg" alt="" />
                    </div>
                    -->
                </div>
            </div>

            <div class="col-sm-9 padding-right">
                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Livros</h2>
                    <c:forEach items="${livros}" var="livro">
                        <div class="col-sm-4">
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <div style="height: 200px;">
                                            <img style="max-height: 100%;width: auto;" src="../arquivos/${livro.foto1}" alt="" />
                                        </div>
                                        <h2>${livro.nome}</h2>
                                        <p>
                                            <c:forEach items="${livro.autores}" var="autor">
                                                ${autor.nome},
                                            </c:forEach>
                                        </p>
                                        <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-"></i>Mais Info</a>
                                        <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Carrinho</a>
                                    </div>
                                    <div class="product-overlay">
                                        <div class="overlay-content">
                                            <h2>${livro.nome}</h2>
                                            <p>
                                                <c:forEach items="${livro.autores}" var="autor">
                                                    ${autor.nome},
                                                </c:forEach>
                                            </p>
                                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Mais Info</a>
                                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Carrinho</a>
                                        </div>
                                    </div>
                                </div>                                    
                            </div>
                        </div>
                    </c:forEach>
                </div><!--features_items--> 
            </div>
        </div>
    </div>
</section>
<%@include file='rodape.jsp' %>