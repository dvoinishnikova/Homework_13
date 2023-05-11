package utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FilePathUtils {
    public static Path getFilePath(String fileName) throws URISyntaxException {
        ClassLoader classLoader = FilePathUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return Paths.get(Objects.requireNonNull(resource).toURI());
    }
}