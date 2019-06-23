package br.edu.utfpr.tsi.redes2email.job;

import br.edu.utfpr.tsi.redes2email.model.Contact;
import br.edu.utfpr.tsi.redes2email.model.Suggestion;
import br.edu.utfpr.tsi.redes2email.repository.ContactRepository;
import br.edu.utfpr.tsi.redes2email.repository.SuggestionRepository;

public class SaveSuggestion implements Runnable {

    private final SuggestionRepository suggestionRepository;

    private final ContactRepository contactRepository;

    private final Suggestion suggestion;

    private SaveSuggestion(SuggestionRepository suggestionRepository, ContactRepository contactRepository, Suggestion suggestion) {
        this.suggestionRepository = suggestionRepository;
        this.contactRepository = contactRepository;
        this.suggestion = suggestion;
    }

    public static SaveSuggestion newSaveSuggestion(SuggestionRepository suggestionRepository, ContactRepository contactRepository, Suggestion suggestion) {
        return new SaveSuggestion(suggestionRepository, contactRepository, suggestion);
    }

    @Override
    public void run() {
        var contact = suggestion.getContact();
        contact.getPhones().forEach(phone -> phone.setContact(contact));

        contactRepository.save(contact);
        suggestionRepository.save(suggestion);
    }
}
