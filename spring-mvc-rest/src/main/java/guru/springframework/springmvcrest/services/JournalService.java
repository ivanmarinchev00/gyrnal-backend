package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.domain.Journal;

import java.util.List;

public interface JournalService {
    Journal addJournal(Journal journal, int id) throws Exception;
    Journal findJournalById(int id);
    List<Exercise> findExercisesForJournalByDate(int id, String date);
    void deleteExercise(int journalId, int exerciseId);
    Exercise addExercise(int id, Exercise exercise);
    Exercise updateExercise(int id, Exercise exercise);
    List<Exercise> getAllExercisesForJournal(int id);
}
