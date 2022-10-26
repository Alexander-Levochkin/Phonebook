package creating;

public record Person (String name, String number) implements Comparable<Person> {
    @Override
    public String toString() {
        return name + " " + number;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }
}
