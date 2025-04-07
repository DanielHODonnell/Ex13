import java.io.*;

public class ExceptionDemo {
    public void someMethod() {
        try {
            anotherMethod();
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public void anotherMethod() throws IOException {
        File tempFile = File.createTempFile("UTF-8-demo", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Sample text with non-ASCII character: ñ");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            unhappyMethod(reader);
        }
    }

    public void unhappyMethod(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if ((int) c > 127) {
                    throw new IOException("Non-ASCII character found: " + c);
                }
            }
        }
    }

    public static void main(String[] args) {
        ExceptionDemo et = new ExceptionDemo();
        et.someMethod();
    }
}