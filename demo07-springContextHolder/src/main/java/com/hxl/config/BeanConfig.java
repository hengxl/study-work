package com.hxl.config;

import com.hxl.domain.Student;
import com.hxl.domain.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Student getStuBean() {
        return new Student()
                .setSName("学生")
                .setSAge(18);
    }

    @Bean
    public Teacher getTeaBean() {
        return new Teacher()
                .setTName("老师")
                .setTAge(28);
    }
}
