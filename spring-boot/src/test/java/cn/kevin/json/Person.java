package cn.kevin.json;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 2018-12-27
 */
@Data
public class Person {

	private String name;

	private Integer age;

	private Date birthday;

	public String getBirthday() {
		return new SimpleDateFormat("yyyy-mm-dd").format(birthday) + "/t-a=cd";
	}
}
