/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entity.Hobby;
import entity.Person;
import facade.EntityFacade;
import java.util.List;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {
        EntityFacade facade = new EntityFacade(Persistence.createEntityManagerFactory("CA2WebPU"));
        Person p = new Person("anders@gmail.com","Anders", "And");
        p = facade.createPerson(p);

        System.out.println(p.getId());

        Hobby h1 = new Hobby("Tennis", "Smashing a ball");
        p = facade.addHobby(p, h1);

        System.out.println(p.getHobbies().get(0).getName());

        Hobby h2 = new Hobby("Gaming", "Going crazy");
        p = facade.addHobbyFromId(1, h2);

        System.out.println(p.getHobbies().get(1).getName());

        Person person = facade.findPerson(1);        
        System.out.println(person.getFirstName());
        
        
        List<Person> persons = facade.getPersons();
        System.out.println(persons.size());
        System.out.println(persons.get(0).getFirstName());
        System.out.println(persons.get(0).getLastName());

    }

}
