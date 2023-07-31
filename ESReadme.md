# Desafío para desarrolladores de back-end de Directa24

En este desafío, la API REST contiene información sobre una colección de películas lanzadas después del año 2010, dirigidas por directores aclamados.
Dado el valor de umbral, el objetivo es usar la API para obtener la lista de los nombres de los directores con la mayoría de las películas dirigidas. Específicamente, la lista de nombres de directores con un recuento de películas estrictamente superior al umbral dado.
La lista de nombres debe devolverse en orden alfabético.

Para acceder a la colección de usuarios, realice una solicitud HTTP GET a:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=<Número de página>
donde <pageNumber> es un número entero que indica la página de los resultados que se devolverán.

Por ejemplo, solicitud GET para:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=2
devolverá la segunda página de la colección de películas. Las páginas están numeradas desde 1, por lo que para acceder a la primera página, debe solicitar la página número 1.
La respuesta a dicha solicitud es un JSON con los siguientes 5 campos:

- página: La página actual de los resultados
- por_página: el número máximo de películas devueltas por página.
- total: el número total de películas en todas las páginas del resultado.
- total_pages: El número total de páginas con resultados.
- datos: una matriz de objetos que contienen películas devueltas en la página solicitada

Cada registro de película tiene el siguiente esquema:
- Título: título de la película
- Año: año en que se estrenó la película
- Calificación: calificación de la película
- Lanzamiento: fecha de lanzamiento de la película
- Tiempo de ejecución: tiempo de duración de la película en minutos
- Género: mover género
- Director: director de cine
- Escritor: escritores de cine
- Actores: actores de cine

## Función descriptiva

Completa la función

     List<String> getDirectors(umbral int)

getDirectors tiene el siguiente parámetro:
- umbral: número entero que indica el valor umbral para el número de películas que una persona ha dirigido

La función debe devolver una lista de cadenas que indiquen el nombre de los directores cuyo número de películas dirigidas es estrictamente mayor que el umbral dado.
El nombre de los directores en la lista debe ordenarse en orden alfabético.


#### Entrada de muestra para pruebas personalizadas
     4
#### Salida de muestra
     Martin Scorsese
     Woody Allen

El valor de umbral es 4, por lo que el resultado debe contener nombres de directores con más de 4 películas dirigidas.
Hay 2 de esos directores y nombres en orden alfabético enumerados en Salida de muestra.