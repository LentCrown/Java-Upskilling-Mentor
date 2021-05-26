package org.mentor.patterns.creational.factory;

import org.mentor.patterns.creational.factory.CppDeveloperFactory;
import org.mentor.patterns.creational.factory.Developer;
import org.mentor.patterns.creational.factory.DeveloperFactory;
import org.mentor.patterns.creational.factory.JavaDeveloperFactory;

public class Main {
    public static void main(String[] args) {
/*1     Отдельно создавать сущности:

        JavaDeveloper javaDeveloper = new JavaDeveloper();
        javaDeveloper.writeCode();
        CppDeveloper cppDeveloper = new CppDeveloper();
        cppDeveloper.writeCode();

        Недостаток - переписывание кода при необходимости создания другой сущности
*/

/*2     Создадим интерфейс для создания похожих сущностей;
        Будем создавать экземпляры сущностей с типом интерфейса.

        Developer developer = new JavaDeveloper();
        developer.writeCode();
        developer = new CppDeveloper();
        developer.writeCode();

        Недостаток - мы немного упростили программу, но переписывать код по прежнему нужно
*/

/*3     Создадим сущности, которые будут отвечать за создание JavaDeveloper и CppDeveloper:
        Общий интерфейс создания объектов;
        Своя реализация создания;

        DeveloperFactory developerFactory = new JavaDeveloperFactory();
        Developer developer = developerFactory.createDeveloper();
        developer.writeCode();

        developerFactory = new JavaDeveloperFactory();
        developer = developerFactory.createDeveloper();
        developer.writeCode();

        Можно еще упростить расширение нашей программы в будущем
        */

//4     Создадим статический метод
        DeveloperFactory developerFactory = createDeveloperBySpecialty("java");
//        developer = developerFactory.createDeveloper().writeCode();
//        developer.writeCode();
        developerFactory.createDeveloper().writeCode();

        developerFactory = createDeveloperBySpecialty("cpp");
        developerFactory.createDeveloper().writeCode();

    }
    static DeveloperFactory createDeveloperBySpecialty(String specialty){
        if(specialty.equalsIgnoreCase("java")) return new JavaDeveloperFactory();
        else if(specialty.equalsIgnoreCase("cpp")) return new CppDeveloperFactory();
        else throw new RuntimeException(specialty + " is unknown specialty");
    }
}
