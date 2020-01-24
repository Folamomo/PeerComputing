package computinglib;

import java.io.IOException;
import java.util.LinkedList;

// Mechanizm result type wydał mi się zbędny, więc wszystko na listach i kolekcjach
// Przy użyciu Task_Primes.run() liczby pierwsze zapisują się w results i zmienia się isDone na true
// Task_Primes operuje na znalezionych wcześniej liczbach pierwszych zapisanych w dependencies jako Taski
// Jeżeli jest to pierwsze wywołanie to liczy sito od 2 do n
// Jeżeli jest to jakiś odległy zakres i jeszcze nie ma wszystkich potrzebnych wyników to też działa,
// tak raczej brutal force ale żeby było poprawnie niestety musi
// saveToFile po prostu dopisuje rzeczy do istniejącego pliku


public class Main {

    public static void main(String[] args) throws IOException {
        Task_Primes task = new Task_Primes(1, 2, 5, null);
        LinkedList<Task> dep = new LinkedList<Task>();
        task.run();
        dep.add(task);
        Task_Primes task2 = new Task_Primes(1, 30, 80, dep);
        task2.run();
        task2.saveToFile("path");
    }
}
