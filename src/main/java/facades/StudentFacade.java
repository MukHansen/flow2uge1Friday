package facades;

import entities.Semester;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import mappers.StudentInfo;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class StudentFacade {

    private static StudentFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private StudentFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StudentFacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getStudentCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long studentCount = (long) em.createQuery("SELECT COUNT(s) FROM Student s").getSingleResult();
            return studentCount;
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Student.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsByTheNameAnders() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Student.findByFirstname").setParameter("firstname", "Anders").getResultList();
        } finally {
            em.close();
        }
    }

    public Student insertStudent(String fName, String lName) {
        EntityManager em = emf.createEntityManager();

        Student stu = new Student(fName, lName);
        try {
            em.getTransaction().begin();
            em.persist(stu);
            em.getTransaction().commit();
            return stu;
        } finally {
            em.close();
        }
    }

    public Student assStudentToSem(long stuID, long semID) {
        EntityManager em = emf.createEntityManager();
        try {
            Student stu = em.find(Student.class, stuID);
            Semester sem = em.find(Semester.class, semID);
            stu.setSemester(sem);

            em.getTransaction().begin();
            em.merge(stu);
            em.getTransaction().commit();

            return stu;
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsWithLastNameAnd() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Student.findByLastname").setParameter("lastname", "And").getResultList();
        } finally {
            em.close();
        }
    }

    public long getTotalStudentCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(s) FROM Student s").getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

    public long getNumberOfStudentsBySemester(String semName) {
        EntityManager em = emf.createEntityManager();
        try {
            Semester sem = (Semester) em.createNamedQuery("Semester.findByName").setParameter("name", semName).getSingleResult();
            long count = (long) em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.semester = :semester").setParameter("semester", sem).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

    public List<StudentInfo> getAllStudentInfo() {
        EntityManager em = emf.createEntityManager();
        try {
            List<StudentInfo> list = em.createNamedQuery("Student.findAll").getResultList();
            return list;
        } finally {
            em.close();
        }

    }

    public StudentInfo getStudentInfo(long Id) {
        EntityManager em = emf.createEntityManager();
        try {
            Student stu = (Student) em.createNamedQuery("Student.findById").setParameter("id", Id).getSingleResult();
            StudentInfo stuInf = new StudentInfo(stu);
            return stuInf;

        } finally {
            em.close();
        }

    }

}
