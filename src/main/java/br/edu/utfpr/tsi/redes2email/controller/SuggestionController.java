package br.edu.utfpr.tsi.redes2email.controller;

import br.edu.utfpr.tsi.redes2email.model.Suggestion;
import br.edu.utfpr.tsi.redes2email.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SuggestionController implements WebMvcConfigurer {

    private final SuggestionService suggestionService;

    @GetMapping("/")
    public String listSuggestion(Model model) {
        model.addAttribute("suggestions", suggestionService.getAll());

        return "suggestion/list";
    }

    @GetMapping("suggestion")
    public String formSuggestion(final Suggestion suggestion, Model model) {
        var contact = suggestionService.newContact(suggestion);
        model.addAttribute("suggestion", contact);
        return "suggestion/new";
    }

    @PostMapping("suggestion")
    public String newSuggestion(@Valid @ModelAttribute Suggestion suggestion, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/suggestion/new";
        }

        suggestionService.saveSuggestion(suggestion);

        return "redirect:/";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("suggestion/list");
        registry.addViewController("/suggestion").setViewName("suggestion/new");
    }
}
