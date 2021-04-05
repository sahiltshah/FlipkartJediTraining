package com.flipkart.validator;

import com.flipkart.Exception.StudentException.StudentIndexInvalidException;
import com.flipkart.Exception.StudentException.UnapprovedStudentException;
import com.flipkart.bean.Student;

import static com.flipkart.temporaryDB.DB.*;

public class StudentValidator implements helperInterface.StudentValidatorFunctions {

    @Override
    public boolean checkCanStudyMoreCourses(int student_index) {
        try {
            if (student_index < 0 || student_index >= students.size()) {
                throw new StudentIndexInvalidException();
            } else if (students.get(student_index).getRollNumber() == -1)
                throw new UnapprovedStudentException();
            else {
                if (students.get(student_index).getCurrentSubjectCount() < 4)
                    return true;
                else
                    return false;

            }
        } catch (UnapprovedStudentException e) {
            e.printStackTrace();
        } catch (StudentIndexInvalidException e) {
            e.printStackTrace();
        }

        return false;

    }


    @Override
    public boolean checkIsStudentIdValid(int queryRollNumber) {
        for (Student x : students) {
            if (x.getRollNumber() == queryRollNumber)
                return true;
        }
        return false;
    }

    public boolean checkCanAddStudentCoursePreferences(int student_index) {
        try {
            if (student_index < 0 || student_index >= students.size()) {
                throw new StudentIndexInvalidException();
            } else if (students.get(student_index).getRollNumber() == -1)
                throw new UnapprovedStudentException();
            else {
                if (students.get(student_index).getSubjectPreferences().size() < 6)
                    return true;
                else
                    return false;

            }
        } catch (UnapprovedStudentException e) {
            e.printStackTrace();
        } catch (StudentIndexInvalidException e) {
            e.printStackTrace();
        }

        return false;

    }

}
