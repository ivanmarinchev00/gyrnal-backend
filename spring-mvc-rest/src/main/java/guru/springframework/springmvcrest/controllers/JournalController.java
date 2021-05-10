package guru.springframework.springmvcrest.controllers;
import guru.springframework.springmvcrest.domain.Exercise;
import guru.springframework.springmvcrest.domain.Journal;
import guru.springframework.springmvcrest.services.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = JournalController.BASE_URL,produces = "application/json")
public class JournalController {
    private final JournalService journalService;
    public static final String BASE_URL = "/api/v1/customers/journal";

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    @PostMapping("/addjournal/{id}") // Change?
    @ResponseStatus(HttpStatus.CREATED)
    public Journal addJournal(@RequestBody Journal journal, @PathVariable int id) throws Exception {
        return journalService.addJournal(journal, id);
    }

    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    @GetMapping("/{id}")
    public Journal findJournalById(@PathVariable int id){
        return journalService.findJournalById(id);
    }


    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    @DeleteMapping("{journalid}/deleteexercise/{id}") // Change
    public void deleteExercise(@PathVariable int id, @PathVariable int journalid){
        journalService.deleteExercise(journalid, id);
    }
    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    @GetMapping("{id}/getallforjournal") // Change
    public List<Exercise> getAllExercisesForJournal(@PathVariable int id){
        return journalService.getAllExercisesForJournal(id);
    }

    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    @PostMapping("{id}/addexercise/")
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise addExercise(@PathVariable int id, @RequestBody Exercise exercise){
      return journalService.addExercise(id, exercise);
    }

}
