package com.hxl.base;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    // 假设 Author 和 Book 类已正确定义
    private static List<Author> getAuthors() {
        // 数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        // 书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

    /**
     * 测试性能
     * 先 distinct 后 filter
     * 先 filter 后 distinct
     */
    @Test
    public void testStream() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int r = random.nextInt(1000) + 1;
            list1.add(r);
            list2.add(r);
        }

        long begin = System.currentTimeMillis();

        // 先 distinct 再 filter : 慢一些
        list1.stream()
                .distinct()
                .filter(integer -> integer > 200)
                .forEach(System.out::println);
        long end = System.currentTimeMillis();

        System.out.println("time1:" + (end - begin));
        System.out.println();
        System.out.println();

        begin = System.currentTimeMillis();

        // 先 filter 再 distinct : 更快
        list2.stream()
                .filter(integer -> integer > 200)
                .forEach(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("time2:" + (end - begin));
    }

    @Test
    public void testStream1() {
        // 获取 作者集合
        List<Author> authors = getAuthors();
        System.out.println("------------------------------------------------------------------------------------------");

        // 先 filter 再 distinct 耗时更少
        long begin = System.currentTimeMillis();
        // 将集合转换成流
        authors.stream()
                .filter(author -> author.getAge() < 18) // 年龄小于18
                .distinct() // 去重
                .forEach(author -> System.out.println(author.getName())); // 打印作者姓名

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - begin));
        System.out.println("------------------------------------------------------------------------------------------");
    }

    /**
     * 获取Stream流对象
     */
    @Test
    public void testStream2() {
        Integer[] arr = new Integer[]{2, 5, 10, 3, 4, 4, 5, 2, 1, 10, 3, 5};

        // Arrays里的转换方法
        Stream<Integer> stream1 = Arrays.stream(arr);
        stream1.filter(i -> i > 2)
                .distinct()
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------------------------");

        // Stream的转换方法
        Stream<Integer> stream2 = Stream.of(arr);
        stream2.filter(i -> i > 2)
                .distinct()
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------------------------");

        // 双列集合
        Map<String, Integer> map = new HashMap<>();
        map.put("张三", 18);
        map.put("李四", 21);
        map.put("王五", 20);

        // 转换成单列集合后 可以调用stream方法
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        entries.stream()
//                .filter(entry -> entry.getValue() > 18)
//                .forEach(entry -> System.out.println(entry.getValue()));
        map.entrySet().stream()
                .filter(entry -> entry.getValue() > 18)
                .forEach(entry -> System.out.println(entry.getValue()));
    }

    /**
     * filter方法
     */
    @Test
    public void testStream3() {
        List<Author> authors = getAuthors();

        // 打印所有姓名长度大于 1 的作家的姓名
        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    /**
     * map方法
     */
    @Test
    public void testStream4() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));

        System.out.println("------------------------------------------------------------------------------------------");

        authors.stream()
                .distinct()
                .map(author -> author.getName())
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------------------------");

        authors.stream()
                .distinct()
                .map(Author::getName)
                .forEach(System.out::println);
    }

    /**
     * 去重
     */
    @Test
    public void testStream5() {
        List<Author> authors = getAuthors();

        // 打印所有作家的姓名，并且要求不能有重复元素
        authors.stream()
                .map(Author::getName) // 筛选出所有作家的姓名
                .distinct() // 去重
                .forEach(System.out::println); // 打印姓名
    }

    /**
     * 排序
     */
    @Test
    public void testStream6() {
        List<Author> authors = getAuthors();

        authors.stream()
                .sorted(((o1, o2) -> o1.getAge() - o2.getAge()))
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * limit  和  skip
     */
    @Test
    public void testStream7() {
        List<Author> authors = getAuthors();

        authors.stream()
                .sorted(((o1, o2) -> o1.getAge() - o2.getAge()))
                .distinct()
                .limit(2)
                .forEach(System.out::println);

        System.out.println();
        System.out.println();

        authors.stream()
                .sorted(((o1, o2) -> o1.getAge() - o2.getAge()))
                .distinct()
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * flatMap
     */
    @Test
    public void testStream8() {
        List<Author> authors = getAuthors();

        // 打印所有书籍的名字 对重复的元素进行去重
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        System.out.println();

        // 打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct() // 对Books属性进行去重
                .flatMap(book -> Stream.of(book.getCategory().split(",")))
                .distinct() // 对分类进行去重
                .forEach(System.out::println);
    }

    /**
     * 终结操作之 count
     */
    @Test
    public void testStream9() {
        List<Author> authors = getAuthors();

        // 打印这些作家的所出书籍的数目 注意删除重复的元素
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();

        System.out.println("count = " + count);
    }

    /**
     * 终结操作之 max | min
     */
    @Test
    public void testStream10() {
        List<Author> authors = getAuthors();

        // 分别获取这些作家所出书籍的最高分和最低分，并且打印
        Optional<Integer> maxScore = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getScore)
                .max(((o1, o2) -> o1 - o2));

        Optional<Integer> minScore = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getScore)
                .min(((o1, o2) -> o1 - o2));

        System.out.println(maxScore.get());
        System.out.println(minScore.get());
    }

    /**
     * 将流转换成集合
     */
    @Test
    public void testStream11() {
        List<Author> authors = getAuthors();
        // 获取一个存放所有作者名字的List集合

        List<String> nameList = authors.stream()
                .map(Author::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(nameList);

        System.out.println();
        System.out.println();

        // 获取一个所有书名的Set集合
        Set<String> nameSet = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getName)
                .collect(Collectors.toSet());

        System.out.println(nameSet);

        System.out.println();
        System.out.println();

        // 获取一个Map集合，map的key作为作者名，value为List<Book>
        Map<String, List<Book>> collect = authors.stream()
                .distinct() // TODO：必须去重 因为Map要求key不能重复 因为Author::getName会有重复
                .collect(Collectors.toMap(Author::getName, Author::getBooks));

        System.out.println("collect = " + collect);
    }

    /**
     * match: anyMatch  |  allMatch
     */
    @Test
    public void testStream12() {
        // 判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();

        boolean isExist = authors.stream()
                .anyMatch(author -> author.getAge() > 29);

        System.out.println(isExist);

        System.out.println();
        System.out.println();

        // 判断是否所有人的年龄都小于20？
        isExist = authors.stream()
                .allMatch(author -> author.getAge() < 20);

        System.out.println(isExist);
        System.out.println();
        System.out.println();

        // 判断是否所有人的年龄都不大于20？
        isExist = authors.stream()
                .noneMatch(author -> author.getAge() > 20);

        System.out.println(isExist);
    }

    /**
     * find
     */
    @Test
    public void testStream13() {
        List<Author> authors = getAuthors();

        // 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        Optional<String> name = authors.stream()
                .filter(author -> author.getAge() > 18)
                .map(Author::getName)
                .findAny();

        System.out.println(name);

        System.out.println();
        System.out.println();

        // 获取第一个年龄最小的作家 并输出他的名字
        Optional<String> firstName = authors.stream()
                .sorted(((o1, o2) -> o1.getAge() - o2.getAge()))
                .map(Author::getName)
                .findFirst();

        System.out.println(firstName);

    }

    /**
     * TODO：reduce !!!
     *   1.手动初始化  双参
     *   2.将第一个元素作为初始化值   单参
     */
    @Test
    public void testStream14() {
        // 使用reduce求所有作者年龄的和
        List<Author> authors = getAuthors();

        Integer result = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(0, (a, b) -> a + b);

        System.out.println("result = " + result);

        System.out.println();
        System.out.println();

        // 使用reduce求所有作者中年龄的最大值
        result = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(0, (maxAge, currentAge) -> maxAge > currentAge ? maxAge : currentAge);

        System.out.println(result);

        System.out.println();
        System.out.println();

        Optional<Integer> minResult = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce((minAge, currentAge) -> minAge > currentAge ? currentAge : minAge);

        minResult.ifPresent(System.out::println);
    }

}
