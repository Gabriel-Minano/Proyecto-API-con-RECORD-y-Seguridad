package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonaRequest;
import com.example.demo.dto.PersonaResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;
@Service 
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repository;

    public PersonaServiceImpl(PersonaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonaResponse> listarPersonas() {

        return repository.findAll().stream().map(this::convertirAResponse).toList();
    }

    @Override
    public PersonaResponse buscarPorId(Long id) {

        Persona persona = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("La persona con la  ID: " + id + " no existe"));
        return convertirAResponse(persona);
    }

    @Override
    public PersonaResponse crearPersona(PersonaRequest request) {
        Persona nuevo = new Persona(request.nombre(), request.edad());
        Persona guardada = repository.save(nuevo);
        return convertirAResponse(guardada);
    }

    @Override
    public PersonaResponse actualizarPersona(Long id, PersonaRequest request) {
        Persona personaActual = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La persona con la  ID: " + id + " no existe"));
        personaActual.setNombre(request.nombre());
        personaActual.setEdad(request.edad());
        return convertirAResponse(personaActual);
    }

    @Override
    public void eliminarPersona(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("La persona con la  ID: " + id + " no existe");
        }
        repository.deleteById(id);
    }

    private PersonaResponse convertirAResponse(Persona p) {
        return new PersonaResponse(p.getId(), p.getNombre(), p.getEdad());
    }
}
