package com.layermark.springbootapi.projects;

import com.layermark.springbootapi.student.Student;
import com.layermark.springbootapi.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public void addNewProject(Project project){
        Optional<Project> projectOptional =projectRepository.findProjectByPname(project.getPname());
        if(projectOptional.isPresent()){
            throw new IllegalStateException("project exsists");
        }
        projectRepository.save(project);
    }

    public void deleteProject(Long projectid){
        boolean exists =projectRepository.existsById(projectid);
        if(!exists){
            throw new IllegalStateException(
                    "Project with id "+projectid+" doesn't exist");
        }
        projectRepository.deleteById(projectid);
    }

    @Transactional
    public void updateProject(Long projectId, String newName ,String newInstructor ){
        Project myChangeProject = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException(
                "Project with id "+ projectId + " doesn't exists"
        ));
        if(newName!=null && newName.length()>0 && !Objects.equals(myChangeProject.getCourseName(), newName)){
            Optional<Project> projectOptional =projectRepository.findProjectByCourseName(newName);
            if(projectOptional.isPresent()){
                throw new IllegalStateException(
                        "Project with name "+ newName +" already exists");
            }
            myChangeProject.setCourseName(newName);
        }

        if(newInstructor!=null && newInstructor.length()>0 && !Objects.equals(myChangeProject.getInstructorName(), newInstructor)){
            myChangeProject.setInstructorName(newInstructor);
        }

    }

    @Transactional
    public void updateProjectStudents(Long projectId, Student sid){
        Project myproj = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException(
                "Project with id "+ projectId + " doesn't exists"
        ));

        myproj.addStudents(sid);
    }

    @Transactional
    public void connectProjectStudents(Long projectId, Long sid){
        System.out.println("buradayiiimm");
        Project myproj = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException(
                "Project with id "+ projectId + " doesn't exists"
        ));
        Student mystu = studentRepository.findById(sid).orElseThrow(() -> new IllegalStateException(
                "Student with id "+ sid + " doesn't exists"
        ));

        myproj.connectStudent(mystu);
    }

}
