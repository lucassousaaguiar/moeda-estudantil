package br.pucminas.moedaestudantil.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.pucminas.moedaestudantil.model.EmpresaParceira;
import br.pucminas.moedaestudantil.repository.EmpresaParceiraRepository;

/**
 * Regras do CRUD de empresa parceira (RF03), com senha em hash (RNF04).
 */
@Service
public class EmpresaService {

    private final EmpresaParceiraRepository empresaRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public EmpresaService(EmpresaParceiraRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaParceira> listar() {
        return empresaRepository.findAll();
    }

    public EmpresaParceira buscar(Long id) {
        return empresaRepository.findById(id).orElseThrow();
    }

    public EmpresaParceira salvar(EmpresaParceira empresa) {
        if (empresa.getId() != null) {
            EmpresaParceira existente = buscar(empresa.getId());
            if (empresa.getSenha() == null || empresa.getSenha().isBlank()) {
                empresa.setSenhaHash(existente.getSenhaHash()); // mantém a senha atual
            }
        }
        if (empresa.getSenha() != null && !empresa.getSenha().isBlank()) {
            empresa.setSenhaHash(encoder.encode(empresa.getSenha()));
        }
        return empresaRepository.save(empresa);
    }

    public void excluir(Long id) {
        empresaRepository.deleteById(id);
    }
}
