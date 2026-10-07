package br.pucminas.moedaestudantil.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.pucminas.moedaestudantil.model.Aluno;
import br.pucminas.moedaestudantil.model.Conta;
import br.pucminas.moedaestudantil.model.Instituicao;
import br.pucminas.moedaestudantil.repository.AlunoRepository;
import br.pucminas.moedaestudantil.repository.InstituicaoRepository;

/**
 * Regras do CRUD de aluno (RF02): cria a conta de moedas no cadastro,
 * guarda a senha como hash (RNF04) e vincula a instituição pré-cadastrada.
 */
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AlunoService(AlunoRepository alunoRepository, InstituicaoRepository instituicaoRepository) {
        this.alunoRepository = alunoRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscar(Long id) {
        return alunoRepository.findById(id).orElseThrow();
    }

    public Aluno salvar(Aluno aluno, Long instituicaoId) {
        Instituicao instituicao = instituicaoRepository.findById(instituicaoId).orElseThrow();
        aluno.setInstituicao(instituicao);

        if (aluno.getId() == null) {
            aluno.setConta(new Conta()); // novo aluno começa com saldo 0
        } else {
            Aluno existente = buscar(aluno.getId());
            aluno.setConta(existente.getConta());
            if (aluno.getSenha() == null || aluno.getSenha().isBlank()) {
                aluno.setSenhaHash(existente.getSenhaHash()); // mantém a senha atual
            }
        }
        if (aluno.getSenha() != null && !aluno.getSenha().isBlank()) {
            aluno.setSenhaHash(encoder.encode(aluno.getSenha()));
        }
        return alunoRepository.save(aluno);
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }
}
