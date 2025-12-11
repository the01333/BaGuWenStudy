package com.puxinxiaolin.study.javaBasic.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description: 使用 transient 关键字实现指定字段忽略序列化
 * @Author: YCcLin
 * @Date: 2025/4/21 12:47
 */
class User1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private transient String password;

    public User1(String username, String password) {
        this.name = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/**
 * @Description: 自定义序列方法实现指定字段忽略序列化
 * @Author: YCcLin
 * @Date: 2025/4/21 12:49
 */
class User2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password;

    public User2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 序列化
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // 序列化非 transient 字段
        oos.defaultWriteObject();
        // 自定义序列化 password
        oos.writeObject(encryptPassword(password));
    }

    // 反序列化
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        password = decryptPassword((String) ois.readObject());
    }

    private String encryptPassword(String password) {
        return "encrypted_" + password;
    }

    private String decryptPassword(String encryptedPassword) {
        return encryptedPassword.replace("encrypted_", "");
    }

    @Override
    public String toString() {
        return "User2{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

public class TransientExample {
    public static void main(String[] args) {
//        User1 user1 = new User1("Jackson", "123456");
//        Path path = Paths.get("user.ser");
//
//        // 序列化
//        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
//            oos.writeObject(user1);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        // 反序列化
//        User1 desrializedUser1 = null;
//        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
//            desrializedUser1 = (User1) ois.readObject();
//        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//
//        System.out.println(desrializedUser1);

        User2 user2 = new User2("Jackson", "123456");
        Path path = Paths.get("user.ser");

        // 序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(user2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 反序列化
        User2 desrializedUser2 = null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            desrializedUser2 = (User2) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println(desrializedUser2);
    }
}
