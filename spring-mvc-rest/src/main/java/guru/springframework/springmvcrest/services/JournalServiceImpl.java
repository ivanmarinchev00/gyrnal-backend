package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.domain.Journal;
import guru.springframework.springmvcrest.repositories.JournalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin(origins ="http://localhost:3000")
public class JournalServiceImpl implements JournalService{
    private final JournalRepository journalRepository;
    private final ExerciseServiceImpl exerciseService;
    private final CustomerServiceImpl customerService;

    public JournalServiceImpl(JournalRepository journalRepository, ExerciseServiceImpl exerciseService1, CustomerServiceImpl customerService) {
        this.journalRepository = journalRepository;
        this.exerciseService = exerciseService1;
        this.customerService = customerService;
    }
    @Override
    public Journal addJournal(Journal journal, int customerId) throws Exception{
         Customer customer = customerService.findCustomerById(customerId);
         if(customer.getJournal() != null){
             throw new Exception("Customer already has a journal");
         }
         customer.setJournal(journal);
          return customerService.saveCustomer(customer).getJournal();
    }

    @Override
    public Journal findJournalById(int id){
        return journalRepository.findById(id).get();
    }

    @Override
    public void deleteExercise(int journalId, int exerciseID){
        Journal journal = this.findJournalById(journalId);
        Exercise exercise = exerciseService.findById(exerciseID);
        exerciseService.removeExercise(exerciseID);
    }

    @Override
    public Exercise addExercise(int id, Exercise exercise){
        Journal journal = this.findJournalById(id);
        journal.addExercise(exercise);
        journalRepository.save(journal);
        return exercise;
    }

    @Override
    public Exercise updateExercise(int id, Exercise exercise){
        Journal journal = this.findJournalById(id);
        Exercise e = journal.getExercises().get(exercise.getId());
        e.setName(exercise.getName());
        e.setReps(exercise.getReps());
        e.setSets(exercise.getSets());
        e.setWeight(exercise.getWeight());
        e.setDate(exercise.getDate());
        journalRepository.save(journal);
        return e;
    }
    @Override
    public List<Exercise> getAllExercisesForJournal(int journalId){
        List<Exercise> filteredExercises = new ArrayList<>();
        for (int i=0; i<exerciseService.getAllExercises().size(); i++)
        {
            if(exerciseService.getAllExercises().get(i).getJournal().getId() == journalId){
                filteredExercises.add(exerciseService.getAllExercises().get(i));
            }
        }
        return filteredExercises;
    }
    @Override
    public List<Exercise> findExercisesForJournalByDate(int id, String date){
        List<Exercise> filteredExercises = new ArrayList<>();
        for (int i=0; i<exerciseService.getAllExercises().size(); i++)
        {
            if(exerciseService.getAllExercises().get(i).getJournal().getId() == id && exerciseService.getAllExercises().get(i).getDate().equals(date)){
                filteredExercises.add(exerciseService.getAllExercises().get(i));
            }
        }
        return filteredExercises;
    }


}
