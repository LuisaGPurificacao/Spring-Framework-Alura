package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("ID do paciente informado não existe!");
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("ID do médico informado não existe!");

        var medico = escolherMedico(dados);

        var paciente = pacienteRepository.findById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente.get(), dados.data(), null);
        repository.save(consulta);
        return null;
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());
        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.id()))
            throw new ValidacaoException("Id da consulta informada não existe!");
        var consulta = repository.getReferenceById(dados.id());
        consulta.cancelar(dados.motivo());
    }
}
