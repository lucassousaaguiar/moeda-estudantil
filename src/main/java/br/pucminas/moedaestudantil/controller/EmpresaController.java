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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.pucminas.moedaestudantil.model.EmpresaParceira;
import br.pucminas.moedaestudantil.service.EmpresaService;
import jakarta.validation.Valid;

/**
 * CRUD de empresa parceira (RF03) — camada Controller do MVC.
 */
@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empresas", empresaService.listar());
        return "empresas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("empresa", new EmpresaParceira());
        return "empresas/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("empresa", empresaService.buscar(id));
        return "empresas/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("empresa") EmpresaParceira empresa, BindingResult erros,
            Model model, RedirectAttributes flash) {
        boolean nova = empresa.getId() == null;
        if (nova && (empresa.getSenha() == null || empresa.getSenha().isBlank())) {
            erros.rejectValue("senha", "obrigatoria", "A senha é obrigatória no cadastro");
        }
        if (erros.hasErrors()) {
            return "empresas/form";
        }
        try {
            empresaService.salvar(empresa);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erroGeral", "CNPJ, email ou login já cadastrados.");
            return "empresas/form";
        }
        flash.addFlashAttribute("mensagem", nova ? "Empresa cadastrada com sucesso." : "Empresa atualizada com sucesso.");
        return "redirect:/empresas";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes flash) {
        empresaService.excluir(id);
        flash.addFlashAttribute("mensagem", "Empresa excluída.");
        return "redirect:/empresas";
    }
}
