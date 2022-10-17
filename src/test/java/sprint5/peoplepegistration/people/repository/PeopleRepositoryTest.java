package sprint5.peoplepegistration.people.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static sprint5.peoplepegistration.people.controller.stub.PeopleServiceStub.createAEntity;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = PeopleRepository.class)
@EnableReactiveMongoRepositories
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.2.8")
class PeopleRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private PeopleRepository peopleRepository;

    @Test
    void existsByIdAndCreditCardNumberAndCreditCardDateExpirationAndCreditCardCvv() {
    }

    @Test
    void existsByIdAndDebitCardNumberAndDebitCardDateExpirationAndDebitCardCvv() {
    }

    @Test
    void existsByIdAndPaypalEmailAndPaypalPassword() {
    }

    @Test
    void findByNome() {
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    void shouldSaveAPerson() {
        var entity = createAEntity();

        mongoTemplate.save(entity, "test");

        assertNotNull(entity);
        verify(mongoTemplate).save(entity, "test");
    }

    @Test
    void test() {
        // given
        PersonEntity personEntity = createAEntity();

        // when
        mongoTemplate.save(personEntity, "test");

        var entitySaved = peopleRepository.save(personEntity);

        // then
        assertNotNull(personEntity);
        //assertEquals("Camila", entitySaved.());
        verify(mongoTemplate).save(personEntity, "test");
    }
}