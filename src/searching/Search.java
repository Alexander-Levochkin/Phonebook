package searching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Search<T> {
    private final List<T> directory = new ArrayList<>();
    private SearchStrategy<T> searchStrategy;

    public Search(List<T> directory, SearchStrategy<T> searchStrategy) {
        this.directory.addAll(directory);
        this.searchStrategy = searchStrategy;
    }

    public SearchResult searchFor(List<String> findList) {
        return searchStrategy.search(directory, findList);
    }

    public List<T> getDirectory() {
        return directory;
    }

    public void setDirectory(List<T> directory) {
        Collections.copy(this.directory, directory);
    }

    public SearchStrategy<T> getSearchStrategy() {
        return searchStrategy;
    }

    public void setSearchStrategy(SearchStrategy<T> searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
}
