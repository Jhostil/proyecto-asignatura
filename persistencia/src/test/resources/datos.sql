insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Calarcá");
insert into ciudad values (3, "Circasia");

insert into usuario values ("123", "juan@email.com", "Juan Gonzales", "5468414", 2);
insert into usuario values ("124", "maria@email.com", "Juanita Maria", "668454", 2);
insert into usuario values ("125", "perez@email.com", "Joan Perez", "685448", 3);

insert into administrador values ("122", "admin@email.com", "Tulio josé", "contraseña");
insert into administrador values ("121", "admin2@email.com", "Tulia joséfa", "Contrasena");
insert into administrador values ("120", "admin3@email.com", "Turio josh", "con_traseña");

insert into usuario_telefonos values ("123", "3114568975");
insert into usuario_telefonos values ("123", "3246985765");
insert into usuario_telefonos values ("125", "3147895654");

insert into producto values (1, "Teclado Mecanico RGB full modelo:54gba", 10, "21/10/03", "Teclado Mecanico RGB", 70000, 35, 1, "123");
insert into producto values (2, "El barato iphone 13 modelo 13x1kl", 0, "21/10/10", "Iphone 13 rojo", 30000000, 2000, 3, "125");
insert into producto values (3, "Lapicero Apple con tinta de oro real", 0.01, "21/10/03", "Lapicero Apple dorado", 65500000, 4, 2, "124");

insert into producto_ruta_imagen values (1, "C:\Users\usuario\Images\imagen1.png");
insert into producto_ruta_imagen values (2, "C:\Users\usuario\Images\imagen2.png");
insert into producto_ruta_imagen values (3, "C:\Users\usuario\Images\imagen3.png");

insert into categoria values (1, "Tecnología");
insert into categoria values (2, "Papelería");
insert into categoria values (3, "Herramientas");

insert into producto_categorias values (1,1);
insert into producto_categorias values (2,1);
insert into producto_categorias values (3,2);

insert into compra values (1, "2021/10/05 09:12:12", "PSE", "123");
insert into compra values (2, "2021/09/20 10:11:30", "MasterCard", "124");
insert into compra values (3, "2021/10/05 21:25:04", "Efecty", "125");

insert into detalle_compra values (1, 70000, 1, 1, 1);
insert into detalle_compra values (2, 40000000, 1, 2, 2);
insert into detalle_compra values (3, 65500000, 1, 3, 3);

insert into comentario values (1, 3, "2021/10/05 09:12:12", "Algunas luces no encienden", "", 1, "125");
insert into comentario values (2, 5, "2021/10/05 09:12:12", "No soy fanboy de Apple pero es lo mejor Dios creo", "", 2, "123");
insert into comentario values (3, 4, "2021/10/05 09:12:12", "Increible, ahora puedo firmar con oro liquido", "", 3, "124");
insert into comentario values (4, 1, "2021/10/10 09:12:12", "la tinta se empezó a oscurecer, muy mala","", 3, "124");

insert into usuario_productos_favoritos values ("123", 3);
insert into usuario_productos_favoritos values ("123", 2);
insert into usuario_productos_favoritos values ("124", 1);

insert into chat values (1, 1, "123");
insert into chat values (2, 2, "124");
insert into chat values (3, 3, "125");

insert into mensaje values (1, "Juan Gonzales", "2021/10/05 09:12:12", "Buenas tardes, ¿Cuanta garantía tiene?", 1);
insert into mensaje values (2, "Juanita Maria", "2021/10/05 09:12:12", "Ya recibí el producto, está en buenas condiciones", 2);
insert into mensaje values (3, "Joan Perez", "2021/10/05 09:12:12", "Buen día, ya realicé el pago, ¿Cuándo se despacha el producto?", 3);

insert into subasta values (1, "2021/10/20 09:12:12", 1);
insert into subasta values (2, "2021/10/21 09:12:12", 2);
insert into subasta values (3, "2021/10/22 09:12:12", 3);

insert into detalle_subasta values (1, "2021/10/04 09:12:12", 75000, 1,"123");
insert into detalle_subasta values (2, "2021/10/04 09:12:12", 30800000, 2,"124");
insert into detalle_subasta values (3, "2021/10/04 09:12:12", 66500000, 3,"125");