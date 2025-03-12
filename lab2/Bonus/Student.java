class Student extends Person {
  private int registration;
  private Project[] preferedProjects;

  public Student(int registration, String name, String dateOfBirth, Project[] preferedProjects){
    super(name, dateOfBirth);
    this.registration = registration;
    this.preferedProjects = preferedProjects;
  }

  public int getRegistration(){
    return registration;
  }

  public Project[] getPreferredProjects(){
    return preferedProjects;
  }

  @Override
  public boolean equals(Object obj){
    if(this == obj){
      return true;
    }
    
    if(obj == null || getClass() != obj.getClass()){
      return false;
    }

    Student student = (Student) obj;
    return registration == student.registration;
  }


@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Student{").append("name='").append(name).append("', registration=").append(registration);
    sb.append(", preferredProjects=[");
    for (Project project : preferedProjects) {
        sb.append(project.getTitle()).append(", ");
    }
    sb.append("]}");
    return sb.toString();
}
}