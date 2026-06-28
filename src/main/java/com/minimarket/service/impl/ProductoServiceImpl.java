package com.minimarket.service.impl;

import com.minimarket.entity.Producto;
import com.minimarket.repository.ProductoRepository;
import com.minimarket.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public boolean hayStock(Long productoId, int cantidad) {
        Optional<Producto> producto = productoRepository.findById(productoId);
        return producto.isPresent() && producto.get().getStock() >= cantidad;
    }

    @Override
    public Producto descontarStock(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe"));
        if (producto.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }
}
