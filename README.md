# 400CIS002-20232-poc-solid
Repositorio de código para la PoC de los Principios SOLID

## Introducción

La construcción de buenos sistemas de software depende en gran medida de qué tan **“clean”** (limpios) están tanto el código (**building blocks**) como la **arquitectura**. 
Por ende, los **Principios de Diseño SOLID** establecen la manera en que las funciones y los datos deberían agruparse en clases, y cómo estas últimas debería interconectarse **[1]**. 
Su objetivo, entonces es el de crear estructuras en módulos que sean capaces de **adaptarse al cambio**, que sean **entendibles fácilmente** y que sean la base de **componentes reutilizables** **[1]**.

******

## Historia

- **1980** – **Robert C. Martin** **[1]** presenta los principios en un debate de USENET (un sistema predecesor y similar a Facebook).
- **2000** – Los principios se establecen en un orden diferente.
- **2004** – **Michael Feathers** sugiere ordenar los principios de tal manera que sean llamados **SOLID**.

******

## Principios de Diseño SOLID

|                 Principio                 |                                                                                                                     Descripción                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                             Antes de Aplicarlo                                                                                                                                                                                                                                                                                                                                                                                                                                             |                                                                                                                                                                                                                                                                     Después de aplicarlo                                                                                                                                                                                                                                                                      |
|:-----------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| **Single Responsibility Principle (SRP)** | Cada módulo de software debe tener sólo una razón para cambiar, y esta razón viene definida como un actor (grupo común de stakeholders o usuarios del sistema). En definitiva, el código debería separarse si varios actores dependen de él **[1]**. |                                                                                                                                                          1) Duplicaciones de Código. <br/> 2) Merge conflicts.  <br/> <br/> **Ejemplo**: la funcionalidad del manejo de la base de datos y de la gestión de las órdenes está acoplada entre las clases porque tienen responsabilidades compartidas. **Actores**: **DBA**, usaría la **Database** para consultar las órdenes; el **OrderManager** también ejecutará la consulta del consolidado de pago de estas órdenes. Estos se van a ver afectados mutuamente frente a cualquier cambio. <br/>   ![SRP-Before.png](single-responsibility-principle%2Fsrc%2Fmain%2Fresources%2FSRP-Before.png)                                                                                                                                                           |                                                                                        1) DRY principle.  <br/>  2) Disminuyen merge conflicts. <br/> <br/> **Ejemplo**: los diferentes actores usarán diferentes clases para la ejecución de sus objetivos, con responsabilidades claras y definidas: **persister**, **register** y **payment calculator**. <br/> <br/>   ![SRP-After.png](single-responsibility-principle%2Fsrc%2Fmain%2Fresources%2FSRP-After.png)                                                                                         |
|      **Open-Closed Principle (OCP)**      |                                              Para que un sistema pueda cambiar fácilmente se deben diseñar de tal manera que dicho cambio sea añadiendo más código y no cambiando el existente **[1]**.                                              |                                                                                                                                                                          1) Archivos de código fuente con cientos de líneas de código. <br/>  2) Diferentes variaciones de un mismo tipo de acción no dependen ni de interfaces ni de abstracciones.  <br/> <br/> **Ejemplo**: para la persistencia, se pueden usar diferentes estructuras o bases de datos. No obstante, este esquema que viene de la solución original depende de la implementación en particular de Database sin opción de extensión (o al menos no una limpia). <br/><br/> ![OCP-Before.png](open-closed-principle%2Fsrc%2Fmain%2Fresources%2FOCP-Before.png)                                                                                                                                                                          |                                                                         1) Archivos de código más pequeños y con alta cohesión.  <br/>  2) Abstracciones e Interfaces soportando extensibilidad.  <br/> <br/> **Ejemplo**: a través de **IDatabase** se logra la extensión a dos opciones de bases de datos, sin afectar el contrato (métodos) de uso desde las otras clases del módulo. <br/><br/>  ![OCP-After.png](open-closed-principle%2Fsrc%2Fmain%2Fresources%2FOCP-After.png)                                                                         |
|  **Liskov Substitution Principle (LSP)**  |                                 Establece que un sistema de software puede diseñarse de tal manera que sus partes puedan intercambiarse siempre y cuando estas se adhieran a contratos que permitan hacerlo **[1]**.                                 |                                                                                                                            1) Módulos interactúan mediante implementaciones concretas y no mediante interfaces. <br/> 2) *Casting objects*  o *instanceof* para definir implementación en concreto. <br/> 3) Interacciones guiadas por condicionales if. <br/> <br/> **Ejemplo**: el **PaymentCalculator** calcula el pago de manera ordinaria. Pero otra manera de hacerlo es a través de puntos de descuento (**DiscountPointsPaymentCalculator**). No obstante, el módulo implementa una u otra de manera directa sin la posibilidad de hacer una sustitución limpia <br/> <br/>  ![LSP-Before.png](liskov-substitution-principle%2Fsrc%2Fmain%2Fresources%2FLSP-Before.png)                                                                                                                            | 1) Interfaces bien definidas para la comunicación inter-modular. <br/> 2) Inyección de dependencias para definición de implementaciones concretas.  <br/> <br/> **Ejemplo**: por ende, **PaymentCalculator** se vuelve una abstracción desde la cual extienden los dos métodos: **Ordinary** y **DiscountPoints**. De esta manera las otras clases del módulo usarán esta abstracción y podrán reemplazar de manera limpia una implementación con otra. <br/> <br/>  ![LSP-After.png](liskov-substitution-principle%2Fsrc%2Fmain%2Fresources%2FLSP-After.png) |
| **Interface Segregation Principle (ISP)** |                                Sugiere no depender de dependencias que no se utilizan **[1]**. De acuerdo con **[2]**, este principio se alinea con el Lean Movement: crear valor con la mínima cantidad de recursos.                                | 1) Excepciones de tipo ***UnsupportedOperationException***.  <br/> 2) Comentarios como implementaciones. <br/> 3) Implementación vacía de métodos. <br/> 4) Módulos que solo usan un subconjunto de los métodos de la interfaz.  <br/> <br/> **Ejemplo**: la clase principal utiliza el **OrderManagerFacade** para conocer el estado de la base de datos. Mientras que el **OrderManager** solo utiliza la opción de persistencia para almacenar las órdenes. No obstante, persistencia y consulta de datos están acopladas en la misma interfaz **IDatabase**. Por ende, se necesita segregarla para que ambos mecanismos puedan ser usados de manera separada por cada una. De esa manera se evita que tengan acceso a servicios no deseados (que la clase principal pueda almacenar órdenes). <br/> <br/> ![ISP-Before.png](interface-segregation-principle%2Fsrc%2Fmain%2Fresources%2FISP-Before.png) |            Interfaces definidas con los métodos justos que necesitan exponer.   <br/> <br/> **Ejemplo**: en este caso se segregó en **IDatabasePersister**, para que el **OrderManagerFacade** lo use en su **OrderRegister**. Y en el **IDatabaseRetriever**, que va a ser usado por la clase principal para averiguar el estado de la base de datos. De esta manera se segregan las responsabilidades y se evitan accesos no deseados. <br/> <br/>   ![ISP-After.png](interface-segregation-principle%2Fsrc%2Fmain%2Fresources%2FISP-After.png)             |
| **Dependency Inversion Principle (DIP)**  |                                                                      De acuerdo con **[2]**, los módulos deberían depender de abstracciones y no de implementaciones concretas.                                                                      |                                                      1) Uso de implementaciones concretas en vez de interfaces. <br/> 2) Desarrolladores crean instancias de implementaciones concretas cada vez que las necesitan y no le delegan esta creación al framework (Spring IoC Container, por ejemplo) **[2]**.   <br/> <br/> **Ejemplo**: cada implementación de bases de datos, que fueron previamente extendidas en el **Open-Closed Principle**, utiliza una implementación particular de cada **DataSource** (A y B, correspondientes con una estructura de lista y otra de mapa). Se recomienda depender de abstracciones o interfaces y dejar que las implementaciones concretas las haga el framework mediante inyección de dependencias. <br/> <br/>  ![DIP-Before.png](dependency-inversion-principle%2Fsrc%2Fmain%2Fresources%2FDIP-Before.png)                                                      |                            Los módulos hacen uso de las interfaces y dejan que el framework realice la inyección de dependencias acorde con la implementación en particular a utilizar.  <br/> <br/> **Ejemplo**: en este caso se crea la interfaz **IDataSource** para que cualquier tipo de **DataSource** sea **inyectado** por el framework (clase principal), en cada Database, de acuerdo con las reglas de negocio. <br/> <br/>  ![DIP-After.png](dependency-inversion-principle%2Fsrc%2Fmain%2Fresources%2FDIP-After.png)                             |

**NOTA**: un **módulo** es un conjunto de funciones y estructuras de datos con alta cohesión **[2]** (aquella que implica el cumplimiento del SRP). Según **[2]**, esto implica que: el nombre del módulo representa su funcionalidad expuesta (mediante interfaces); y que frente a un nuevo cambio se afecten varias de las clases que pertenecen a dicho módulo, debido a la **alta cohesión**.

Finalmente, la aplicación de estos principios no solo garantiza la calidad y la mantenibilidad del código, sino que también ayuda a promover la agilidad en el ciclo de desarrollo de software.

******

## How to Test it?
Ejecute el método principal (main) de cada clase cuyo nombre se refiera a un principio de diseño SOLID:
- [SingleResponsibilityPrinciple.java](single-responsibility-principle%2Fsrc%2Fmain%2Fjava%2Forg%2Farchitecture%2Fsolid%2Fprinciples%2Fpoc%2FSingleResponsibilityPrinciple.java)
- [OpenClosedPrinciple.java](open-closed-principle%2Fsrc%2Fmain%2Fjava%2Forg%2Farchitecture%2Fsolid%2Fprinciples%2Fpoc%2Focp%2FOpenClosedPrinciple.java)
- [LiskovSubstitutionPrinciple.java](liskov-substitution-principle%2Fsrc%2Fmain%2Fjava%2Forg%2Farchitecture%2Fsolid%2Fprinciples%2Fpoc%2Flsp%2FLiskovSubstitutionPrinciple.java)
- [InterfaceSegregationPrinciple.java](interface-segregation-principle%2Fsrc%2Fmain%2Fjava%2Forg%2Farchitecture%2Fsolid%2Fprinciples%2Fpoc%2Fisp%2FInterfaceSegregationPrinciple.java)
- [DependencyInversionPrinciple.java](dependency-inversion-principle%2Fsrc%2Fmain%2Fjava%2Forg%2Farchitecture%2Fsolid%2Fprinciples%2Fpoc%2Fdip%2FDependencyInversionPrinciple.java)

******
******

## Referencias:

1. [Martin C, Robert (2018). Clean Architecture: A Craftsman’s Guide to Software Structure and Design. Pearson Education, pages 57-91, 201-211](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164)
2. [Enriquez, René. Salazar, Alberto (2018). Software Architecture with Spring 5.0: Design and architect highly scalable, robust, and high-performance Java applications. Packt, pages 17-28](https://www.amazon.com/Software-Architecture-Spring-5-0-high-performance/dp/1788992997)
3. [OpenWebinars - Clean Code](https://openwebinars.net/academia/aprende/clean-code/2258/)