public class Main {
  public static void main(String[] args) {
    
      Project p1 = new Project("AI Research");
      Project p2 = new Project("Blockchain Development");
      Project p3 = new Project("Cybersecurity Analysis");
      Project p4 = new Project("Cloud Computing");

      
      Teacher t1 = new Teacher(new Project[]{p1, p2}, "Dr. Smith", "1975-03-20");
      Teacher t2 = new Teacher(new Project[]{p3, p4}, "Dr. Johnson", "1980-11-10");

      
      Student s1 = new Student(101, "Alice", "2000-05-12", new Project[]{p1, p2});
      Student s2 = new Student(102, "Bob", "1999-07-24", new Project[]{p1, p3});
      Student s3 = new Student(103, "Charlie", "2001-09-30", new Project[]{p2, p4});
      Student s4 = new Student(104, "David", "2002-01-15", new Project[]{p3, p4});

      
      Problem problem = new Problem(new Student[]{s1, s2, s3, s4}, new Teacher[]{t1, t2});

      
      Solution solution = new Solution(problem);
      solution.allocateProjects();

      
      System.out.println("\nAlocarea proiectelor:");
      System.out.println(solution);
  }
}
