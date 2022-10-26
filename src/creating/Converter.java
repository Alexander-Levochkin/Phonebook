package creating;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
    private final Path directory;
    private final Path findList;
    private CreatingResult mapCreatingResult;

    public Converter(Path directory, Path findList) {
        this.directory = directory;
        this.findList = findList;
    }

    public List<Person> scanDirectoryToList() {
        List<Person> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(directory)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" ", 2);
                result.add(new Person(data[1], data[0]));
            }
        } catch (IOException e) {
            throw new RuntimeException("scanDirectoryToList failed", e);
        }

        return result;
    }

    public HashMap<String, String> scanDirectoryToHashMap() {
        try (Stream<String> lines = Files.lines(directory)) {
            long start = System.currentTimeMillis();

            HashMap<String, String> directoryHashMap = new HashMap<>(lines.collect(Collectors.toUnmodifiableMap(
                    s -> s.split(" ", 2)[1],
                    s -> s.split(" ", 2)[0])));

            long end = System.currentTimeMillis();
            mapCreatingResult = new CreatingResult(Duration.ofMillis(end-start));

            return directoryHashMap;
        } catch (IOException e) {
            throw new RuntimeException("scanDirectoryToHashMap failed", e);
        }
    }

    public List<String> scanFindList() {
        try {
            return Files.readAllLines(findList);
        } catch (IOException e) {
            throw new RuntimeException("scanFindList failed", e);
        }
    }

    public CreatingResult getMapCreatingResult() {
        return mapCreatingResult;
    }
}
