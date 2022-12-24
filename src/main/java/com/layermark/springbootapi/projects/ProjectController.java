package com.layermark.springbootapi.projects;

import com.layermark.springbootapi.student.Student;
import com.layermark.springbootapi.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private final ProjectService projectService;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectController(ProjectService projectService, StudentRepository studentRepository, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

    @PostMapping
    public void registerCourse(@RequestBody Project project){
        projectService.addNewProject(project);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProjects(@PathVariable("projectId") Long projectId){
        projectService.deleteProject(projectId);
    }

    @PutMapping(path = "{projectId}")
    public void updateCourse(@PathVariable("projectId") Long projectId,
                             @RequestParam(required = false) String cname,
                             @RequestParam(required = false) String instructorname
    ){
        projectService.updateProject(projectId, cname, instructorname);
    }

    @PutMapping(path = "d/{projectId}")
    public  void updateStudents(@PathVariable("projectId") Long projectId,
                                @RequestBody Student sid
    ){
        projectService.updateProjectStudents(projectId, sid);
    }

    @PutMapping(path = "c/{projectId}")
    public  void connectStudents(@PathVariable("projectId") Long projectId,
                                @RequestParam Long sid
    ){
        projectService.connectProjectStudents(projectId, sid);
    }
}
