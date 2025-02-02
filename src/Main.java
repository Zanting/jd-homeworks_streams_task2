import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                names.get(new Random().nextInt(names.size())),
                families.get(new Random().nextInt(families.size())),
                new Random().nextInt(100),
                Sex.values()[new Random().nextInt(Sex.values().length)],
                Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countPersonsUnder18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        List<String> listOfConscripts = persons.stream()
                .filter(x -> x.getSex().toString() == "MAN")
                .filter(x -> (x.getAge() >= 18 && x.getAge() < 27))
                .map(x -> x.getFamily().toString())
                .collect(Collectors.toList());

        List<Person> listAbleBodiedPeople = persons.stream()
                .filter(x -> x.getEducation().toString() == "HIGHER")
                .filter(x -> (x.getSex().toString() == "MAN" && x.getAge() >= 18 && x.getAge() < 60) || (x.getSex().toString() == "WOMAN" && x.getAge() >= 18 && x.getAge() < 65))
                .sorted(Comparator.comparing(x -> x.getFamily().toString()))
                .collect(Collectors.toList());

        System.out.println("Колличество несовершеннолетних: " + countPersonsUnder18);
        System.out.println("Количество призывников: " + listOfConscripts.size());
        System.out.println("Работоспособные люди с высшим образованием: " + listAbleBodiedPeople.size());
    }
}