package cn.kevin.validation;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@Data
public class PersonForm {

    @NotNull(message = "姓名不可为空")
    private String name;

    @Min(value = 9, message = "最小年龄应该大于9")
    @Max(value = 70, message = "最大年龄应该小于70")
    private Integer age;
}
