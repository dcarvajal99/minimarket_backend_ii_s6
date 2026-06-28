package com.minimarket.service.impl;

import com.minimarket.entity.Carrito;
import com.minimarket.entity.Producto;
import com.minimarket.repository.CarritoRepository;
import com.minimarket.repository.ProductoRepository;
import com.minimarket.service.CarritoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    // Inyeccion por constructor: facilita el uso de @InjectMocks en las pruebas.
    public CarritoServiceImpl(CarritoRepository carritoRepository,
                              ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    @Override
    public Carrito findById(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public void deleteById(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public List<Carrito> findByUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Carrito agregarProducto(Carrito carrito) {
        if (carrito.getProducto() == null || carrito.getProducto().getId() == null) {
            throw new IllegalArgumentException("El carrito debe referenciar un producto valido");
        }
        // Consulta el stock real del producto (dependencia simulada en pruebas).
        Optional<Producto> producto = productoRepository.findById(carrito.getProducto().getId());
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("El producto no existe");
        }
        Integer cantidad = carrito.getCantidad();
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        if (producto.get().getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente para agregar el producto al carrito");
        }
        return carritoRepository.save(carrito);
    }

    @Override
    public boolean usuarioEsCorrecto(Carrito carrito, Long usuarioId) {
        return carrito != null
                && carrito.getUsuario() != null
                && carrito.getUsuario().getId() != null
                && carrito.getUsuario().getId().equals(usuarioId);
    }
}
