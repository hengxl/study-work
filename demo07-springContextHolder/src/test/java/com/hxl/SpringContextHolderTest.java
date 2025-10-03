package com.hxl;

import com.hxl.bean.Student;
import com.hxl.bean.Teacher;
import com.hxl.holder.SpringContextEasyHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
public class SpringContextHolderTest {

    @Test
    public void test01() {
        Supplier<Student> stu =
                SpringContextEasyHolder.getSupplier(Student.class);

        Student student = stu.get();
        System.out.println(student);
        System.out.println("-----------------------------------");


        Supplier<Teacher> teach =
                SpringContextEasyHolder.getSupplier(Teacher.class);
        Teacher teacher = teach.get();

        System.out.println(teacher);
        System.out.println("--------------------------------------");
    }
}
