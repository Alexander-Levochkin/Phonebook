package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort<T> {
    private final List<T> directory = new ArrayList<>();
    private boolean isFailed = false;
    private SortStrategy<T> sortStrategy;

    public Sort(List<T> directory, SortStrategy<T> sortStrategy) {
        this.directory.addAll(directory);
        this.sortStrategy = sortStrategy;
    }

    public SortResult sort(long maxTime) {
        final SortResult sort = sortStrategy.sort(directory, maxTime);
        if (sort.isFailed()) this.isFailed = true;
        return sort;
    }

    public List<T> getDirectory() {
        return directory;
    }

    public void setDirectory(List<T> directory) {
        Collections.copy(this.directory, directory);
    }

    public boolean isFailed() {
        return isFailed;
    }

    public SortStrategy<T> getSortStrategy() {
        return sortStrategy;
    }

    public void setSortStrategy(SortStrategy<T> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
}
