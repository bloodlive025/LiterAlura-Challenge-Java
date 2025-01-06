<h1>LiterAlura-Challenge-Java</h1>
Desarrollo del proyecto LiterAlura del programa Backend de Alura Latam
<h2>Descripcion del proyecto</h2>
El siguiente proyecto es un programa escrito en java en la que se creara una tabla donde se almacenara los datos de libros. Adicionalmente se creara una tabla donde se guardara los datos de los autores.


Para el desarrollo de este proyecto se uso el framework sringboot para el desarrollo del backend, del IDE IntelliJ, y para guardar los informacion de los libros se uso la base de datos PostgreSQL.

Este programa se ejecutara directamente desde la consola de IntelliJ, donde se nos mostrara las siguientes opciones:

<h3>1. Buscar Libros</h3>
Al usar esta opcion, el programa pedira ingresar el titulo del libro que deseamos buscar. Para la busqueda se hara uso del API de gutendex, donde se encuentra los datos de todos los libros, y si la busqueda es correcta entonces los datos del libro buscado se guardaron en nuestra base de datos, ademas de guardarse los datos del autor de este libro en una tabla 
llamada autores.
<h3>2. Listar Libros</h3>
Al usar esta opcion se listara todos los libros que se encuentra en nuestra base de datos.
<h3>3. Listar Autores</h3>
Al usar esta opcion se listara todos los autores que se encuentran en la base de datos junto con los libros que tiene registrado cada autor.
