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

## logs (b)
* NO DEBE utilizarse System.out.println.
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

* DEBE recibir únicamente los atributos de dominio obligatorios.
* NO DEBE incluir el `id`.
* NO DEBE incluir atributos opcionales.
* DEBE reutilizar los setters o métodos de validación para evitar duplicar lógica.

### Atributos (c-d)

* Los atributos obligatorios DEBEN recibirse en el constructor.
* Los atributos opcionales DEBEN quedar fuera del constructor.
* En un atributo opcional, `null` DEBE considerarse un estado válido que significa "no existe".
* Los atributos DEBEN usar wrappers (`Integer`, `Long`, `Boolean`), nunca tipos primitivos.
* (f) La entity solo DEBE aplicar las reglas que pueda garantizar por sí misma.
* (f) Las reglas que requieren consultar otros datos, como la unicidad de un email, DEBEN comprobarse en el servicio correspondiente.
* (f) Las reglas de negocio, por ejemplo poner un rol por defecto, se deben situar en el servicio.

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
