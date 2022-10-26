package sorting;

import creating.Person;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BubbleSort implements SortStrategy<Person> {

    @Override
    public SortResult sort(List<Person> directory, long maxTime) {
        final long start = System.currentTimeMillis();

        //sort directory in alphabetic order
        for (int i = 0; i < directory.size() - 1; i++)
            for (int j = 0; j < directory.size() - i - 1; j++)
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    Collections.swap(directory, j, j + 1);

                    // Stops if sorting time takes too long
                    long sortingTime = System.currentTimeMillis() - start;
                    if (sortingTime > maxTime) {
                        return new SortResult(Duration.ofMillis(sortingTime), this, true);
                    }
                }

        final long end = System.currentTimeMillis();
        return new SortResult(Duration.ofMillis(end-start), this, false);
    }
}
