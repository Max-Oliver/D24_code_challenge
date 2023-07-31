package d24.challenge.max.util;

import java.io.IOException;
import java.util.Objects;

public class ClassLoader {

    public String loader(String fileName) throws IOException {
        var classLoader = getClass().getClassLoader();
        return new String(Objects.requireNonNull(classLoader.getResourceAsStream(fileName)).readAllBytes());
    }
}
