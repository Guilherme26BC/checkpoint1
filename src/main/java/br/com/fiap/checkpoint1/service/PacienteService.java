package br.com.fiap.checkpoint1.service;

import br.com.fiap.checkpoint1.dto.PacienteRequestCreate;
import br.com.fiap.checkpoint1.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private List<Paciente> pacientes = new ArrayList<>();

    private Long sequence =1L;

    public Paciente createPaciente(PacienteRequestCreate dto){
        Paciente paciente = new Paciente();
        paciente.setId(sequence++);
        //
        paciente.setNome(dto.getNome());
        paciente.setEndereco(dto.getEndereco());
        paciente.setBairro(dto.getBairro());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone_completo(dto.getTelefone_completo());

        pacientes.add(paciente);
        return paciente;
    }
    public boolean deletePaciente(Long id){
        return pacientes.removeIf(p->p.getId().equals(id));
    }

    public Optional<Paciente> getById(Long id){
        return pacientes.stream()
                .filter(p-> p.getId().equals(id))
                .findFirst();
    }
    public List<Paciente> getAllPacientes(){
        return pacientes;
    }
}
