package com.layermark.springbootapi.student;

import com.layermark.springbootapi.projects.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student){
        Optional<Student> studentOptional =studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists =studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id "+studentId+" doesn't exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String newName ,String newEmail ){
        Student myChangeStudent = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id "+ studentId + " doesn't exists"
        ));
        if(newName!=null && newName.length()>0 && !Objects.equals(myChangeStudent.getName(), newName)){
            myChangeStudent.setName(newName);
        }

        if(newEmail!=null && newEmail.length()>0 && !Objects.equals(myChangeStudent.getEmail(), newEmail)){
            Optional<Student> studentOptional =studentRepository.findStudentByEmail(newEmail);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email is taken");
            }
            myChangeStudent.setEmail(newEmail);
        }
    }
}
