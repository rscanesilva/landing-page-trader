package br.com.trader.controller;

import br.com.trader.entity.Lead;
import br.com.trader.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LeadController {

    @Autowired
    LeadRepository repository;

    @GetMapping("")
    public String showIndex(Lead lead) {
        return "index";
    }

    @PostMapping("lead")
    public String cadastrarLead(Lead lead, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Erro no cadastro");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:";
        }
        redirectAttributes.addFlashAttribute("message", "Cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        repository.save(lead);
        return "redirect:";
    }

    @GetMapping("admin")
    private String obterLeads(Model model) {
        model.addAttribute("leads", repository.findAll());
        return "leads";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}
