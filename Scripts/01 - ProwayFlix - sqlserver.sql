 
create database prowayflix;

use prowayflix;
 


--drop table Perfil 
CREATE TABLE Perfil (
    IdPerfil int IDENTITY(1,1) NOT NULL,
    Nome varchar(50) NULL, 
    PRIMARY KEY (IdPerfil) 
);


  
--drop table Usuario
CREATE TABLE Usuario (
    IdUsuario int IDENTITY(1,1) NOT NULL,
    PerfilId int,
    Nome varchar(50),
    Email varchar(50),
    Logon varchar(50),
    Senha varchar(50),
    PRIMARY KEY (IdUsuario),
    FOREIGN KEY (PerfilId) REFERENCES Perfil(IdPerfil)
);
  

---------------------------------------

 
--drop table Categoria
CREATE TABLE Categoria (
    IdCategoria int IDENTITY(1,1) NOT NULL,
    Nome varchar(50) NULL, 
    PRIMARY KEY (IdCategoria) 
);

--drop table Filme
CREATE TABLE Filme (
    IdFilme int IDENTITY(1,1) NOT NULL,
    CategoriaId int,
    Nome varchar(50),
    Ano int,
    Sinopse varchar(2000), 
    PRIMARY KEY (IdFilme),
    FOREIGN KEY (CategoriaId) REFERENCES Categoria(IdCategoria)
);
   

--drop table Serie
CREATE TABLE Serie (
    IdSerie int IDENTITY(1,1) NOT NULL,
    CategoriaId int,
    Nome varchar(50),
    Sinopse varchar(2000), 
    Ano int,
    PRIMARY KEY (IdSerie),
    FOREIGN KEY (CategoriaId) REFERENCES Categoria(IdCategoria)
);
   
--drop table Temporada
CREATE TABLE Temporada (
    IdTemporada int IDENTITY(1,1) NOT NULL,
    SerieId int, 
    Sequencial int, 
    PRIMARY KEY (IdTemporada),
    FOREIGN KEY (SerieId) REFERENCES Serie(IdSerie)
);

--drop table Episodio 
CREATE TABLE Episodio (
    IdEpisodio int IDENTITY(1,1) NOT NULL,
    TemporadaId int, 
    Sequencial int,  
	Nome varchar(50),
    Sinopse varchar(2000),
    PRIMARY KEY (IdEpisodio),
    FOREIGN KEY (TemporadaId) REFERENCES Temporada(IdTemporada)
);
 

---------------------------------------

--drop table Assistido
CREATE TABLE Assistido (
    IdAssistido int IDENTITY(1,1) NOT NULL,
    UsuarioId int, 
    FilmeId int, 
    SerieId int,  
	Em date, 
    PRIMARY KEY (IdAssistido),
    FOREIGN KEY (UsuarioId) REFERENCES Usuario(IdUsuario),
    FOREIGN KEY (FilmeId) REFERENCES Filme(IdFilme),
    FOREIGN KEY (SerieId) REFERENCES Serie(IdSerie)
);

 
 
 


