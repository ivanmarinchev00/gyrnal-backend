package guru.springframework.springmvcrest.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
@Test
    public void getRepsTest(){
    Exercise exercise = new Exercise("Bench press", 12, 3, 100, "24-11-2020");
    assertEquals(12, exercise.getReps());
}
    @Test
    public void getSetsTest(){
        Exercise exercise = new Exercise("Bench press", 12, 3, 100, "24-11-2020");
        assertEquals(3, exercise.getSets());
    }
    @Test
    public void getWeightTest(){
        Exercise exercise = new Exercise("Bench press", 12, 3, 100, "24-11-2020");
        assertEquals(100, exercise.getWeight());
    }
    @Test
    public void getNameTest(){
        Exercise exercise = new Exercise("Bench press", 12, 3, 100, "24-11-2020");
        assertEquals("Bench press", exercise.getName());
    }
}