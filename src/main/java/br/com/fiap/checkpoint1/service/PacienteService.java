package br.com.fiap.checkpoint1.service;

import br.com.fiap.checkpoint1.dto.PacienteRequestCreate;
import br.com.fiap.checkpoint1.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
