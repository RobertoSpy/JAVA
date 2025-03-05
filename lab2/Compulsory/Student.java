public class Student {
  private String name;
  private Project[] acceptableProjects; 

  public Student(String name, Project[] acceptableProjects) {
      this.name = name;
      this.acceptableProjects = acceptableProjects;
  }

  
  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public Project[] getAcceptableProjects() {
      return acceptableProjects;
  }

  public void setAcceptableProjects(Project[] acceptableProjects) {
      this.acceptableProjects = acceptableProjects;
  }

  @Override 

  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Student{name='").append(name).append("', acceptableProjects=");
      for (Project project : acceptableProjects) {
          sb.append(project.getProjectName()).append(" ");
      }
      sb.append("}");
      return sb.toString();
  }
}