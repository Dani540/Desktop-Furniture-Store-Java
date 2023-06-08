package com.repository;

import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.furniturestore.models.entity.users.Client;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.FurnitureType;
import com.others.formsSystem.UserType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private static Repository repository;
    private static User user;
    private static Furniture furniture;
    private static Client client;

    @BeforeAll
    static void setUpAll(){
        user = new User("Test", "User", UserType.vendor);
        furniture = new TraditionalFurniture(repository.getFurnitureId(FurnitureType.traditional), "test", "test of traditional furniture", 25.5, 99.90);
        client = new Client(99999999);

        repository.setUserType(UserType.vendor);

        if ( !repository.isUserExists(user) )
            repository.addUser(user);
        if (!repository.isFurnitureExists(FurnitureType.traditional))
            repository.addFurniture(furniture);
        if (!repository.isClientExists(client))
            repository.addClient(client);
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.removeUser(user);
        repository.removeFurniture(furniture.getId(), FurnitureType.traditional);
    }

    @Test
    void getCONNECTION() {
        assertNotNull(DataBase.getInstance().getCONNECTION());
    }

    @Test
    void setUser() {
        assertNotNull(repository.getUsers().stream().filter(n -> n.equals(user)));
    }

    @Test
    void getUsers() {
        assertEquals(1, repository.getUsers().stream().filter(n -> user.getData().equals(n.getData())).toList().size());
    }

    @Test
    void isUserExists() {
        assertEquals(1, repository.getUsers().stream().filter(n -> n.getData().equals( repository.getUser(user).getData() ) && n.getUserType()==user.getUserType()).toList().size());
    }

    @Test
    void addFurniture() {
        assertNotNull(repository.getFurniture().stream().filter(n -> n.equals(furniture)));
    }

    @Test
    void getFurniture() {
        assertEquals(1, repository.getFurniture().stream().filter(n -> n.getName().equals(furniture.getName()) && n.getId()==furniture.getId()+1).toList().size());
    }

    @Test
    void removeFurniture() {
        repository.removeFurniture(furniture.getId(), FurnitureType.traditional);
        assertEquals(0, repository.getFurniture().stream().filter(n -> n.getName().equals(furniture.getName()) && n.getId()==furniture.getId()).toList().size());
    }

    @Test
    void addClient() {
        assertNotNull(repository.getClientInfo(client));
    }

    @Test
    void isClientExists() {
        assertEquals(1, repository.getClient().stream().filter(n -> n.getRut()==client.getRut()).toList().size());
    }
}