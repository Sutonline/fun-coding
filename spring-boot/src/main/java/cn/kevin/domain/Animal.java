package cn.kevin.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@Data
public class Animal {

    @NotNull(message = "动物类型不可为空")
    private String type;

    @NotNull(message = "动物名称不可为空")
    private String name;
}
