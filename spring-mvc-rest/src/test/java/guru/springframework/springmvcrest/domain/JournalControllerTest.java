package guru.springframework.springmvcrest.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmvcrest.services.CustomerServiceImpl;
import guru.springframework.springmvcrest.services.JournalServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@AutoConfigureTestDatabase
public class JournalControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockmvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @MockBean
    private JournalServiceImpl journalService;
    private CustomerServiceImpl customerService;
    private List<Exercise> exercises;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void SetMockMvc(){
        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        exercises = new ArrayList<>();
    }

    @Test
    public void AddJournalTest() throws Exception {
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Journal j = new Journal();
        customer.setJournal(j);
        String journalJson = "{\\\"id\\\":\\\"0\\\",\\\"exercises\\\":null,\\\"customer\\\":\\null\\}";
        Mockito.when(journalService.addJournal(j, customer.getId())).thenReturn(j);
        String object = objectMapper.writeValueAsString(j);
        mockmvc.perform(post("/api/v1/customers/journal/addjournal/0").contentType(APPLICATION_JSON_UTF8)
        .content(object))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andReturn();

    }
    @Test
    public void AddExerciseTest() throws Exception {
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Journal j = new Journal();
        customer.setJournal(j);
        Exercise e = new Exercise("Push ups", 3, 12,10, "21-01-2021");
        Mockito.when(journalService.addExercise(0, e)).thenReturn(e);
        String object = objectMapper.writeValueAsString(e);
        mockmvc.perform(post("/api/v1/customers/journal/0/addexercise/").contentType(APPLICATION_JSON_UTF8)
                .content(object))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    public void GetAllForJournalTest() throws Exception {
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Journal j = new Journal();
        customer.setJournal(j);
        Exercise e = new Exercise("Push ups", 3, 12,10, "21-01-2021");
        exercises.add(e);
        Mockito.when(journalService.getAllExercisesForJournal(0)).thenReturn(exercises);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers/journal/0/getallforjournal");
        MvcResult result = mockmvc.perform(builder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(exercises);

        Assert.assertEquals(expected, content);
    }
    @Test
    public void DeleteExerciseTest() throws Exception {
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Journal j = new Journal();
        customer.setJournal(j);
        Exercise e = new Exercise("Push ups", 3, 12,10, "21-01-2021");
        Mockito.doNothing().when(journalService).deleteExercise(0,0);
        mockmvc.perform(delete("/api/v1/customers/journal/0/deleteexercise/0"));
    }
    @Test
    public void GetJournalById() throws Exception {
        Journal j = new Journal();
        Mockito.when(journalService.findJournalById(0)).thenReturn(j);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers/journal/0");
        MvcResult result = mockmvc.perform(builder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(j);

        Assert.assertEquals(expected, content);
    }
}
