package guru.springframework.springmvcrest.repositories;

import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
}
