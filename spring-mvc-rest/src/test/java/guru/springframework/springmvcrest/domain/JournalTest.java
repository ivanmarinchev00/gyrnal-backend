package guru.springframework.springmvcrest.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    @Test
    private void addExerciseTest(){
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "shakira", "ROLE_ADMIN");
        Journal journal = new Journal(customer);
        Exercise exercise = new Exercise("Bench press", 12, 3, 100, "2019-10-10");
        journal.addExercise(exercise);
        assertEquals(1, journal.getExercises().size());
    }
}