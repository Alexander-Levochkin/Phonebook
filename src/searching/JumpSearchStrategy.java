package searching;

import creating.Person;

import java.time.Duration;
import java.util.List;

public class JumpSearchStrategy implements SearchStrategy<Person> {

    @Override
    public SearchResult search(List<Person> directoryList, List<String> findList) {
        final long start = System.currentTimeMillis();

        int found = 0;
        for (String person : findList) {
            int current = 0, previous = 0;
            final int last = directoryList.size()-1;
            final int step = (int) Math.sqrt(directoryList.size());

            while (directoryList.get(current).name().compareTo(person) < 0) {
                if (current == last) break;
                previous = current;
                current = Math.min(current + step, last);
            }

            while (previous <= current) {
                if (directoryList.get(current).name().equals(person)) {
                    found++;
                    break;
                }
                current--;
            }
        }

        final long end = System.currentTimeMillis();
        return new SearchResult(found, findList.size(), Duration.ofMillis(end-start));
    }
}
