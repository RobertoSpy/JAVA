
public class Main {
    public static void main(String[] args) {
       
       
        Project p1 = new Project("P1", ProjectType.THEORETICAL);
        Project p2 = new Project("P2", ProjectType.PRACTICAL);
        Project p3 = new Project("P3", ProjectType.THEORETICAL);
        Project p4 = new Project("P4", ProjectType.PRACTICAL);

   
        Project[] s1Projects = {p1, p2};
        Student s1 = new Student("S1", s1Projects);

        Project[] s2Projects = {p1, p3};
        Student s2 = new Student("S2", s2Projects);

        Project[] s3Projects = {p3, p4};
        Student s3 = new Student("S3", s3Projects);

        Project[] s4Projects = {p1, p4};
        Student s4 = new Student("S4", s4Projects);

     
        p1.setAssignedStudent(s2);  
        p2.setAssignedStudent(s1);  
        p3.setAssignedStudent(s3);  
        p4.setAssignedStudent(s4);  

       
        System.out.println("Studentii si proiectele lor alocate:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);

        
        System.out.println("\nProiectele:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
    }
}

