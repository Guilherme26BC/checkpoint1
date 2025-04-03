package br.com.fiap.checkpoint1.service;

import br.com.fiap.checkpoint1.dto.PacienteRequestCreate;
import br.com.fiap.checkpoint1.dto.PacienteRequestUpdate;
import br.com.fiap.checkpoint1.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    //lista auxiliar
    private List<Paciente> pacientes = new ArrayList<>();
    //sequence para id
    private Long sequence =1L;

    public Paciente createPaciente(PacienteRequestCreate dto){
        Paciente paciente = new Paciente();
        paciente.setId(sequence++);
        //instanciando os atributos do paciente pelo dto
        paciente.setNome(dto.getNome());
        paciente.setEndereco(dto.getEndereco());
        paciente.setBairro(dto.getBairro());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone_completo(dto.getTelefone_completo());

        pacientes.add(paciente);
        return paciente;
    }
    public Optional<Paciente> updatePaciente(Long id, PacienteRequestUpdate dto){
        //percorre a lista e procura o id que corresponde ao digitado
        return pacientes.stream().filter(p->p.getId().equals(id))
                .findFirst()
                .map(p->{
                    //atualiza os dados do paciente e atualiza com o dto
                   p.setNome(dto.getNome());
                   p.setEndereco(dto.getEndereco());
                   p.setBairro(dto.getBairro());
                   p.setEmail(dto.getEmail());
                   p.setTelefone_completo(dto.getTelefone_completo());
                    return p;
                });
    }

    public boolean deletePaciente(Long id){
        return pacientes.removeIf(p->p.getId().equals(id));
    }

    public Optional<Paciente> getById(Long id){
        //retorna o primeiro elemento que corresponde
        return pacientes.stream()
                .filter(p-> p.getId().equals(id))
                .findFirst();
    }
    public List<Paciente> getAllPacientes(){
        return pacientes;
    }
}
