class Project{
  private String title;

  public Project(String title){
    this.title = title;
  }

  public String getTitle(){
    return title;
  }

  @Override
  public boolean equals(Object obj){
    if(this == obj){
      return true;
    }
    
    if(obj == null || getClass() != obj.getClass()){
      return false;
    }

    Project project = (Project) obj;
    return title == project.title;
  }
}
