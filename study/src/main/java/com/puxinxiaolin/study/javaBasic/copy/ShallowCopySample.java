package com.puxinxiaolin.study.javaBasic.copy;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Employee implements Cloneable {
    String position;
    Person person;

    public Employee(String position, Person person) {
        this.position = position;
        this.person = person;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

/**
 * @Description: 浅拷贝只复制值类型属性，对于引用类型属性，只复制引用地址，所以修改引用类型属性，也会影响原对象
 * @Author: YCcLin
 * @Date: 2025/4/21 11:33
 */
public class ShallowCopySample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person("Jack", 18);
        Employee original = new Employee("Manager", person);
        Employee newOne = (Employee) original.clone();

        System.out.println(original.person == newOne.person);
        newOne.person.name = "Tom";
        System.out.println(original.person.name);
    }
}
