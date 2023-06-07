package com.repository;

import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.furniturestore.models.entity.users.Client;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.FurnitureType;
import com.others.formsSystem.UserType;
import org.junit.jupiter.api.*;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {

    private static DataBase dataBase;
    private static User user;
    private static Furniture furniture;
    private static Client client;

    @BeforeAll
    static void setUpAll(){
        dataBase = new DataBase();
        user = new User("Test", "User", UserType.vendor);
        furniture = new TraditionalFurniture(dataBase.getFurnitureId(FurnitureType.traditional), "test", "test of traditional furniture", 25.5, 99.90);
        client = new Client(99999999);

        dataBase.setUserType(UserType.vendor);
        if ( !dataBase.isUserExists(user) ) {
            dataBase.setUser(user);
        }

        if (!dataBase.isFurnitureExists(furniture, FurnitureType.traditional))
            dataBase.addFurniture(furniture);
        if (!dataBase.isClientExists(client)){
            dataBase.addClient(client);
        }
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        dataBase.removeUser(user);
        dataBase.removeFurniture(furniture.getId(), FurnitureType.traditional);
    }

    @Test
    void getCONNECTION() {
        assertNotNull(dataBase.getCONNECTION());
    }

    @Test
    void setUser() {
        assertNotNull(dataBase.getUsers().stream().filter(n -> n.equals(user)));
    }

    @Test
    void getUsers() {
        assertEquals(1, dataBase.getUsers().stream().filter(n -> user.getData().equals(n.getData())).toList().size());
    }

    @Test
    void isUserExists() {
        assertEquals(1,dataBase.getUsers().stream().filter( n -> n.getData().equals( dataBase.getUser(user).getData() ) && n.getUserType()==user.getUserType()).toList().size());
    }

    @Test
    void addFurniture() {
        assertNotNull(dataBase.getFurniture().stream().filter(n -> n.equals(furniture)));
    }

    @Test
    void getFurniture() {
        assertEquals(1,dataBase.getFurniture().stream().filter(n -> n.getName().equals(furniture.getName()) && n.getId()==furniture.getId()+1).toList().size());
    }

    @Test
    void removeFurniture() {
        dataBase.removeFurniture(furniture.getId(), FurnitureType.traditional);
        assertEquals(0, dataBase.getFurniture().stream().filter(n -> n.getName().equals(furniture.getName()) && n.getId()==furniture.getId()).toList().size());
    }

    @Test
    void addToSale() {
    }

    @Test
    void addClient() {
    }

    @Test
    void isClientExists() {
    }
}