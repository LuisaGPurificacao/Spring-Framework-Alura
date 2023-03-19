package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime data = dados.data();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = data.getHour() < 7;
        var depoisDoFechamentoDaClinica = data.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica)
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
    }

}
