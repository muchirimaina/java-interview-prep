import java.util.LinkedList;
import java.util.Queue;

class Animal {
    int id;         // could be any identifier
    boolean isDog;  // true = dog, false = cat
    int timestamp;  // tracks arrival order

    Animal(int id, boolean isDog, int timestamp) {
        this.id = id;
        this.isDog = isDog;
        this.timestamp = timestamp;
    }
}

public class AnimalShelterOptimized {

    private Queue<Animal> dogs = new LinkedList<>();
    private Queue<Animal> cats = new LinkedList<>();
    private int timestamp = 0;  // increments with each enqueue

    // Enqueue a new animal
    public void enqueue(int id, boolean isDog) {
        Animal animal = new Animal(id, isDog, timestamp++);
        if (isDog) {
            dogs.add(animal);
        } else {
            cats.add(animal);
        }
    }

    // Dequeue the oldest animal
    public int dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) throw new RuntimeException("No animals available");
        if (dogs.isEmpty()) return dequeueCat();
        if (cats.isEmpty()) return dequeueDog();

        // Compare timestamps
        if (dogs.peek().timestamp < cats.peek().timestamp) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    // Dequeue the oldest dog
    public int dequeueDog() {
        if (dogs.isEmpty()) throw new RuntimeException("No dogs available");
        return dogs.poll().id;
    }

    // Dequeue the oldest cat
    public int dequeueCat() {
        if (cats.isEmpty()) throw new RuntimeException("No cats available");
        return cats.poll().id;
    }

    public static void main(String[] args) {
        AnimalShelterOptimized shelter = new AnimalShelterOptimized();

        shelter.enqueue(4, true);   // Dog
        shelter.enqueue(7, false);  // Cat
        shelter.enqueue(9, true);   // Dog
        shelter.enqueue(87, true);  // Dog
        shelter.enqueue(100, true); // Dog

        int result1 = shelter.dequeueCat(); // should be 7
        int result2 = shelter.dequeueDog(); // should be 4
        int result3 = shelter.dequeueAny(); // should be 9 (oldest remaining)

        System.out.println("Should be a cat :" + result1);
        System.out.println("Should be a Dog :" + result2);
        System.out.println("Can be any :" + result3);
    }
}