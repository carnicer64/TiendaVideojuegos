# AA2 ACCESO A DATOS

### Requisitos obligatorios
- [x] Diseña la API y escribe el fichero OpenAPI 3.0 de la API. Incluye, al menos, los casos de éxito (20X), 400, 404 y los 500.
- [x] Diseña una API Virtual de forma que existan, al menos, 3 Casos de Uso para cada operación (uno de OK y otro para KO).
- [x] Prepara una colección Postman de prueba para la API diseñada y otra que permita probar todos los Casos de Uso de la API virtual
- [x] Diseña, al menos, 3 operaciones para que funcionen de forma reactiva con WebFlux (https://github.com/toskabnk/AmazonAAReactive/releases/tag/v1.0).
- [x] Ajusta el desarrollo de tu proyecto para que cumpla todas las decisiones de diseño adoptadas en los puntos anteriores

### Requisitos opcionales
- [ ] Si tu API está securizada, añade la información necesaria al fichero OpenAPI 3.0
- [ ] Añade alguna operación en la que se envien o reciban ficheros
- [x] Parametriza ambas colecciones Postman de forma que sea fácil cambiar el host, puerto o basePath de la API
- [x] Añade al fichero de especificación de la API (OpenAPI 3.0) un par de ejemplos para cada operación
- [x] Utiliza las herramientas Git y GitHub durante todo el desarrollo de la aplicación. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo.



# AA1 Acceso a datos

- Descarga el paquete desde GitHub
- Importa el proyecto a un IDE
- Descarga Postman e importa el archivo JSON de la carpeta postman.


## Funcionalidades obligatorias:
- [x] El modelo de datos estará compuesto de, al menos, 5 clases y tendrán que existir relaciones entre ellas. Cada clase tendrá, al menos, 6 atributos (String, int, float, boolean y algún tipo para almacenar fechas). Cada clase tendrá, al menos, 2 atributos obligatorios y algún otro con algún tipo de restricción de formato/validación.
- [x] Se tendrá que poder realizar, el menos, las operaciones CRUD sobre cada una de las clases. Se controlarán, al menos, los errores 400, 404 y 500
- [x] Añade opciones de filtrado para al menos una operación en cada clase en donde se puedan indicar hasta 3 campos diferentes (solo aplicable para operaciones GET)
- [x] Prepara una colección Postman que permita probar todas las operaciones desarrolladas
- [x] Configura en el proyecto la librería logback para que la aplicación web cuente con un log. Añade trazas en el código de forma que permita seguir el rastro de ejecución en el log (para todas las operaciones que se puedan realizar y también para los casos en los que se recojan errores)

## Otras funcionalidades:
- [ ] Añade una operación PATCH para cada una de las clases del modelo
- [x] Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación. Escribe el fichero README.md para explicar cómo poner en marcha el proyecto. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo
- [x] Añade 3 nuevos endpoints a la aplicación (sin repetir método) que realicen nuevas operaciones con los datos y que requieran el uso de DTOs y/o utilizar las relaciones entre las clases
- [ ] Securiza algunas de tus operaciones de la API con un token JWT
- [ ] Añade 3 operaciones que utilicen consultas JPQL para extraer la información de la base de datos
- [ ] Añade 3 operaciones que utilicen consultas SQL nativas para extraer la información de la base de datos
- [x] Añade 2 clases más al modelo de datos con sus respectivas operaciones CRUD (inclúyelas también en la colección Postman)
- [ ] Parametriza la colección Postman para que pueda ser ejecutada con el Runner de Postman y realizar una prueba completa de la API
