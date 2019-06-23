package br.edu.utfpr.tsi.redes2email.service;

import br.edu.utfpr.tsi.redes2email.job.SaveSuggestion;
import br.edu.utfpr.tsi.redes2email.job.SenderSuggestion;
import br.edu.utfpr.tsi.redes2email.model.Contact;
import br.edu.utfpr.tsi.redes2email.model.Phone;
import br.edu.utfpr.tsi.redes2email.model.Suggestion;
import br.edu.utfpr.tsi.redes2email.repository.ContactRepository;
import br.edu.utfpr.tsi.redes2email.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    private final ContactRepository contactRepository;

    private final JavaMailSender javaMailSender;

    public Suggestion newContact(Suggestion suggestion) {
        var contact = Contact.newContact();
        contact.getPhones().add(Phone.newPhone());

        suggestion.setContact(contact);
        return suggestion;
    }

    @Async
    @Transactional
    public void saveSuggestion(Suggestion suggestion) {
        SaveSuggestion.newSaveSuggestion(suggestionRepository, contactRepository, suggestion).run();
        SenderSuggestion.newSender(javaMailSender, suggestion).run();
    }

    public List<Suggestion> getAll() {
        var suggestions = new ArrayList<Suggestion>();

        suggestionRepository.findAll().forEach(suggestions::add);

        return suggestions;
    }


}
