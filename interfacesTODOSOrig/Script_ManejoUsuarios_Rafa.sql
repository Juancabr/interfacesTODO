create database usuarios;
use usuarios;
create table usuario(
	nombre varchar(40) not null,
    email varchar(100) primary key,
    contrasena varchar(100) not null,
    banear boolean default false
);

insert into usuario values("miguel","mparamos@cenecmalaga.es","11234",false);

select * from usuario;

-- Para arreglar la zona horaria
set global time_zone='+1:00';

-- Crear otro usuario con con ciertos permisos
create user miguel identified by'12345';
grant select on usuarios.* to miguel;
-- grant all privileges on usuarios.* to miguel;

-- crear tabla compras(solo empresa)
create table compras(
	id int(5),
	usuario varchar(40),
    producto int(5),
    fecha timestamp,
	FOREIGN KEY (producto) REFERENCES productos(id),
    FOREIGN KEY (usuario) REFERENCES usuario(email)
);
-- crear ventana compra (solo si es uan empresa)