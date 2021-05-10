package guru.springframework.springmvcrest.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @OneToMany(mappedBy="journal", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Exercise> exercises;
    @OneToOne(mappedBy = "journal", fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;

    protected Journal(){

    }

    public Journal(Customer customer) {
    this.exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises(){
        return exercises;
    }

    public void addExercise(Exercise exercise){
        exercise.setJournal(this);
        exercises.add(exercise);
    }

    public Integer getId(){
        return this.id;
    }
}
