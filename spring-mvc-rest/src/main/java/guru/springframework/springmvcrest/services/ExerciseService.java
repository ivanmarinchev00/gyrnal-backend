package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.domain.Journal;

import java.util.List;

public interface ExerciseService {
    Exercise findById(int id);
    Boolean removeExercise(int id);
    List<Exercise> getAllExercises();
}
