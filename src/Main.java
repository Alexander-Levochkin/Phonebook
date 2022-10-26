import creating.Converter;
import creating.CreatingResult;
import creating.Person;
import searching.*;
import sorting.BubbleSort;
import sorting.QuickSort;
import sorting.Sort;
import sorting.SortResult;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final Path DIRECTORY = Path.of("C:\\Users\\Alexandr\\Desktop\\directory.txt");
    private static final Path FIND_LIST = Path.of("C:\\Users\\Alexandr\\Desktop\\find.txt");
    private static final Path SMALL_DIRECTORY = Path.of("C:\\Users\\Alexandr\\Desktop\\small_directory.txt");
    private static final Path SMALL_FIND_LIST = Path.of("C:\\Users\\Alexandr\\Desktop\\small_find.txt");

    public static void main(String[] args) {
        Converter converter = new Converter(DIRECTORY, FIND_LIST);
//        Converter converter = new Converter(SMALL_DIRECTORY, SMALL_FIND_LIST);
        List<Person> directoryList = converter.scanDirectoryToList();
        List<String> findListList = converter.scanFindList();
        HashMap<String, String> directoryHashMap = converter.scanDirectoryToHashMap();

        SearchResult linearSearchResult = performLinearSearch(directoryList, findListList);
        SortResult bubbleSortResult = performBubbleSortAndJumpSearch(directoryList, findListList, linearSearchResult);
        SortResult quickSortResult = performQuickSortAndBinarySearch(directoryList, findListList);
        SearchResult hashTableSearchResult = createHashTableAndPerformSearch(
                directoryHashMap, findListList, converter.getMapCreatingResult());
    }

    private static SearchResult createHashTableAndPerformSearch(
            HashMap<String, String> map, List<String> findList, CreatingResult creatingResult) {
        System.out.println("Start searching (hash table)...");

        HashMapSearch hashSearch = new HashMapSearch(map);
        SearchResult hashSearchResult = hashSearch.search(findList);

        System.out.println(new SearchResult(hashSearchResult.getFound(), hashSearchResult.getOutOf(),
                creatingResult.duration().plus(hashSearchResult.getDuration())));
        System.out.println(creatingResult);
        System.out.println(hashSearchResult.durationInfo);

        return hashSearchResult;
    }

    private static SortResult performQuickSortAndBinarySearch(List<Person> directoryList, List<String> findList) {
        System.out.println("Start searching (quick sort + binary search)...");

        int maxTimeInSeconds = 20;
        Sort<Person> quickSort = new Sort<>(directoryList, new QuickSort());
        SortResult sortResult = quickSort.sort(maxTimeInSeconds);

        Search<Person> binarySearch = new Search<>(directoryList, new BinarySearchStrategy());
        SearchResult binarySearchResult = binarySearch.searchFor(findList);

        System.out.println(new SearchResult(binarySearchResult.getFound(), binarySearchResult.getOutOf(),
                sortResult.duration().plus(binarySearchResult.getDuration())));
        System.out.println(sortResult);
        System.out.println(binarySearchResult.durationInfo);
        System.out.println();

        return sortResult;
    }

    private static SortResult performBubbleSortAndJumpSearch(
            List<Person> directoryList, List<String> findList, SearchResult linearSearchResult) {
        System.out.println("Start searching (bubble sort + jump search)...");

        Sort<Person> bubbleSort = new Sort<>(directoryList, new BubbleSort());
        SortResult sortResult = bubbleSort.sort(linearSearchResult.getLengthInMillis()*10);

        if (bubbleSort.isFailed()) {
            System.out.println(new SearchResult(linearSearchResult.getFound(), linearSearchResult.getOutOf(),
                    sortResult.duration().plus(linearSearchResult.getDuration())));
            System.out.println(sortResult + " - STOPPED, moved to linear search");
            System.out.println(linearSearchResult.durationInfo);
        } else {
            Search<Person> jumpSearch = new Search<>(directoryList, new JumpSearchStrategy());
            SearchResult jumpSearchResult = jumpSearch.searchFor(findList);
            System.out.println(new SearchResult(jumpSearchResult.getFound(), jumpSearchResult.getOutOf(),
                    sortResult.duration().plus(jumpSearchResult.getDuration())));
            System.out.println(sortResult);
            System.out.println(jumpSearchResult.durationInfo);
        }
        System.out.println();

        return sortResult;
    }

    private static SearchResult performLinearSearch(List<Person> directoryList, List<String> findList) {
        System.out.println("Start searching (linear search)...");
        Search<Person> linearSearch = new Search<>(directoryList, new LinearSearchStrategy());
        SearchResult linearSearchResult = linearSearch.searchFor(findList);
        System.out.println(linearSearchResult);
        System.out.println();
        return linearSearchResult;
    }
}
