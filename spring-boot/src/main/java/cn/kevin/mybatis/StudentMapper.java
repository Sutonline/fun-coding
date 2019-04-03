package cn.kevin.mybatis;

import cn.kevin.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author yongkang.zhang
 * created at 2019-03-16
 */
@Mapper
public interface StudentMapper {

	@Insert("insert into student(name) values (#{name})")
	int insert(Student student);

	@Update("update student set name = #{name} where id = #{id}")
	int update(Integer id, String name);

	@Select("select id, name from student where id = #{id}")
	Student listById(Integer id);

}
