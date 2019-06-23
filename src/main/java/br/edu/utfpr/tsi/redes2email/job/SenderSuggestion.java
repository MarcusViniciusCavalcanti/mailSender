package br.edu.utfpr.tsi.redes2email.job;

import br.edu.utfpr.tsi.redes2email.model.Phone;
import br.edu.utfpr.tsi.redes2email.model.Suggestion;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class SenderSuggestion implements Runnable {

    private final JavaMailSender sender;

    private final Suggestion suggestion;

    private SenderSuggestion(JavaMailSender sender, Suggestion suggestion) {
        this.sender = sender;
        this.suggestion = suggestion;
    }

    public static SenderSuggestion newSender(JavaMailSender javaMailSender, Suggestion suggestion) {
        return new SenderSuggestion(javaMailSender, suggestion);
    }

    @Override
    public void run() {
        var email = new SimpleMailMessage();
        var text = suggestion.getText();
        var name = suggestion.getContact().getName();
        var created = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss").format(suggestion.getCreated());
        var phones = suggestion.getContact().getPhones()
                .stream().map(Phone::getNumber)
                .collect(Collectors.joining(" - "));

        email.setTo("suggestion@girafas.com");
        email.setFrom("application@girafas.com");
        email.setSubject("Suggestão criada: " + created + " por: " + name);
        email.setSentDate(new Date());
        email.setText(
                "Nome: " + name + "\n" +
                "Criando em: " + created + "\n" +
                "Contatos: " + phones + "\n" +
                "Sugestão:\n" + text
        );

        sender.send(email);
    }
}
