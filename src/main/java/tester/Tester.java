/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import facades.StudentFacade;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Mkhansen;
 */
public class Tester {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ExamPreparationJPQL",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final StudentFacade FACADE = StudentFacade.getStudentFacade(EMF);

    public static void main(String[] args) {
        System.out.println("Getting All Students");
        System.out.println(FACADE.getAllStudents());
        System.out.println("---------");
        System.out.println("Getting All Students by the name of Anders");
        System.out.println(FACADE.getAllStudentsByTheNameAnders());
        System.out.println("----------");
//        System.out.println("Inserting Student in the database");
//        System.out.println(FACADE.insertStudent("NewGuy", "Johnson"));
//        System.out.println("Assigning new Student to Semester");
//        System.out.println(FACADE.assStudentToSem(7, 1));
        System.out.println("Getting All Students with the lastname And");
        System.out.println(FACADE.getAllStudentsWithLastNameAnd());
        System.out.println("-----------");
        System.out.println("Getting Total count of all Students");
        System.out.println(FACADE.getTotalStudentCount());
        System.out.println("-----------");
        System.out.println("Getting All Students by semester name");
        System.out.println(FACADE.getNumberOfStudentsBySemester("CLcos-v14e"));
//        System.out.println(FACADE.getNumberOfStudentsBySemester(1));
        System.out.println("Getting All StudentInfo");
        System.out.println(FACADE.getAllStudentInfo());
        System.out.println("Getting Student Info by Id");
        System.out.println(FACADE.getStudentInfo(1));
        
    }
}
