package br.edu.utfpr.tsi.redes2email.repository;

import br.edu.utfpr.tsi.redes2email.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
}
