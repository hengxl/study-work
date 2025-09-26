package hxl.sexEnum;

import lombok.Getter;

@Getter
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
