package cn.kevin.mybatis;

import cn.kevin.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yongkang.zhang
 * created at 21/11/2018
 */
@Mapper
public interface UserMapper {

    /**
     * 还是需要column, 2.0版本的需要使用inject columns by hint
     * 3.0上说直接支持，但maven库里只查到了2.x
     *
     * @param user 要保存的user对象
     * @return affected rows
     */
    @Insert("insert into user(id, name, sex) values (#{id}, #{name}, #{sex})")
    int insert(User user);

    @Select("select id, name, sex from user")
    List<User> selectAll();
}
