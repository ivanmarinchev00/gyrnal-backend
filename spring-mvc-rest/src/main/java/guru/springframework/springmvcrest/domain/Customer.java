package guru.springframework.springmvcrest.domain;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String roles = "";


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    //@JsonIgnore
    private Journal journal;

    public Customer(){

    }

    public Customer(String firstname, String lastname, String email, String password, String roles){
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.roles = roles;
    }

    public Customer(String username, String password, String role) {
        this.email = username;
        this.password = password;
        this.roles = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getEmail(){
        return this.email;
    }

    public Journal getJournal(){
            return this.journal;
        }


    public void setJournal(Journal j){this.journal = j;}

    
    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(String role){this.roles = role;}

    public String getRoles(){
        return this.roles;
    }
    public List<String> getRoleList(){
    if(this.roles.length()>0){
        return Arrays.asList(this.roles.split(","));
        }
    return new ArrayList<>();
    }

}
