import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    //a. Конструктор
    @Test
    public void nullInGetHippodrome() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    public void nullInGetHippodrome_ReturnMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyListInGetHippodrome() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>()));
    }

    @Test
    public void emptyListInGetHippodrome_ReturnMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    //b. метод getHorses
    @Test
    void getHorses() {
        double speed = 2.0;
        double distance = 3.0;
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse" + (i + 1);
            horseList.add(new Horse(name, speed, distance));
            speed += 0.1;
            distance += 0.1;
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        Assertions.assertIterableEquals(horseList, hippodrome.getHorses());
    }

    //c. метод move
    @Test
    void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            horseList.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        hippodrome.getHorses().forEach(horse -> Mockito.verify(horse).move());
    }

    //d. метод getWinner
    @Test
    void getWinner() {
        Horse mockHorse1 = Mockito.mock(Horse.class);
        Mockito.when(mockHorse1.getDistance()).thenReturn(2.0);
        Horse mockHorse2 = Mockito.mock(Horse.class);
        Mockito.when(mockHorse2.getDistance()).thenReturn(5.0);
        Horse mockHorse3 = Mockito.mock(Horse.class);
        Mockito.when(mockHorse3.getDistance()).thenReturn(3.0);
        List<Horse> horseList = new ArrayList<>();
        horseList.add(mockHorse1);
        horseList.add(mockHorse2);
        horseList.add(mockHorse3);

        Hippodrome hippodrome = new Hippodrome(horseList);

        assertSame(mockHorse2, hippodrome.getWinner());// если нужен тотже объект
    }
}