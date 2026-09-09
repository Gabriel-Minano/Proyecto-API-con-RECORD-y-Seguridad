package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PersonaRequest;
import com.example.demo.dto.PersonaResponse;

public interface PersonaService {
    //Listar todas las personas
    List<PersonaResponse> listarPersonas();
    //Buscar una persona
    PersonaResponse buscarPorId(Long id);
    //Crear una persona
    PersonaResponse crearPersona(PersonaRequest request);
    //Actualizar una persona
    PersonaResponse actualizarPersona(Long id, PersonaRequest request);
    //Eliminar una persona
    void eliminarPersona(Long id);
}
