import org.junit.Test;
import java.io.*;

public class ExceptionDemoTest {
    @Test(expected = IOException.class)
    public void testUnhappyMethodWithNonAscii() throws IOException {
        ExceptionDemo demo = new ExceptionDemo();
        File tempFile = File.createTempFile("UTF-8-demo", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("This has a non-ASCII character: ©");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            demo.unhappyMethod(reader);
        }
    }

    @Test
    public void testUnhappyMethodWithAscii() throws IOException {
        ExceptionDemo demo = new ExceptionDemo();
        File tempFile = File.createTempFile("UTF-8-demo", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Only ASCII characters here");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            demo.unhappyMethod(reader);  // Should not throw exception
        }
    }
}