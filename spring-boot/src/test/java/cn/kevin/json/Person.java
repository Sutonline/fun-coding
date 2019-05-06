package cn.kevin.json;

import lombok.Data;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 2018-12-27
 */
@Setter
public class Person {

	private String name;

	private String aaName;

	private Integer age;

	private Date birthday;

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return new SimpleDateFormat("yyyy-mm-dd").format(birthday) + "/t-a=cd";
	}

	public Integer getAge() {
		return age;
	}

	public String getAaName() {
		return aaName;
	}
}
