# Guía de estilo y arquitectura

Documento normativo para contribuir en `POO`.

## Criterios de correccion

Cada criterio descuenta puntos. La letra marca la penalizacion:

- a = -0.2
- b = -0.4
- c = -0.6
- d = -0.8
- e = -1
- f = -1.5
- g = -2
- h = -2.5
- i = -3
- j = -5
- K = -10

Coste de incumplir: va en la cabecera de cada sección; solo se etiqueta la línea que se sale de ese coste común.

## Niveles de regla

* `DEBE`: obligatorio.
* `NO DEBE`: prohibido.
* `DEBERIA`: recomendado, salvo razón técnica justificada.
* `PUEDE`: opcional.

## Convenciones de nombres (b)

* Clases e interfaces: DEBE usar PascalCase (`User`, `RentService`, `FindCriteria`).
* Métodos y variables: DEBE usar camelCase (`totalPrice`, `findByMobile`).
* Constantes (`static final`): DEBE usar mayúsculas con guion bajo (`MAX_SIZE`, `DEFAULT_ROLE`).
* Paquetes: DEBE usar minúsculas, sin guiones y con jerarquías separadas por `.` (`clients.http`, `resources.dtos`, `infrastructure.data`).
* Enums: DEBE nombrar el tipo en PascalCase y sus valores en mayúsculas (`Role.ADMIN`, `Province.MADRID`).
* Booleanos: DEBE usar los prefijos `is`, `has` o `can` (`isActive`, `hasMobile`, `canEdit`).
* Los nombres DEBEN ser descriptivos y estar en inglés.
* DEBERIA evitar abreviaturas, salvo las habituales (`id`, `cif`, `url`, `dto`).
* NO DEBE usar notación húngara ni prefijos de tipo (`strName`, `iCount`).

## Límites de tamaño (b-c)

Son umbrales de revisión, no reglas absolutas. El criterio principal es la responsabilidad única y la legibilidad. (Rozar el umbral → `b`; pasarse de largo → `c`.)

* DEBERIA mantener un máximo de 3 parámetros por método. Si varios parámetros forman un mismo concepto, agruparlos en un objeto (`criteria`, `command`, `request`).
* DEBERIA mantener un máximo de 20 líneas por método.
* DEBERIA mantener una complejidad ciclomática máxima de 8 por método.
* DEBERIA mantener un máximo de 2 niveles de anidamiento.
* DEBERIA mantener un máximo de 20 métodos públicos por clase.
* DEBERIA mantener un máximo de 6 dependencias inyectadas por constructor.
* DEBERIA mantener un máximo de 250 líneas por clase.
* DEBERIA mantener un máximo de 120 caracteres por línea.
* DEBERIA mantener entre 2 y 20 clases por paquete.
* DEBERIA mantener un máximo de 8 atributos por clase.

Superar estos límites obliga a revisar el diseño, pero no implica necesariamente que sea incorrecto.

## Estilo de código (a)

* DEBE usar `this.` explícitamente en todos los accesos a atributos y métodos propios de la instancia.
* NO DEBE utilizar comentarios para explicar APIs no públicas, código interno o decisiones que puedan expresarse mediante el propio código.
* (b) El mensaje de la excepción DEBE indicar el valor que causó el error.
* (b) NO DEBE utilizarse comentarios en el código.

## logs (b)
* NO DEBE utilizarse `System.out.print` ni `System.out.println`, salvo dentro de `cli.view.View`.
* DEBIERA utilizarse `LogManager.getLogger().*` (debug, info, warn, error) dependiendo del tipo de mensaje.

## Entity (`models`)

Una entity es una clase de dominio con identidad propia y ciclo de vida.

* (e) DEBE ser mutable.
* (e) DEBE tener una identidad única (id única).
* (f) DEBE pertenecer al dominio puro.
* (g-h) NO DEBE conocer la CLI, los servicios de aplicación, ni los repositorios ni ninguno otra capa.

### Identidad (f)

* DEBE tener un atributo técnico `id` de tipo `Long`.
* El `id` DEBE ser único
* El `id` NO DEBE tener significado de negocio.
* NO DEBE usar una clave natural (`email`, `cif`, etc.) como identidad, porque puede cambiar.
* (d-e) El `id` NO DEBE recibirse como parámetro del constructor.
* (d-e) El `id` DEBE permanecer `null` mientras la entity es transitoria; la infraestructura lo asigna al persistirla.

### Constructores (c-d)

* PUEDE recibir todos los atributos de dominio necesarios para construir la entity de forma normalizada.
* NO DEBE incluir el `id`.
* PUEDE incluir atributos opcionales.
* Los atributos opcionales recibidos en el constructor PUEDEN ser `null`.
* DEBE reutilizar los setters o métodos de validación para evitar duplicar lógica.

### Atributos (c-d)

* Los atributos obligatorios DEBEN recibirse en el constructor.
* Los atributos opcionales PUEDEN recibirse en el constructor o asignarse después mediante setters.
* En un atributo opcional, `null` DEBE considerarse un estado válido que significa "no existe".
* Los atributos DEBEN usar wrappers (`Integer`, `Long`, `Boolean`), nunca tipos primitivos.
* (f) La entity DEBE aplicar las reglas de negocio que pueda garantizar por sí misma.
* (f) Las reglas que requieren consultar otros datos, como la unicidad de un email, DEBEN comprobarse en el servicio correspondiente.

### Validación (c-d)

* DEBE validar cada atributo en un único punto, en su setter.
* El constructor DEBE reutilizar esa validación.
* DEBE aplicar fail-fast y lanzar `IllegalArgumentException` ante valores inválidos.
* (f) DEBE validar los invariantes de dominio, como campos obligatorios, formatos y rangos.
* Los atributos opcionales DEBEN aceptar `null`; si contienen un valor, este DEBE validarse.
* NO DEBE añadir guardas defensivas que oculten errores de programación.
* (f) NO DEBE sustituir silenciosamente valores inválidos por valores por defecto.

### `equals` / `hashCode` (e)

* DEBE definir la igualdad exclusivamente mediante el `id`.
* NO DEBE usar atributos de negocio para determinar la igualdad.
* Dos entities distintas con `id == null` NO DEBEN considerarse iguales.
* DEBE implementar `equals` y `hashCode` conjuntamente y de forma coherente.
* La implementación PUEDE generarse con IntelliJ, Lombok o una herramienta equivalente, pero DEBE revisarse para comprobar que cumple estas reglas.

### Relaciones entre clases (k)

* Las clases DEBEN relacionarse con otras clases del dominio, no con sus `id`.

## Diagramas PlantUML (`docs`) (c-d)

Los diagramas PlantUML DEBEN representar el modelo de dominio de forma clara y coherente con el código.

* DEBE usarse PlantUML para documentar las relaciones entre modelos cuando se represente el dominio.
* DEBE indicarse siempre la multiplicidad en ambos extremos de cada relación (`"1"`, `"0..1"`, `"*"`, `"1..*"`, etc.).
* DEBE indicarse el tipo de relación PlantUML que corresponda:
    * Asociación: `-->`
    * Agregación: `o-->`
    * Composición: `*-->`
    * Herencia: `<|--`

* DEBE respetarse la dirección de la dependencia: la flecha DEBE apuntar hacia la clase de la que se depende.
* (k) Las clases del modelo DEBEN relacionarse con otras clases, no con sus `id`.
* NO DEBE representarse una relación de dominio mediante atributos como `userId`, `roleId`, `productId`, etc.
* En las clases del diagrama DEBEN aparecer solo los nombres de los atributos.
* NO DEBE incluirse visibilidad, tipos, anotaciones, restricciones ni detalles técnicos dentro de los atributos del modelo.

## Repository (`data.repositories`) (e-f)

Un repository es una abstracción de persistencia para entities del dominio.

* DEBE definirse una interfaz de repositorio en `data.repositories`.
* Las implementaciones concretas DEBEN estar en subpaquetes de infraestructura (`data.repositories.map`, `data.repositories.mysql`, etc.).
* Los modelos NO DEBEN depender de repositorios.
* Los repositorios DEBEN tener una relación de uso con los modelos.
* Los servicios DEBEN depender de interfaces de repositorio, no de implementaciones concretas.
* Las interfaces de repositorio NO DEBEN depender de detalles de infraestructura como `Map`, SQL.
* Las implementaciones de repositorio DEBEN implementar una interfaz de repositorio.

### Convenciones de repositorio (b-c)

* La interfaz genérica DEBE llamarse `GenericRepository<T>`.
* Un repositorio específico DEBE llamarse `{Entity}Repository` (`UserRepository`, `ConsentRepository`).
* Una implementación en memoria DEBE llamarse `{Entity}RepositoryMap`.
* Una implementación SQL DEBE llamarse `{Entity}RepositorySql`.
* Una implementación concreta DEBE extender la implementación genérica cuando exista (`GenericRepositoryMap<T>`, `GenericRepositorySql<T>`).
* NO DEBE filtrarse fuera del repositorio la estructura interna de persistencia (`Map`, tablas SQL, nombres de columnas, claves generadas).

### Asignación de identidad (f)

* La asignación de `id` DEBE ser un detalle interno del repositorio o de la infraestructura.
* NO DEBE exponerse en la interfaz pública del repositorio un método cuyo único propósito sea asignar el `id`.
* Las entities DEBEN mantener `setId` disponible solo para infraestructura; el código de aplicación NO DEBE usarlo para crear identidad manualmente.

### Funciones CRUD (e)
* `create` DEBE garantizar un id único, y debe ser generado en la implementación, no se realizan comprobaciones de reglas de negocio.
* `create` DEBE ignorar el valor de id que tenga la `entity`.

## Service (`services`) (e-f)

Un service resuelve funcionalidades de aplicacion coordinando modelos y repositorios.

* DEBE implementar casos de uso de la aplicacion.
* DEBE ser una clase sin estado propio de negocio.
* DEBE comprobar solo las reglas de negocio que el modelo no pueda garantizar por si mismo.
* DEBE usar modelos para recibir datos, devolver resultados o ejecutar comportamiento de dominio.
* DEBE asociarse con uno o varios repositorios mediante sus interfaces.
* NO DEBE depender de implementaciones concretas ni detalles de infraestructura como `Map`, SQL, `Connection`, ficheros, CLI o GUI.
* Las reglas que requieren consultar otros datos o coordinar repositorios, como comprobar que un email es unico, DEBEN situarse en el service.

## CLI (`cli`) (e-f)

La CLI es la capa de entrada y salida por consola. Su responsabilidad es interpretar comandos, recoger parámetros, invocar servicios y mostrar resultados.

* DEBE estar en el paquete `app.cli`.
* Los comandos concretos DEBEN estar en `app.cli.commands`.
* La salida por consola DEBE estar en `app.cli.view`.
* La CLI DEBE depender de servicios, no de repositorios.
* La CLI PUEDE usar modelos para construir entradas hacia los servicios o mostrar resultados.
* La CLI NO DEBE contener reglas de negocio.
* Cada comando DEBE llamar a un solo servicio.
* La CLI NO DEBE modificar el estado de los modelos salvo para construirlos con los datos introducidos por el usuario.
* La CLI DEBE delegar la gestión de errores de ejecución en un `ErrorHandler`.
### Command (`cli.Command`) (d-e)

Un `Command` representa una acción ejecutable desde consola.

* DEBE declarar su nombre mediante `name()`.
* DEBE declarar sus parámetros obligatorios mediante `obligatoryParams()`.
* DEBE declarar sus parámetros opcionales mediante `optionalParams()`.
* Los parámetros opcionales DEBEN indicarse después de los obligatorios.
* Los parámetros opcionales DEBEN interpretarse por posición: si no se informa un opcional, tampoco DEBEN informarse los posteriores.
* DEBE declarar su mensaje de ayuda mediante `helpMessage()`.
* DEBE ejecutar la acción mediante `execute(String[] params)`.
* DEBE usar `View` para mostrar resultados.

### CommandLineInterface (`cli.CommandLineInterface`) (e)

`CommandLineInterface` coordina el ciclo de lectura y ejecución de comandos.

* DEBE registrar comandos por su `name()`.
* DEBE comprobar que el comando existe antes de ejecutarlo.
* DEBE validar el número de parámetros recibidos.
* DEBE aceptar como mínimo los parámetros obligatorios.
* DEBE aceptar como máximo los parámetros obligatorios más los opcionales.
* NO DEBE validar reglas de negocio.
* NO DEBE construir lógica específica de un comando concreto.
* DEBE delegar la ejecución en el `Command` correspondiente.

### View (`cli.view`) (d-e)

`View` centraliza la salida por consola.

* Toda escritura por consola DEBE hacerse desde `View`.
* `View` PUEDE usar `System.out.print` y `System.out.println` para escribir en consola.
* Las clases de comando NO DEBEN usar `System.out.print` ni `System.out.println` directamente.
* `View` DEBE limitarse a mostrar información; NO DEBE ejecutar lógica de aplicación.
