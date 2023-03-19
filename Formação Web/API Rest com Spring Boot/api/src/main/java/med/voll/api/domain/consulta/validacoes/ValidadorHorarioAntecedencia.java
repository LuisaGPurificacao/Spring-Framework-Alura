package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime data = dados.data();
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmMinutos = Duration.between(agora, data).toMinutes();

        if (diferencaEmMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");
    }

}
