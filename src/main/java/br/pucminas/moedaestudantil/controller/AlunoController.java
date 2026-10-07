package br.pucminas.moedaestudantil.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.pucminas.moedaestudantil.model.Aluno;
import br.pucminas.moedaestudantil.repository.InstituicaoRepository;
import br.pucminas.moedaestudantil.service.AlunoService;
import jakarta.validation.Valid;

/**
 * CRUD de aluno (RF02) — camada Controller do MVC: recebe as requisições,
 * chama o service e escolhe a view Thymeleaf.
 */
@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final InstituicaoRepository instituicaoRepository;

    public AlunoController(AlunoService alunoService, InstituicaoRepository instituicaoRepository) {
        this.alunoService = alunoService;
        this.instituicaoRepository = instituicaoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listar());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("instituicoes", instituicaoRepository.findAll());
        return "alunos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoService.buscar(id));
        model.addAttribute("instituicoes", instituicaoRepository.findAll());
        return "alunos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("aluno") Aluno aluno, BindingResult erros,
            @RequestParam Long instituicaoId, Model model, RedirectAttributes flash) {
        boolean novo = aluno.getId() == null;
        if (novo && (aluno.getSenha() == null || aluno.getSenha().isBlank())) {
            erros.rejectValue("senha", "obrigatoria", "A senha é obrigatória no cadastro");
        }
        if (erros.hasErrors()) {
            model.addAttribute("instituicoes", instituicaoRepository.findAll());
            model.addAttribute("instituicaoSelecionada", instituicaoId);
            return "alunos/form";
        }
        try {
            alunoService.salvar(aluno, instituicaoId);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("instituicoes", instituicaoRepository.findAll());
            model.addAttribute("erroGeral", "CPF, email ou login já cadastrados.");
            return "alunos/form";
        }
        flash.addFlashAttribute("mensagem", novo ? "Aluno cadastrado com sucesso." : "Aluno atualizado com sucesso.");
        return "redirect:/alunos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes flash) {
        alunoService.excluir(id);
        flash.addFlashAttribute("mensagem", "Aluno excluído.");
        return "redirect:/alunos";
    }
}
