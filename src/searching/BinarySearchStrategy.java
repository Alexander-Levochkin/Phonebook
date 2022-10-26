package searching;

import creating.Person;

import java.time.Duration;
import java.util.List;

public class BinarySearchStrategy implements SearchStrategy<Person> {

    @Override
    public SearchResult search(List<Person> directoryList, List<String> findList) {
        final long start = System.currentTimeMillis();

        int found = 0;
        for (String element : findList) {
            //search
            int left = 0;
            int right = directoryList.size()-1;

            while (left <= right) {
                int middle = (right+left) / 2;
                if (directoryList.get(middle).name().equals(element)) {
                    found++;
                    break;
                } else if (directoryList.get(middle).name().compareTo(element) < 0) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        final long end = System.currentTimeMillis();
        return new SearchResult(found, findList.size(), Duration.ofMillis(end-start));
    }
}
