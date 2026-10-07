package br.pucminas.moedaestudantil.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.pucminas.moedaestudantil.model.Conta;
import br.pucminas.moedaestudantil.model.Instituicao;
import br.pucminas.moedaestudantil.model.Professor;
import br.pucminas.moedaestudantil.repository.InstituicaoRepository;
import br.pucminas.moedaestudantil.repository.ProfessorRepository;

/**
 * Carga inicial (RN07/RF12): instituições pré-cadastradas e professores
 * enviados pela instituição no momento da parceria.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final InstituicaoRepository instituicaoRepository;
    private final ProfessorRepository professorRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DataSeeder(InstituicaoRepository instituicaoRepository, ProfessorRepository professorRepository) {
        this.instituicaoRepository = instituicaoRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void run(String... args) {
        if (instituicaoRepository.count() > 0) {
            return;
        }
        Instituicao puc = instituicaoRepository.save(new Instituicao("PUC Minas"));
        instituicaoRepository.save(new Instituicao("UFMG"));
        instituicaoRepository.save(new Instituicao("CEFET-MG"));

        Professor p = new Professor();
        p.setNome("Marcos Rezende");
        p.setEmail("mrezende@pucminas.br");
        p.setLogin("mrezende");
        p.setSenhaHash(encoder.encode("prof123"));
        p.setCpf("111.222.333-44");
        p.setDepartamento("Engenharia de Software");
        p.setInstituicao(puc);
        Conta conta = new Conta();
        conta.creditar(1000); // crédito do semestre corrente (RN01)
        p.setConta(conta);
        professorRepository.save(p);
    }
}
