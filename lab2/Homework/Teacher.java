class Teacher extends Person {
  private Project[] projects;

  public Teacher(Project[] projects, String name, String dateOfBirth){
    super(name, dateOfBirth);
    this.projects = projects;
  }

  public Project[] getProjects(){
    return projects;
  }
//ovveride la equals
  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Teacher teacher = (Teacher) obj;
      return name.equals(teacher.name) && dateOfBirth.equals(teacher.dateOfBirth);
  }

  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Teacher{name='").append(name).append("', dateOfBirth='").append(dateOfBirth).append("', projects=[");
      for (Project project : projects) {
          sb.append(project.getTitle()).append(", ");
      }
      sb.append("]}");
      return sb.toString();
  }
}
