package searching;

import java.util.List;

@FunctionalInterface
public interface SearchStrategy<T> {
    SearchResult search(List<T> directoryList, List<String> findList);
}
