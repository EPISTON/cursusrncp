#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- SB : Appel de la taglib struts-bootstrap -->
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- B : Ajout du fichier CSS -->
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<!-- B: Appel du javascript bootstrap et de jquery qui est requis -->
<script type="text/javascript" src="../js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<title>Liste produits</title>
<!-- SB : la balise suivante permet de charger les resources nécessaire pour bootstrap -->
<sb:head/>
</head>
<body>
	<!-- B : page-header permet de créer une entête, utilisable à l'intérieur de n'importe quel bloc
		well => mise en valeur (background plus foncé) -->
	<div class="page-header well col-lg-offset-2 col-lg-8">
		<h2>Edition de produits</h2>
	</div>
	<div class="row">
		<!-- B :
			row => nouvelle ligne
			col => nouvelle colone 
				lg => "large", peut être remplacer par md (medium) xs (xsmall) 
					selon l'affichage souhaité sur les différentes taille d'écran
				offset => retrait, correspond au nombre de colones que l'ont souhaite ajouter en marge
				xx => nombre de colone, de 1 à 12 (chaque row contient douze colones)

			les row / row sont récursifs
			
			col-lg-offset-2 => ajouter 2 colones avant le bloc
			col-lg-8 => définir la taille du bloc à 8 colones
		 -->
		<div class="col-lg-offset-2 col-lg-8 well">
			<div class="page-header">
				<h3>Premier exemple</h3>
			</div>
			
			<!-- Les 3 balises suivantes permettent de créer un cadre pour chacune avec un récapitulatif de 
				toutes les erreurs mises en forme
				
				exemple : 
				- le nom ne peut être nul
				- le prix ne peut être négatif
				- le stock ne peut être négatif
				- ... -->
			<s:actionerror theme="bootstrap"/>
            <s:actionmessage theme="bootstrap"/>
            <s:fielderror theme="bootstrap"/>

			<!-- SB : Action / Method comme d'habitude
				theme permet d'infiquer de faire appel au plugin struts2-bootstrap
				cssClass nous permet de préciser une class en particulier 
				(dans le cas présent une classe de bootstrap.css -->
			<s:form action="save" method="post" 
				theme="bootstrap" 
				cssClass="form-horizontal">
				
				<!-- SB : Chaque field s'utilise comme d'habitude -->
				
				<!-- SB : Ajout d'un tooltip pour afficher une bulle d'aide lors du survol avec la souris
					nécessite bootstrap.js (et donc jquery) -->
				<s:hidden name="id" />
				<s:textfield name="nom" 
							label="Nom du produit" 
							tooltip="Le nom du produit (celui qui est sur la boite éventuellement)"
							helpText="Passez le curseur sur l'icone du label pour afficher le tooltip" />
				<s:textfield name="prix" label="Prix" />
				<s:textfield name="poids" label="Poids" />
				<s:select name="catId" list="categories" 
						listValue="libelle" 
						listKey="id" 
						label="Categorie"/>
				<s:textfield name="stock" label="Stock" />
				<!-- SB/B : Ajout d'une classe bootstrap directement sur le s:submit -->
				<div class="text-center">
					<s:submit value="sauvegarder" cssClass="btn btn-primary" />
				</div>
			</s:form>
			<hr>

			<!--
			*************************************************************
			**********************  AUTRE EXEMPLE  **********************
			*************************************************************
			-->
			<div class="page-header">
				<h3>Autre exemple</h3>
			</div>
			<!-- SB : ajout de class css 
					form-vertical => label puis en dessous field -->
			<s:form action="save" method="post"
					theme="bootstrap" 
					cssClass="well form-vertical">
				<s:hidden name="id" />
				<!-- SB : On ajoute un helptext qui s'affichera en dessous -->
				<s:textfield name="nom" 
							label="Nom du produit" 
							helpText="Le nom du produit (celui qui est sur la boite éventuellement)" />
				<!-- SB : On ajout une icone 
					inputPrepend => au début
					inputAppend => à la fin -->
				<s:textfield name="prix" label="Prix"
							inputPrepend="$" />
				<!-- SB/B : même chose que le précédent mais avec une icon inputAppendIcon inputPrependIcon
					consulter http://getbootstrap.com/components/#glyphicons pour les noms
					(attention, ne mettre que le nom et pas la classe complète, 
					exemple "glyphicon glyphicon-scale" devient juste "scale" -->
				<s:textfield name="poids" label="Poids"
							inputPrependIcon="scale" />
				<s:select name="catId" list="categories" 
						listValue="libelle" 
						listKey="id" 
						label="Categorie"/>
				<s:textfield name="stock" label="Stock" />
				<!-- SB/B : Ajout d'une classe bootstrap directement sur le s:submit -->
				<div class="text-center">
					<s:submit value="sauvegarder" cssClass="btn btn-primary" />
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>