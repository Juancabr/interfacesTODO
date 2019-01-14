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
 create user miguel@localhost identified by'12345';
 grant select on usuarios.* to miguel;
-- grant all privileges on usuarios.* to miguel;

-- crear tabla producto
create table producto(
	idProducto int(5) primary key,
	nombre varchar(40),
    precio double
);

insert into producto values(1,'jamon',12);

-- crear tabla compras(solo empresa)
create table compras(
	id int(5),
	usuario varchar(40),
    producto int(5),
    fecha timestamp,
	FOREIGN KEY (producto) REFERENCES producto(idProducto),
    FOREIGN KEY (usuario) REFERENCES usuario(email)
);
-- crear ventana compra (solo si es uan empresa)

create table oferta(
	nombre varchar(45) primary key,
    idproducto int(5),
    oprecio int(5),
    oprecionew int(5),
    oinicio date,
    ofin date,
    foreign key (idproducto) references producto(idProducto)
);