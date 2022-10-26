package searching;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class HashMapSearch {

    private HashMap<String, String> directory;

    public HashMapSearch(HashMap<String, String> directory) {
        this.directory = directory;
    }

    public SearchResult search(List<String> findList) {
        final long start = System.currentTimeMillis();

        int found = 0;
        for (String element : findList) {
            //search
            if (directory.containsKey(element)) found++;
        }

        final long end = System.currentTimeMillis();
        return new SearchResult(found, findList.size(), Duration.ofMillis(end-start));
    }

    public HashMap<String, String> getDirectory() {
        return directory;
    }

    public void setDirectory(HashMap<String, String> directory) {
        this.directory = directory;
    }
}
