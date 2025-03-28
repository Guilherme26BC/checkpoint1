package br.com.fiap.checkpoint1.controller;

import br.com.fiap.checkpoint1.dto.PacienteRequestCreate;
import br.com.fiap.checkpoint1.dto.PacienteRequestUpdate;
import br.com.fiap.checkpoint1.dto.PacienteResponse;
import br.com.fiap.checkpoint1.model.Paciente;
import br.com.fiap.checkpoint1.service.PacienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pacientes")
public class ControllerPaciente {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponse> createPaciente(@RequestBody PacienteRequestCreate dto){
        return ResponseEntity.status(201)
                .body(new PacienteResponse()
                        .toDto(pacienteService.createPaciente(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Long id, @RequestBody PacienteRequestUpdate dto){
        return pacienteService.updatePaciente(id, dto)
                .map(p->new PacienteResponse().toDto(p))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id){
    if(pacienteService.deletePaciente(id)){
        return ResponseEntity.status(204).build();
    }else{
        return ResponseEntity.notFound().build();
    }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable Long id){
        return pacienteService.getById(id)
                .map(p->new PacienteResponse().toDto(p))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAll(){
        List<PacienteResponse> responses= pacienteService.getAllPacientes()
                .stream()
                .map(p->new PacienteResponse().toDto(p))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

}
