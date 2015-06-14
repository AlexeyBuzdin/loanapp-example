package com.github.larchaon.loanapp.util.orika;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.BaseIntegrationTest;
import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.larchaon.loanapp.matchers.BeanMatchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DataMapperTest extends BaseIntegrationTest {

    @Autowired
    DataMapper mapper;

    @Test
    public void DataMapper_should_map_RegisterClientModel_to_Client_by_default() throws Exception {
        RegisterClientModel model = TestBuilder.forBean(RegisterClientModel::new)
                .with(RegisterClientModel::setPersonalCode, 111111111L)
                .with(RegisterClientModel::setEmail, "my@email.com")
                .with(RegisterClientModel::setPassword, "pass")
                .with(RegisterClientModel::setPhoneNumber, "+371 232323")
                .build();

        Client client = mapper.map(model, Client.class);
        assertThat(client, allOf(
                hasProperty(Client::getPersonalCode, equalTo(111111111L)),
                hasProperty(Client::getEmail, equalTo("my@email.com")),
                hasProperty(Client::getPassword, equalTo("pass")),
                hasProperty(Client::getPhoneNumber, equalTo("+371 232323"))
        ));
    }
}