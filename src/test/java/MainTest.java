import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.xml.datatype.Duration;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void main() {
        String[] args = {};
        assertTimeout(ofSeconds(22), new Executable() {
            @Override
            public void execute() throws Throwable {
                Main.main(args);
            }
        });
    }
}