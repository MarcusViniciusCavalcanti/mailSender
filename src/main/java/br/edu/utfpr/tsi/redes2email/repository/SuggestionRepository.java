package br.edu.utfpr.tsi.redes2email.repository;

import br.edu.utfpr.tsi.redes2email.model.Suggestion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {
}
