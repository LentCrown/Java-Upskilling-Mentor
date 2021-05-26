package org.mentor.patterns.creational.singleton;

public class Man {
    private static Man instance;

    private final String name;
    private final String surname;
    private final String patronymic;
    private final String birthDay;

    private Man(String name, String surname, String patronymic, String birthDay) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
    }

    public static Man getInstance(String name, String surname, String patronymic, String birthDay){
        if (instance == null){
            instance = new Man(name, surname, patronymic, birthDay);
        }
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Man man = (Man) o;
        if (!name.equals(man.name)) return false;
        if (!surname.equals(man.surname)) return false;
        if (!patronymic.equals(man.patronymic)) return false;
        return birthDay.equals(man.birthDay);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + birthDay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDay='" + birthDay + '\'' +
                '}';
    }
}
