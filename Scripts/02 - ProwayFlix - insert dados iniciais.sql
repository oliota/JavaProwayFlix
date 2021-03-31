
INSERT INTO Perfil (nome) VALUES ('Administrador'); 
INSERT INTO Perfil (nome) VALUES ('Usuário'); 


select * from Perfil;

------------------------------------------

INSERT INTO Usuario  
   (nome, email, Logon ,senha,perfilid)  
VALUES  
   (
	   'Rubem Oliota',
	   'rubemoliota@gmail.com',
	   'admin',
	   '123',
	   ( select IdPerfil from Perfil where nome = 'Administrador')
   ); 

INSERT INTO Usuario  
   (nome, email, Logon ,senha,perfilid)  
VALUES  
   (
	   'Fulano',
	   'fulano@gmail.com',
	   'fulano',
	   '123',
	   ( select IdPerfil from Perfil where nome = 'Usuário')
   ); 
    
select * from Usuario;

select u.IdUsuario,u.nome,u.email,u.Logon,u.senha,f.nome as perfil
from Usuario u
inner join Perfil f on f.IdPerfil= u.perfilid;



------------------------------------------




INSERT INTO Categoria (nome) VALUES ('Ação'); 
INSERT INTO Categoria (nome) VALUES ('Comédia'); 
INSERT INTO Categoria (nome) VALUES ('Terror'); 
INSERT INTO Categoria (nome) VALUES ('Drama'); 
INSERT INTO Categoria (nome) VALUES ('Ficção'); 
INSERT INTO Categoria (nome) VALUES ('Aventura'); 
INSERT INTO Categoria (nome) VALUES ('Fantasia'); 
INSERT INTO Categoria (nome) VALUES ('Anime'); 

select * from Categoria;