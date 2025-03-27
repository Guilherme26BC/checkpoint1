package br.com.fiap.checkpoint1.controller;

import br.com.fiap.checkpoint1.dto.PacienteRequestCreate;
import br.com.fiap.checkpoint1.model.Paciente;
import br.com.fiap.checkpoint1.service.PacienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class ControllerPaciente {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody PacienteRequestCreate dto){
        Paciente pacienteCreate = pacienteService.createPaciente(dto);
        return ResponseEntity.status(201).body(pacienteCreate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id){
        return pacienteService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

}
