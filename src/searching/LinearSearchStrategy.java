package searching;

import creating.Person;

import java.time.Duration;
import java.util.List;

public class LinearSearchStrategy implements SearchStrategy<Person> {

    @Override
    public SearchResult search(List<Person> directoryList, List<String> findList) {
        final long start = System.currentTimeMillis();

        int found = 0;
        for (String s : findList) {
            for (Person person : directoryList) {
                if (person.name().equals(s)) {
                    found++;
                    break;
                }
            }
        }

        final long end = System.currentTimeMillis();
        return new SearchResult(found, findList.size(), Duration.ofMillis(end-start));
    }
}
