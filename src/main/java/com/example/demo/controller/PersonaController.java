package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonaRequest;
import com.example.demo.dto.PersonaResponse;
import com.example.demo.service.PersonaService;
import com.example.demo.wrapper.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    private final PersonaService service;

    public PersonaController(PersonaService service) {
        this.service = service;
    }

    // Listar todas las personas
    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonaResponse>>> listarTodos() {
        List<PersonaResponse> lista = service.listarPersonas();

        ApiResponse<List<PersonaResponse>> response = new ApiResponse<>(
                true,
                "Personas obtenidos correctamente",
                lista);
        return ResponseEntity.ok(response);
    }

    // Buscar por id a una persona
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonaResponse>> buscarPorId(@PathVariable Long id) {
        PersonaResponse persona = service.buscarPorId(id);
        ApiResponse<PersonaResponse> response = new ApiResponse<>(
                true,
                "Persona con ID: " + id + " encontrada",
                persona);
        return ResponseEntity.ok(response);
    }

    // Crear una persona
    @PostMapping
    public ResponseEntity<ApiResponse<PersonaResponse>> crearPersona(@Valid @RequestBody PersonaRequest request) {
        PersonaResponse persona = service.crearPersona(request);
        ApiResponse<PersonaResponse> response = new ApiResponse<>(
                true,
                "Persona creada con éxito",
                persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Actualizar una persona
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonaResponse>> actualizarPersona(@PathVariable Long id,
            @Valid @RequestBody PersonaRequest request) {
        PersonaResponse persona = service.actualizarPersona(id, request);
        ApiResponse<PersonaResponse> response = new ApiResponse<>(
            true,
            "Persona actualizada con éxito",
            persona
        );
        return ResponseEntity.ok(response);
    }

    //Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPersona(@PathVariable Long id){
        service.eliminarPersona(id);
        ApiResponse<Void> response = new ApiResponse<>(
            true,
            "Persona eliminada correctamente",
            null
        );
        return ResponseEntity.ok(response);
    }
}
