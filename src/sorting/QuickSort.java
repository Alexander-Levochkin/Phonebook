package sorting;

import creating.Person;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class QuickSort implements SortStrategy<Person> {

    @Override
    public SortResult sort(List<Person> directory, long maxTime) {
        final long start = System.currentTimeMillis();

        recursiveSort(directory, 0, directory.size()-1);

        final long end = System.currentTimeMillis();
        return new SortResult(Duration.ofMillis(end-start), this, false);
    }

    private void recursiveSort(List<Person> directory, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(directory, begin, end);

            recursiveSort(directory, begin, partitionIndex-1);
            recursiveSort(directory, partitionIndex+1, end);
        }
    }

    private int partition(List<Person> directory, int begin, int end) {
        Person pivot = directory.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (directory.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(directory, i, j);
            }
        }

        Collections.swap(directory, i+1, end);

        return i+1;
    }
}
