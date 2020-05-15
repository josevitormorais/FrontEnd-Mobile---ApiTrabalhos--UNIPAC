package com.example.appunipac.Model.Domain;

import java.util.Objects;

public class Aluno {
    public static final String TABLE_ALUNO = "aluno";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_REGISTER = "register";

    public static final String CREATE_TABLE_ALUNO = "create table " + TABLE_ALUNO + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_NAME + " text not null, " +
            FIELD_REGISTER + " int not null);";

    public static final String DROP_TABLE_ALUNO = "DROP TABLE IF EXISTS " + TABLE_ALUNO;

    public static String[] allColumms = { Aluno.FIELD_ID, Aluno.FIELD_NAME, Aluno.FIELD_REGISTER };

    private Long id;
    private String name;
    private int register;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                ", " + FIELD_ID + "=" + id +
                ", " + FIELD_NAME + "='" + name + '\'' +
                ", " + FIELD_REGISTER + "=" + register +
                '}';
    }

}
