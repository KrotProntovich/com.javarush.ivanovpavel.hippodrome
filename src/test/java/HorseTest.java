import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    //a. конструктор
    @Test
    public void nullNameInGetHorse() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null,5.0,5.0);
                });
    }

    @Test
    public void nullNameInGetHorse_ReturnMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null,5.0,5.0);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void emptyNameInGetHorse(String name) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name,5.0,5.0);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void emptyNameInGetHorse_ReturnMessage(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name,5.0,5.0);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void negativeSpeedInGetHorse() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse",-5.0,5.0);
                });
    }

    @Test
    public void negativeSpeedInGetHorse_ReturnMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse",-5.0,5.0);
                });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceInGetHorse() {
       Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse",5.0,-5.0);
                });
    }

    @Test
    public void negativeDistanceInGetHorse_ReturnMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse",5.0,-5.0);
                });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    //b. метод getName
    @Test
    void getName() {
        Horse horseTest = new Horse("Horse",5.0,10.0);
        assertEquals("Horse",horseTest.getName());
    }

    //c. метод getSpeed
    @Test
    void getSpeed() {
        Horse horseTest = new Horse("Horse",5.0,10.0);
        assertEquals(5.0, horseTest.getSpeed());
    }

    //d. метод getDistance
    @Test
    void getDistance() {
        Horse horseTest = new Horse("Horse",5.0,10.0);
        assertEquals(10.0, horseTest.getDistance());
    }

    //e. метод move
    @Test
    void callStaticMethodInMove() {
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)){
            Horse horseTest = new Horse("Horse",5.0,10.0);
            horseTest.move();
            mockStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"Horse1, 5.0, 2.0, 0.2",
                "Horse2, 2.0, 6.0,0.5",
                "Horse3, 1.0, 8.0, 4.0"})
    void move(String name, double speed, double distance, double random) {
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)){
            mockStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            Horse horseTest = new Horse(name, speed, distance);
            double distanceExpected = distance + speed * random;
            horseTest.move();
            assertEquals(distanceExpected,horseTest.getDistance());
        }
    }
}
