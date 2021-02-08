package com.desafioSpring.SalesAPI.search;


import com.desafioSpring.ClientsAPI.ApiClientsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiClientsApplication.class)
@AutoConfigureMockMvc
public class ClientsApplicationTests {

    String clientsURL= "/api/clients/createclient";
    String getClientsURL= "/api/clients/getclients";
    String getClientsFilteredURL= "/api/clients/getclientsfiltered";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void getAllClients() throws Exception {
        this.mockMvc.perform(get( getClientsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 99 ,\n" +
                        "    \"username\": \"Carmelo\" ,\n" +
                        "    \"birthdate\": \"10/10/2000\",\n" +
                        "    \"province\": \"Neuquen\"\n" +
                        "  }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"username\":\"diego\",\"birthdate\":\"23/4/1997\",\"province\":\"Buenos Aires\"},{\"id\":2,\"username\":\"camila\",\"birthdate\":\"13/7/1992\",\"province\":\"Cordoba\"},{\"id\":3,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":4,\"username\":\"micaela\",\"birthdate\":\"6/12/1999\",\"province\":\"Jujuy\"},{\"id\":5,\"username\":\"marcelo\",\"birthdate\":\"1/12/1949\",\"province\":\"Jujuy\"},{\"id\":6,\"username\":\"Tamara\",\"birthdate\":\"26/1/1995\",\"province\":\"Buenos Aires\"},{\"id\":7,\"username\":\"Carmen\",\"birthdate\":\"30/7/1989\",\"province\":\"Catamarca\"},{\"id\":8,\"username\":\"Julio\",\"birthdate\":\"28/5/1953\",\"province\":\"Catamarca\"},{\"id\":44,\"username\":\"Julian\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":34,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":17,\"username\":\"Macarena\",\"birthdate\":\"2/3/2000\",\"province\":\"Jujuy\"},{\"id\":99,\"username\":\"Carmelo\",\"birthdate\":\"10/10/2000\",\"province\":\"Neuquen\"}]")));
    }

    @Test
    void getClientsFilteredByProvince() throws Exception {
        this.mockMvc.perform(get( getClientsFilteredURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"province\":\"buenos aires\"   \n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"username\":\"diego\",\"birthdate\":\"23/4/1997\",\"province\":\"Buenos Aires\"},{\"id\":6,\"username\":\"Tamara\",\"birthdate\":\"26/1/1995\",\"province\":\"Buenos Aires\"}]")));
    }

    @Test
    void createClientWhenDOESNOTExists() throws Exception {
        this.mockMvc.perform(post( clientsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 99 ,\n" +
                        "    \"username\": \"Carmelo\" ,\n" +
                        "    \"birthdate\": \"10/10/2000\",\n" +
                        "    \"province\": \"Neuquen\"\n" +
                        "  }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"200\",\"message\":\"Operación exitosa\",\"clientlist\":[{\"id\":1,\"username\":\"diego\",\"birthdate\":\"23/4/1997\",\"province\":\"Buenos Aires\"},{\"id\":2,\"username\":\"camila\",\"birthdate\":\"13/7/1992\",\"province\":\"Cordoba\"},{\"id\":3,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":4,\"username\":\"micaela\",\"birthdate\":\"6/12/1999\",\"province\":\"Jujuy\"},{\"id\":5,\"username\":\"marcelo\",\"birthdate\":\"1/12/1949\",\"province\":\"Jujuy\"},{\"id\":6,\"username\":\"Tamara\",\"birthdate\":\"26/1/1995\",\"province\":\"Buenos Aires\"},{\"id\":7,\"username\":\"Carmen\",\"birthdate\":\"30/7/1989\",\"province\":\"Catamarca\"},{\"id\":8,\"username\":\"Julio\",\"birthdate\":\"28/5/1953\",\"province\":\"Catamarca\"},{\"id\":44,\"username\":\"Julian\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":34,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":17,\"username\":\"Macarena\",\"birthdate\":\"2/3/2000\",\"province\":\"Jujuy\"},{\"id\":99,\"username\":\"Carmelo\",\"birthdate\":\"10/10/2000\",\"province\":\"Neuquen\"}]}"));
    }



    //Acá tira error xq el cliente ya existe en el archivo json, para probarlo se puede cambiar cualquier valor del mismo
    //o bien borrarlo este usuario de clients.json y probarlo
    @Test
    void createClientWhenAlreadyExists() throws Exception {
        this.mockMvc.perform(post( clientsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 99 ,\n" +
                        "    \"username\": \"Sasha\" ,\n" +
                        "    \"birthdate\": \"10/10/2000\",\n" +
                        "    \"province\": \"chaco\"\n" +
                        "  }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"400\",\"message\":\"No se pudo crear el cliente, ya existe en usuario con ese id en el sistema\",\"clientlist\":[{\"id\":1,\"username\":\"diego\",\"birthdate\":\"23/4/1997\",\"province\":\"Buenos Aires\"},{\"id\":2,\"username\":\"camila\",\"birthdate\":\"13/7/1992\",\"province\":\"Cordoba\"},{\"id\":3,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":4,\"username\":\"micaela\",\"birthdate\":\"6/12/1999\",\"province\":\"Jujuy\"},{\"id\":5,\"username\":\"marcelo\",\"birthdate\":\"1/12/1949\",\"province\":\"Jujuy\"},{\"id\":6,\"username\":\"Tamara\",\"birthdate\":\"26/1/1995\",\"province\":\"Buenos Aires\"},{\"id\":7,\"username\":\"Carmen\",\"birthdate\":\"30/7/1989\",\"province\":\"Catamarca\"},{\"id\":8,\"username\":\"Julio\",\"birthdate\":\"28/5/1953\",\"province\":\"Catamarca\"},{\"id\":44,\"username\":\"Julian\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":34,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":17,\"username\":\"Macarena\",\"birthdate\":\"2/3/2000\",\"province\":\"Jujuy\"},{\"id\":99,\"username\":\"Carmelo\",\"birthdate\":\"10/10/2000\",\"province\":\"Neuquen\"}]}"));
    }

    @Test
    void createClientWithAlreadyInUseId() throws Exception {
        this.mockMvc.perform(post( clientsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 99 ,\n" +
                        "    \"username\": \"Mariano\" ,\n" +
                        "    \"birthdate\": \"10/10/2000\",\n" +
                        "    \"province\": \"Santa fe\"\n" +
                        "  }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"400\",\"message\":\"No se pudo crear el cliente, ya existe en usuario con ese id en el sistema\",\"clientlist\":[{\"id\":1,\"username\":\"diego\",\"birthdate\":\"23/4/1997\",\"province\":\"Buenos Aires\"},{\"id\":2,\"username\":\"camila\",\"birthdate\":\"13/7/1992\",\"province\":\"Cordoba\"},{\"id\":3,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":4,\"username\":\"micaela\",\"birthdate\":\"6/12/1999\",\"province\":\"Jujuy\"},{\"id\":5,\"username\":\"marcelo\",\"birthdate\":\"1/12/1949\",\"province\":\"Jujuy\"},{\"id\":6,\"username\":\"Tamara\",\"birthdate\":\"26/1/1995\",\"province\":\"Buenos Aires\"},{\"id\":7,\"username\":\"Carmen\",\"birthdate\":\"30/7/1989\",\"province\":\"Catamarca\"},{\"id\":8,\"username\":\"Julio\",\"birthdate\":\"28/5/1953\",\"province\":\"Catamarca\"},{\"id\":44,\"username\":\"Julian\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":34,\"username\":\"fernando\",\"birthdate\":\"2/3/1957\",\"province\":\"Chubut\"},{\"id\":17,\"username\":\"Macarena\",\"birthdate\":\"2/3/2000\",\"province\":\"Jujuy\"},{\"id\":99,\"username\":\"Carmelo\",\"birthdate\":\"10/10/2000\",\"province\":\"Neuquen\"}]}"));
    }


}
