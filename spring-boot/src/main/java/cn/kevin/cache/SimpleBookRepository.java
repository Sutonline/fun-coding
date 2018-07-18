package cn.kevin.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author yongkang.zhang
 * created at 18/07/2018
 */
@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable(value = "books", key = "#isbn")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
