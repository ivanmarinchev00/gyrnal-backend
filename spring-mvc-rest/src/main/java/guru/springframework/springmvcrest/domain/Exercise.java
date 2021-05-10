package guru.springframework.springmvcrest.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String date;
    private int sets;
    private int reps;
    private double weight;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="journal_id", nullable=false)
    private Journal journal;

    protected Exercise(){

    }
    public Exercise(String name, int reps, int sets, double weight, String date){
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.date = date;
    }
    public int getReps(){
        return reps;
    }
    public int getSets(){
        return sets;
    }
    public String getDate() {return date;}
    public double getWeight() {
        return weight;
    }
    public String getName(){
        return name;
    }
    public void dismissJournal(){
//        this.journal.deleteExercise(this);
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Exercise or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Exercise)) {
            return false;
        }

        // typecast o to Exercise so that we can compare data members
        Exercise e = (Exercise) o;

        // Compare the data members and return accordingly
        return this.getName().equals(e.getName())
                && this.getReps() == e.getReps()
                && this.getSets() == e.getSets()
                && this.getWeight() == e.getWeight()
                && this.getDate().equals(e.getDate());
    }
}
