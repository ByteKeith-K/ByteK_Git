import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public class ZooManagement {
    public static void main(String[] args) {
        ZooManager zooManager = new ZooManager("ByteK Zoo");
        zooManager.initializeSampleData();
        zooManager.run();
    }
}

interface SoundMaker {
    void makeSound();
}

abstract class Animal implements SoundMaker {
    private static int nextId = 1;

    protected final int id;
    protected String name;
    protected LocalDate birthDate;
    protected String habitat;
    protected boolean isEndangered;

    public Animal(String name, LocalDate birthDate, String habitat, boolean isEndangered) {
        this.id = nextId++;
        this.name = name;
        this.birthDate = birthDate;
        this.habitat = habitat;
        this.isEndangered = isEndangered;
    }

    public abstract String getSpecies();

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getHabitat() { return habitat; }
    public boolean isEndangered() { return isEndangered; }

    @Override
    public String toString() {
        return String.format("%s (ID: %d) - %s, Age: %d, Habitat: %s, Endangered: %s",
                name, id, getSpecies(), getAge(), habitat, isEndangered ? "Yes" : "No");
    }
}

class Mammal extends Animal {
    private boolean hasFur;
    private int gestationPeriod;

    public Mammal(String name, LocalDate birthDate, String habitat,
                  boolean isEndangered, boolean hasFur, int gestationPeriod) {
        super(name, birthDate, habitat, isEndangered);
        this.hasFur = hasFur;
        this.gestationPeriod = gestationPeriod;
    }

    @Override
    public String getSpecies() {
        return "Mammal";
    }

    @Override
    public void makeSound() {
        System.out.println(name + " makes a mammalian sound!");
    }

    public boolean hasFur() { return hasFur; }
    public int getGestationPeriod() { return gestationPeriod; }

    @Override
    public String toString() {
        return super.toString() + String.format(", Fur: %s, Gestation: %d months",
                hasFur ? "Yes" : "No", gestationPeriod);
    }
}

class Bird extends Animal {
    private double wingspan;
    private boolean canFly;

    public Bird(String name, LocalDate birthDate, String habitat,
                boolean isEndangered, double wingspan, boolean canFly) {
        super(name, birthDate, habitat, isEndangered);
        this.wingspan = wingspan;
        this.canFly = canFly;
    }

    @Override
    public String getSpecies() {
        return "Bird";
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps or sings!");
    }

    public double getWingspan() { return wingspan; }
    public boolean canFly() { return canFly; }

    @Override
    public String toString() {
        return super.toString() + String.format(", Wingspan: %.1f cm, Can fly: %s",
                wingspan, canFly ? "Yes" : "No");
    }
}

class Reptile extends Animal {
    private boolean isVenomous;
    private double bodyTemperature;

    public Reptile(String name, LocalDate birthDate, String habitat,
                   boolean isEndangered, boolean isVenomous, double bodyTemperature) {
        super(name, birthDate, habitat, isEndangered);
        this.isVenomous = isVenomous;
        this.bodyTemperature = bodyTemperature;
    }

    @Override
    public String getSpecies() {
        return "Reptile";
    }

    @Override
    public void makeSound() {
        System.out.println(name + " hisses or makes a reptilian sound!");
    }

    public boolean isVenomous() { return isVenomous; }
    public double getBodyTemperature() { return bodyTemperature; }

    @Override
    public String toString() {
        return super.toString() + String.format(", Venomous: %s, Body temp: %.1f°C",
                isVenomous ? "Yes" : "No", bodyTemperature);
    }
}

class Lion extends Mammal {
    private double maneSize;

    public Lion(String name, LocalDate birthDate, boolean isEndangered, double maneSize) {
        super(name, birthDate, "Savanna", isEndangered, true, 4);
        this.maneSize = maneSize;
    }

    @Override
    public String getSpecies() {
        return "Lion";
    }

    @Override
    public void makeSound() {
        System.out.println(name + " roars loudly!");
    }

    public double getManeSize() { return maneSize; }

    @Override
    public String toString() {
        return super.toString() + String.format(", Mane size: %.1f", maneSize);
    }
}

class Penguin extends Bird {
    private double swimmingSpeed;

    public Penguin(String name, LocalDate birthDate, boolean isEndangered, double swimmingSpeed) {
        super(name, birthDate, "Antarctic", isEndangered, 30.0, false);
        this.swimmingSpeed = swimmingSpeed;
    }

    @Override
    public String getSpecies() {
        return "Penguin";
    }

    @Override
    public void makeSound() {
        System.out.println(name + " makes a honking sound!");
    }

    public double getSwimmingSpeed() { return swimmingSpeed; }

    @Override
    public String toString() {
        return super.toString() + String.format(", Swimming speed: %.1f km/h", swimmingSpeed);
    }
}

class Enclosure {
    private final String id;
    private String name;
    private String habitatType;
    private double area;
    private List<Animal> animals = new ArrayList<>();

    public Enclosure(String id, String name, String habitatType, double area) {
        this.id = id;
        this.name = name;
        this.habitatType = habitatType;
        this.area = area;
    }

    public void addAnimal(Animal animal) {
        if (animal.getHabitat().equalsIgnoreCase(habitatType)) {
            animals.add(animal);
            System.out.println(animal.getName() + " added to " + name);
        } else {
            System.out.println("Cannot add " + animal.getName() +
                    " - habitat mismatch (" + animal.getHabitat() +
                    " vs enclosure's " + habitatType + ")");
        }
    }

    public void removeAnimal(int animalId) {
        animals.removeIf(animal -> animal.getId() == animalId);
    }

    public void displayAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals in this enclosure.");
            return;
        }

        System.out.println("\nAnimals in " + name + ":");
        animals.forEach(System.out::println);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getHabitatType() { return habitatType; }
    public double getArea() { return area; }
    public List<Animal> getAnimals() { return new ArrayList<>(animals); }

    @Override
    public String toString() {
        return String.format("Enclosure %s: %s (%s habitat, %.1f sqm, %d animals)",
                id, name, habitatType, area, animals.size());
    }
}

class ZooManager {
    private String zooName;
    private List<Animal> allAnimals = new ArrayList<>();
    private Map<String, Enclosure> enclosures = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public ZooManager(String zooName) {
        this.zooName = zooName;
    }

    public void initializeSampleData() {
        allAnimals.add(new Lion("Simba", LocalDate.of(2018, 5, 15), false, 25.5));
        allAnimals.add(new Penguin("Pingu", LocalDate.of(2020, 2, 20), true, 8.2));
        allAnimals.add(new Reptile("Slither", LocalDate.of(2015, 7, 10), "Jungle", false, true, 28.5));
        allAnimals.add(new Mammal("Elephant", LocalDate.of(2005, 1, 30), "Savanna", true, false, 22));

        enclosures.put("SAV1", new Enclosure("SAV1", "Savanna Exhibit", "Savanna", 5000));
        enclosures.put("ANT1", new Enclosure("ANT1", "Antarctic Zone", "Antarctic", 2000));
        enclosures.put("JUN1", new Enclosure("JUN1", "Jungle House", "Jungle", 3000));

        for (Animal animal : allAnimals) {
            for (Enclosure enclosure : enclosures.values()) {
                if (enclosure.getHabitatType().equalsIgnoreCase(animal.getHabitat())) {
                    enclosure.addAnimal(animal);
                    break;
                }
            }
        }
    }

    public void run() {
        System.out.println("=== Welcome to " + zooName + " Management System ===");

        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    manageAnimals();
                    break;
                case 2:
                    manageEnclosures();
                    break;
                case 3:
                    animalReports();
                    break;
                case 4:
                    makeAnimalsSound();
                    break;
                case 5:
                    System.out.println("Exiting " + zooName + " Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Manage Animals");
        System.out.println("2. Manage Enclosures");
        System.out.println("3. Animal Reports");
        System.out.println("4. Make Animals Sound");
        System.out.println("5. Exit");
    }

    private void manageAnimals() {
        while (true) {
            System.out.println("\nAnimal Management:");
            System.out.println("1. List All Animals");
            System.out.println("2. Add New Animal");
            System.out.println("3. View Animal Details");
            System.out.println("4. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    listAllAnimals();
                    break;
                case 2:
                    addNewAnimal();
                    break;
                case 3:
                    viewAnimalDetails();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllAnimals() {
        if (allAnimals.isEmpty()) {
            System.out.println("No animals in the zoo.");
            return;
        }

        System.out.println("\nAll Animals in " + zooName + ":");
        allAnimals.forEach(System.out::println);
    }

    private void addNewAnimal() {
        System.out.println("\nAdd New Animal");
        System.out.println("1. Mammal");
        System.out.println("2. Bird");
        System.out.println("3. Reptile");
        System.out.println("4. Lion");
        System.out.println("5. Penguin");
        System.out.println("6. Cancel");

        int typeChoice = getIntInput("Select animal type: ");
        if (typeChoice == 6) return;

        String name = getStringInput("Enter animal name: ");
        int birthYear = getIntInput("Enter birth year: ");
        int birthMonth = getIntInput("Enter birth month (1-12): ");
        int birthDay = getIntInput("Enter birth day: ");
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

        boolean isEndangered = getYesNoInput("Is the animal endangered? (y/n): ");

        Animal newAnimal = null;

        try {
            switch (typeChoice) {
                case 1:
                    String habitat = getStringInput("Enter habitat: ");
                    boolean hasFur = getYesNoInput("Does it have fur? (y/n): ");
                    int gestation = getIntInput("Enter gestation period (months): ");
                    newAnimal = new Mammal(name, birthDate, habitat, isEndangered, hasFur, gestation);
                    break;
                case 2:
                    habitat = getStringInput("Enter habitat: ");
                    double wingspan = getDoubleInput("Enter wingspan (cm): ");
                    boolean canFly = getYesNoInput("Can it fly? (y/n): ");
                    newAnimal = new Bird(name, birthDate, habitat, isEndangered, wingspan, canFly);
                    break;
                case 3:
                    habitat = getStringInput("Enter habitat: ");
                    boolean isVenomous = getYesNoInput("Is it venomous? (y/n): ");
                    double temp = getDoubleInput("Enter body temperature (°C): ");
                    newAnimal = new Reptile(name, birthDate, habitat, isEndangered, isVenomous, temp);
                    break;
                case 4:
                    double maneSize = getDoubleInput("Enter mane size (0 if female): ");
                    newAnimal = new Lion(name, birthDate, isEndangered, maneSize);
                    break;
                case 5:
                    double swimSpeed = getDoubleInput("Enter swimming speed (km/h): ");
                    newAnimal = new Penguin(name, birthDate, isEndangered, swimSpeed);
                    break;
                default:
                    System.out.println("Invalid animal type.");
                    return;
            }

            allAnimals.add(newAnimal);
            System.out.println("Successfully added new animal: " + newAnimal);

            for (Enclosure enclosure : enclosures.values()) {
                if (enclosure.getHabitatType().equalsIgnoreCase(newAnimal.getHabitat())) {
                    enclosure.addAnimal(newAnimal);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error creating animal: " + e.getMessage());
        }
    }

    private void viewAnimalDetails() {
        int id = getIntInput("Enter animal ID: ");
        Optional<Animal> animal = allAnimals.stream().filter(a -> a.getId() == id).findFirst();

        if (animal.isPresent()) {
            System.out.println("\nAnimal Details:");
            System.out.println(animal.get());

            Optional<Enclosure> enclosure = enclosures.values().stream()
                    .filter(e -> e.getAnimals().contains(animal.get()))
                    .findFirst();

            if (enclosure.isPresent()) {
                System.out.println("\nLocated in: " + enclosure.get().getName());
            } else {
                System.out.println("\nNot currently assigned to an enclosure.");
            }
        } else {
            System.out.println("Animal with ID " + id + " not found.");
        }
    }

    private void manageEnclosures() {
        while (true) {
            System.out.println("\nEnclosure Management:");
            System.out.println("1. List All Enclosures");
            System.out.println("2. View Enclosure Details");
            System.out.println("3. Create New Enclosure");
            System.out.println("4. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    listAllEnclosures();
                    break;
                case 2:
                    viewEnclosureDetails();
                    break;
                case 3:
                    createEnclosure();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllEnclosures() {
        if (enclosures.isEmpty()) {
            System.out.println("No enclosures in the zoo.");
            return;
        }

        System.out.println("\nAll Enclosures in " + zooName + ":");
        enclosures.values().forEach(System.out::println);
    }

    private void viewEnclosureDetails() {
        String id = getStringInput("Enter enclosure ID: ").toUpperCase();
        Enclosure enclosure = enclosures.get(id);

        if (enclosure != null) {
            System.out.println("\nEnclosure Details:");
            System.out.println(enclosure);
            enclosure.displayAnimals();
        } else {
            System.out.println("Enclosure with ID " + id + " not found.");
        }
    }

    private void createEnclosure() {
        System.out.println("\nCreate New Enclosure");
        String id = getStringInput("Enter enclosure ID: ").toUpperCase();

        if (enclosures.containsKey(id)) {
            System.out.println("Enclosure with ID " + id + " already exists.");
            return;
        }

        String name = getStringInput("Enter enclosure name: ");
        String habitat = getStringInput("Enter habitat type: ");
        double area = getDoubleInput("Enter area (square meters): ");

        Enclosure newEnclosure = new Enclosure(id, name, habitat, area);
        enclosures.put(id, newEnclosure);
        System.out.println("Successfully created new enclosure: " + newEnclosure);
    }

    private void animalReports() {
        System.out.println("\nAnimal Reports:");
        System.out.println("1. Endangered Animals Report");
        System.out.println("2. Animals by Habitat");
        System.out.println("3. Average Age by Species");
        System.out.println("4. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                endangeredAnimalsReport();
                break;
            case 2:
                animalsByHabitatReport();
                break;
            case 3:
                averageAgeBySpeciesReport();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void endangeredAnimalsReport() {
        long endangeredCount = allAnimals.stream().filter(Animal::isEndangered).count();
        System.out.println("\nEndangered Animals Report:");
        System.out.println("Total endangered animals: " + endangeredCount + " out of " + allAnimals.size());

        if (endangeredCount > 0) {
            System.out.println("\nList of endangered animals:");
            allAnimals.stream()
                    .filter(Animal::isEndangered)
                    .forEach(System.out::println);
        }
    }

    private void animalsByHabitatReport() {
        System.out.println("\nAnimals by Habitat Report:");

        Map<String, List<Animal>> animalsByHabitat = new HashMap<>();
        allAnimals.forEach(animal -> {
            animalsByHabitat.computeIfAbsent(animal.getHabitat(), k -> new ArrayList<>()).add(animal);
        });

        if (animalsByHabitat.isEmpty()) {
            System.out.println("No animals in the zoo.");
            return;
        }

        animalsByHabitat.forEach((habitat, animals) -> {
            System.out.println("\nHabitat: " + habitat);
            System.out.println("Number of animals: " + animals.size());
            animals.forEach(animal -> System.out.println("  " + animal.getName() + " (" + animal.getSpecies() + ")"));
        });
    }

    private void averageAgeBySpeciesReport() {
        System.out.println("\nAverage Age by Species Report:");

        Map<String, List<Animal>> animalsBySpecies = new HashMap<>();
        allAnimals.forEach(animal -> {
            animalsBySpecies.computeIfAbsent(animal.getSpecies(), k -> new ArrayList<>()).add(animal);
        });

        if (animalsBySpecies.isEmpty()) {
            System.out.println("No animals in the zoo.");
            return;
        }

        animalsBySpecies.forEach((species, animals) -> {
            double averageAge = animals.stream()
                    .mapToInt(Animal::getAge)
                    .average()
                    .orElse(0.0);

            System.out.printf("%s: %.1f years (from %d animals)%n",
                    species, averageAge, animals.size());
        });
    }

    private void makeAnimalsSound() {
        System.out.println("\nMaking animals sound:");
        allAnimals.forEach(Animal::makeSound);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Please enter 'y' or 'n'.");
        }
    }
}