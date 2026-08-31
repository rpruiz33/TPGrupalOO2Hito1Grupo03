 
# “TPGrupalOO2Hito1Grupo03”


# Integrantes 
# Roberto Andres Ruiz Pereira--> rpruiz33
# Federico Matias Salvatierra--> Fsalvatierra 
# Leonel Alejandro Soloaga--> Le0oneL
# Ana Belen Vazquez--> anavazquez141

# Sistema de Gestión de Festivales Gastronómicos

Proyecto de persistencia y consultas avanzadas sobre el modelo ORM (Hibernate ) desarrollado para la materia Orientación a Objetos 2 .

---

## Casos de Uso e Implementación por Estudiante

### 1. Ruiz Pereira, Roberto Andrés

* **Caso de Uso 1 (Herencia / Polimorfismo): Consulta de Puestos Desarmables**
* **Método:** `PuestoDesarmableABM.traerPuestosComplejos(cantCarpasMin, tiempoMontajeMax, superficieMin, id)`
* **Relación ORM:** **Herencia (`UnidadVenta` $\leftarrow$ `PuestoDesarmable`)**.
* **Descripción:** Filtra instancias específicas de la subclase `PuestoDesarmable` que heredan de `UnidadVenta` (`superficieM2`, `nombreComercial`), evaluando tanto sus atributos propios (`cantidadCarpas`, `tiempoMontajeMin`) como los heredados para la organización logística del predio.


* **Caso de Uso 2 (Uno a Muchos / Muchos a Muchos): Unidades de Venta por Densidad de Staff**
* **Método:** `FestivalABM.traerUnidadesVentaPorStaffDeFestival(cantidadMinimaStaff)`
* **Relación ORM:** **Uno a Muchos (`Festival` $\rightarrow$ `UnidadVenta`) / Muchos a Muchos (`UnidadVenta` $\leftrightarrow$ `Staff`)**.
* **Descripción:** Navega desde un `Festival` hacia sus `UnidadesVenta` asociadas y filtra aquellas que superan un umbral en su colección de `Staff` asignados para auditar la capacidad operativa del evento.



---

### 2. Salvatierra, Federico Matías

* **Caso de Uso 1 (Muchos a Muchos con Atributos / Filtro Navegado): Cocineros Asignados a Festivales por Rango de Fechas**
* **Método:** `CocineroABM.traerCocinerosPorFestivalYFechas(idFestival, fechaInicio, fechaFin)`
* **Relación ORM:** **Muchos a Muchos (`Festival` $\leftrightarrow$ `UnidadVenta` $\leftrightarrow$ `Staff` / `Cocinero`)**.
* **Descripción:** Recupera la nómina de cocineros pertenecientes a la jerarquía de `Staff` vinculados a un festival específico, cruzando el rango temporal de sus contratos o fechas de ingreso.


* **Caso de Uso 2 (Uno a Muchos / Herencia Polimórfica): Unidades de Venta Filtradas por Datos del Staff**
* **Método:** `UnidadVentaABM.traerUnidadesVentaPorDatosStaff(dni, fechaNacimiento, fechaIngreso)`
* **Relación ORM:** **Muchos a Muchos (`UnidadVenta` $\leftrightarrow$ `Staff`)**.
* **Descripción:** Rastrea y recupera la colección de `UnidadesVenta` asociadas a un miembro específico del personal de cocina o caja mediante la coincidencia de sus datos filiatorios en la entidad base `Staff`.



---

### 3. Soloaga, Leonel

* **Caso de Uso 1 (Uno a Muchos con Fetch Join / Lazy Loading): Carga Optimizada de Platos por Unidad de Venta**
* **Método:** `UnidadVentaABM.traerUnidadVentaConPlatos(idUnidadVenta)`
* **Relación ORM:** **Muchos a Muchos (`UnidadVenta` $\leftrightarrow$ `Plato`) con `FETCH JOIN**`.
* **Descripción:** Realiza la recuperación de una `UnidadVenta` junto con la inicialización explícita de su colección de platos offered (`FETCH JOIN`), previniendo excepciones de inicialización perezosa (*LazyInitializationException*) en la capa de negocio.


* **Caso de Uso 2 (Herencia / Discriminador o Subclase): FoodTracks por Requerimiento Eléctrico**
* **Método:** `UnidadVentaABM.traerFoodTracksConElectricidad(requiereElectricidad)`
* **Relación ORM:** **Herencia (`UnidadVenta` $\leftarrow$ `FoodTrack`)**.
* **Descripción:** Filtra directamente sobre la subclase `FoodTrack` los registros que requieren conexión eléctrica (`requiereElectricidad = true/false`), permitiendo la correcta zonificación dentro del plano del evento.

### 4. Vazquez, Ana 

* **Caso de Uso 1 (Herencia): Consulta de FoodTracks por superficie mínima**

* **Método:** `UnidadVentaABM.traerFoodTracksPorSuperficieMinima(double minSuperficie)`

* **Relación ORM:** **Herencia (`UnidadVenta` ← `FoodTrack`)**.

* **Descripción:** Realiza una consulta sobre la subclase `FoodTrack`, heredada de `UnidadVenta`, filtrando los FoodTracks cuya superficie sea mayor o igual a la superficie mínima indicada. Los resultados se ordenan de mayor a menor superficie.

* **Caso de Uso 2 (Uno a Muchos): Consulta de Unidades de Venta con una cantidad mínima de pedidos**

* **Método:** `UnidadVentaABM.traerUnidadesVentaConMinimoPedidos(long minPedidos)`

* **Relación ORM:** **Uno a Muchos (`UnidadVenta` → `Pedido`)**.

* **Descripción:** Devuelve las unidades de venta que poseen una cantidad de pedidos mayor o igual al mínimo indicado. La consulta utiliza `JOIN`, `GROUP BY` y `HAVING COUNT` para agrupar los pedidos correspondientes a cada unidad de venta y filtrar aquellas que cumplen con la cantidad mínima solicitada.


## Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Persistencia:** Hibernate / HQL 
* **Base de Datos:** MySQL 
* **Patrones Aplicados:** DAO (Data Access Object), ABM, Singleton, Herencia.


## Ejecución del Test

Para ejecutar la carga de datos de prueba y la corrida de las consultas desarrolladas:

1. Configurar las credenciales en `hibernate.cfg.xml`.
2. Incluir en el **Build Path** del proyecto la carga de librerías proporcionada por la cátedra.
3. Ejecutar la clase principal de pruebas:

test.TestFestival

