package com.puxinxiaolin.study.javaBasic.copy;

class Person2 implements Cloneable {
    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Employee2 implements Cloneable {
    String position;
    Person2 person;

    public Employee2(String position, Person2 person) {
        this.position = position;
        this.person = person;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Employee2 cloned = (Employee2) super.clone();
        cloned.person = (Person2) person.clone();
        return cloned;
    }
}

/**
 * @Description: 深拷贝不仅复制值类型属性，也会递归复制引用类型属性，修改新对象的引用类型属性不会影响原对象
 * @Author: YCcLin
 * @Date: 2025/4/21 12:37
 */
public class DeepCopySample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person2 person = new Person2("Jackson", 30);
        Employee2 original = new Employee2("Manager", person);
        Employee2 copy = (Employee2) original.clone();

        System.out.println(original.person == copy.person);
        copy.person.name = "Tim";
        System.out.println(original.person.name);
    }
}
