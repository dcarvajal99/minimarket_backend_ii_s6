# MiniMarket Plus — Pruebas unitarias de Carrito, Inventario y Producto (Desarrollo Backend II, PBY2202)

Ejecución y análisis de pruebas unitarias con **JUnit 5 + Mockito + JaCoCo**
sobre el backend de "MiniMarket Plus" (Spring Boot 3, Java 17). Corresponde a la
actividad de la **Semana 5** del ramo *Desarrollo Backend II* (PBY2202, Duoc UC).

## Herramientas de testing

| Herramienta | Uso |
|---|---|
| **JUnit 5 (Jupiter)** | Framework de pruebas (`@Test`, `@BeforeEach`, `@DisplayName`, aserciones). |
| **Mockito** | Simulación de dependencias (`@Mock`, `@InjectMocks`, `when/thenReturn`, `verify`). |
| **JaCoCo** | Medición de cobertura (reporte en `target/site/jacoco/index.html`). |

## Lógica de negocio probada

- **`CarritoService`** — `agregarProducto()` (valida stock consultando el
  repositorio de productos) y `usuarioEsCorrecto()` (relación Carrito–Usuario).
- **`InventarioService`** — `datosMovimientoValidos()` (tipoMovimiento y cantidad
  no nulos/vacíos), `productoEsCorrecto()` (relación Producto–Inventario) y
  `registrarMovimiento()`.
- **`ProductoService`** — `hayStock()` y `descontarStock()`.

## Pruebas implementadas (30 en total)

- **`CarritoServiceTest`** (8): agregar con/sin stock, producto inexistente,
  cantidad inválida, relación Carrito–Usuario y CRUD.
- **`InventarioServiceTest`** (10): validación de movimiento, relación
  Producto–Inventario, registro válido/inválido y CRUD.
- **`ProductoServiceTest`** (8): `hayStock`, `descontarStock` y CRUD.
- **`UsuarioTest`** (3): pruebas de la entidad (proyecto base).

## Cobertura JaCoCo

| Clase | Antes | Después |
|---|---|---|
| `CarritoServiceImpl` | 10 % | **96 %** |
| `InventarioServiceImpl` | 10 % | **98 %** |
| `ProductoServiceImpl` | 10 % | **100 %** |

Las capturas del reporte (antes y después), la corrida en terminal y el código
están en la carpeta [`evidencias/`](evidencias/).

## Ejecución

```bash
./mvnw clean test
# Reporte de cobertura: target/site/jacoco/index.html
```
