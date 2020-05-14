package com.member.entity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * @Author 小燕子
 * @Date 2020-05-10 01:11:33
 * @Comment 学生1
 */
@Table(name = "Student1")
public class Student1 implements Serializable {

    private static final long serialVersionUID = 1L;

    // 所有字段列表

    /**
     *  主键ID
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private BigDecimal Id;
    /**
     *  测试
     */
    @Column(name = "Test")
    private String Test;


    public BigDecimal getId() {
        return Id;
    }
    public String getTest() {
        return Test;
    }


    public void setId(BigDecimal Id) {
        this.Id = Id;
    }
    public void setTest(String Test) {
        this.Test = Test;
    }


    // toString 方法
    @Override
    public String toString() {
        return "Student1{" +
                "Id='" + Id + '\'' +
                ",Test='" + Test + '\'' +
                '}';
    }
}
