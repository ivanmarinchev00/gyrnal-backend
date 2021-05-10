package guru.springframework.springmvcrest.domain;

public class AuthenticationResponse {

    private final String jwt;
    private String role;
    private Integer journalId;
    private Integer customerId;

    public AuthenticationResponse(String jwt, String role, Integer journalId, Integer customerId) {
        this.jwt = jwt;
        this.role = role;
        this.journalId = journalId;
        this.customerId = customerId;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole(){
        return this.role;
    }

    public Integer getJournalId(){return this.journalId;}

    public Integer getCustomerId(){return this.customerId;}

}
