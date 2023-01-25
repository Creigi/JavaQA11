import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.products.Book;
import ru.netology.products.Product;
import ru.netology.products.Smartphone;
import ru.netology.repositories.ProductRepository;

public class ProductTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "aaaaa FFFFF", 1000, "aaaaa", "FFFFF");
    Product book2 = new Book(5, "ababa FFFFF", 1800, "ababa", "FFFFF");
    Product book3 = new Book(77, "ssaass SSSSS", 500, "ssaass", "SSSSS");
    Product book4 = new Book(36, "bbbaaa QQQQQ", 400, "bbbaaa", "QQQQQ");
    Product book5 = new Book(44, "fffff WWWWW", 100, "fffff", "WWWWW");
    Product smartphone1 = new Smartphone(22, "aaaaa AAAAA", 10_000, "aaaaa", "AAAAA");
    Product smartphone2 = new Smartphone(45, "bbbbb AAAAA", 15_000, "bbbbb", "AAAAA");
    Product smartphone3 = new Smartphone(2, "aaabbb XXXXX", 12_000, "aaabbb", "XXXXX");
    Product smartphone4 = new Smartphone(96, "ssssss VVVVV", 12_000, "ssssss", "VVVVV");
    Product smartphone5 = new Smartphone(100, "ssssaa VVVVV", 18_000, "ssssaa", "VVVVV");

    @Test
    public void testManagerRepoAddingProducts() {
        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRepoRemoveById() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        repo.removeById(96);
        repo.removeById(77);
        repo.removeById(1);

        Product[] expected = {smartphone1, book2, smartphone2, smartphone3, book4, book5, smartphone5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testManagerSearchFewMatches() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        Product[] expected = {book2, book4};
        Product[] actual = manager.searchBy("ba");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testManagerSearchNoMatches() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("haha");

        Assertions.assertArrayEquals(expected, actual);
    }
}
