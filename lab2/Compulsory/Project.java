public class Project {
  private String projectName;
  private ProjectType projectType;
  private Student assignedStudent;
  // projectType ca sa vad de ce tip sunt proiectele si un name
  public Project(String projectName, ProjectType projectType) {
      this.projectName = projectName;
      this.projectType = projectType;
      this.assignedStudent = null; 
  }

  //metode
  public String getProjectName() {
      return projectName;
  }

  public void setProjectName(String projectName) {
      this.projectName = projectName;
  }

  public ProjectType getProjectType() {
      return projectType;
  }

  public void setProjectType(ProjectType projectType) {
      this.projectType = projectType;
  }

  public Student getAssignedStudent() {
      return assignedStudent;
  }

  public void setAssignedStudent(Student assignedStudent) {
      this.assignedStudent = assignedStudent;
  }

  @Override
  //overriding pe afisare
  public String toString() {
      return "Project{" +
              "projectName='" + projectName + '\'' +
              ", projectType=" + projectType +
              ", assignedStudent=" + (assignedStudent != null ? assignedStudent.getName() : "None") +
              '}';
  }
}