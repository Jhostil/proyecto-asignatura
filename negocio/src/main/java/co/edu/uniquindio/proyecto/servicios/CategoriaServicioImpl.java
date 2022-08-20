package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {
        return categoriaRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna categoría"));
    }

    @Override
    public Categoria obtenerCatPorNombre(String nombre) throws Exception {
        return categoriaRepo.buscarPorNombre(nombre);
    }

    @Override
    public boolean existenCat() {
        boolean resultado = true;
        List<Categoria> cat = categoriaRepo.findAll();
        if (cat == null || cat.size() == 0) {
            resultado = false;
        }
        return resultado;
    }

    @Override
    public Categoria registrarCategoria(Categoria categoria) throws Exception {
        return categoriaRepo.save(categoria);
    }


}
