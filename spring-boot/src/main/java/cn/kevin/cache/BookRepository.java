package cn.kevin.cache;

/**
 * @author yongkang.zhang
 * created at 18/07/2018
 */
public interface BookRepository {

    Book getByIsbn(String isbn);
}
