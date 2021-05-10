package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public Boolean removeExercise(int id) {
        if(exerciseRepository.existsById(id)){
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(exerciseRepository.getOne(id));
            exerciseRepository.deleteInBatch(exercises);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Exercise findById(int id){
        return exerciseRepository.findById(id).get();
    }

    @Override
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }


}
