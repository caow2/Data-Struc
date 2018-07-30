package stackqueue;

import java.util.LinkedList;

//Animal Shelter operates on first in first out basis
//People can select whether to adopt the oldest animal or whether they prefer cat or dog - and get the oldest corresponding animal
public class AnimalShelter {
    public static void main(String[] args) {
        Shelter s = new Shelter();
        s.enqueue(AnimalType.DOG);
        s.enqueue(AnimalType.DOG);
        s.enqueue(AnimalType.DOG);
        s.enqueue(AnimalType.CAT);
        s.enqueue(AnimalType.DOG);
        System.out.println(s.dequeueAny());
        System.out.println(s.dequeueAny());
        System.out.println(s.dequeueCat());
        System.out.println(s.dequeueAny());
        System.out.println(s.dequeueDog());
        System.out.println(s.dequeueAny());
        System.out.println(s.dequeueAny());

    }

    /*
     * 1st Approach - can have 2 separate LL; a queue for cats and a queue for dogs, but how to differentiate which came first?
     * Their orders are independent of each other
     *
     * 2nd Approach - have one LL for both cats and dogs. This maintains the proper ordering of animals that came into the shelter
     * However, the order of seniority in animals might be cat, cat, cat, cat, ..., dog
     * If a person wants a dog, will have to look through the whole LL if the queue is unbalanced
     * (having lots of cats and then a dog at the very end).
     * dequeueAny will be O(1), but dequeueCat/Dog may be O(N) in worst case
     * enqueue will be O(1) assuming LL has reference to tail -> just append to end
     *
     * 3rd Approach - Have a separate data struc for animal that has a field for type of animal
     * and also the order that the animal came in
     * i.e. the first animal has order 0, next is order 1, etc.
     * Eventually, if order hits the integer cap, may have to reset since it will just continuously increment
     * dequeueAny and dequeueCat/Dog will be O(1) - just compare order when dequeueCat/Dog
     */
    public static enum AnimalType {
        DOG, CAT;
    }

    public static class Animal {
        AnimalType type;
        int order;

        public Animal(AnimalType type, int order) {
            this.type = type;
            this.order = order;
        }

        public String toString() {
            return type.toString();
        }
    }

    public static class Shelter {
        LinkedList<Animal> cats = new LinkedList<Animal>();
        LinkedList<Animal> dogs = new LinkedList<Animal>();
        int order = 0;

        public Shelter() {
        }

        public void enqueue(AnimalType animal) {
            Animal a = new Animal(animal, order++);
            if (animal == AnimalType.CAT)
                cats.addLast(a);
            else if (animal == AnimalType.DOG)
                dogs.addLast(a);
        }

        public Animal dequeueAny() {
            Animal a = null;

            //check if any of them are empty
            if (cats.isEmpty() && dogs.isEmpty())
                return a; //null
            else if (cats.isEmpty())
                return dogs.removeFirst();
            else if (dogs.isEmpty())
                return cats.removeFirst();

            if (cats.getFirst().order < dogs.getFirst().order)
                a = cats.removeFirst();
            else
                a = dogs.removeFirst();
            return a;
        }

        public Animal dequeueCat() {
            return cats.removeFirst();
        }

        public Animal dequeueDog() {
            return dogs.removeFirst();
        }
    }
}
