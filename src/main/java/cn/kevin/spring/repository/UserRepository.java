package cn.kevin.spring.repository;

import cn.kevin.spring.domain.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * created by yongkang.zhang
 * added at 2018/1/5
 */
@Repository
public class UserRepository {

    private static List<User> userList = List.of(
            new User(1, "张三", 15), new User(2, "李四", 16),
            new User(3, "王五", 17)

    );

    public Flux<User> listAll() {
        return Flux.fromIterable(userList);
    }

    public Mono<User> getById(Integer id) {
        return Mono.fromCallable(() -> userList.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst().orElse(null));
    }

    public void save(User user) {
        userList.add(user);
    }

}
