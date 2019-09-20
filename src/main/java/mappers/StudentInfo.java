/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entities.Student;

/**
 *
 * @author Mkhansen;
 */
public class StudentInfo {
    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;
    

    public StudentInfo(Student stu) {
        this.fullName = stu.getFirstname()+ " " + stu.getLastname();
        this.studentId = stu.getId();
        this.classNameThisSemester = stu.getSemester().getName();
        this.classDescription = stu.getSemester().getDescription();
    }

    @Override
    public String toString() {
        return "StudentInfo{" + "fullName=" + fullName + ", studentId=" + studentId + ", classNameThisSemester=" + classNameThisSemester + ", classDescription=" + classDescription + '}';
    }
    
    
    
}
