import java.util.Random;


public class Main {
    public static void main(String[] args) {
        
        int numStudents = 10;  
        int numProjects = 14;   
        int maxPreferences = 3;  

        
        Project[] projects = new Project[numProjects];
        for (int i = 0; i < numProjects; i++) {
            projects[i] = new Project("Project " + (i + 1));
        }

        //alocare proiecte profi
        int numTeachers = numProjects / 2;  
        Teacher[] teachers = new Teacher[numTeachers];
        Random rand = new Random();

        for (int i = 0; i < numTeachers; i++) {
            int startIndex = i * 2;
            int numProjectsForTeacher = 2;
            
            if (startIndex + numProjectsForTeacher > numProjects) {
                numProjectsForTeacher = numProjects - startIndex; 
            }
            
            Project[] teacherProjects = new Project[numProjectsForTeacher];
            for (int j = 0; j < numProjectsForTeacher; j++) {
                teacherProjects[j] = projects[startIndex + j];
            }

            teachers[i] = new Teacher(teacherProjects, "Teacher " + (i + 1), "1980-01-01");
        }

        //alocare random studenti
        Student[] students = new Student[numStudents];
        for (int i = 0; i < numStudents; i++) {
            int numPreferences = rand.nextInt(maxPreferences) + 1; 
            Project[] preferredProjects = new Project[numPreferences];

            for (int j = 0; j < numPreferences; j++) {
                preferredProjects[j] = projects[rand.nextInt(numProjects)];
            }

            students[i] = new Student(i, "Student " + (i + 1), "2000-01-01", preferredProjects);
        }

        
        Problem problem = new Problem(students, teachers);

        
        Solution solution = new Solution(problem);

       
        solution.allocateProjects();  

       
        long startTime = System.nanoTime();
        boolean hallSatisfied = solution.checkHallCondition();
        long endTime = System.nanoTime();

        long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);

        
        System.out.println("Teorema lui Hall " + (hallSatisfied ? "este îndeplinită" : "NU este îndeplinită"));
        System.out.println("Timp de execuție: " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Memorie folosită: " + memoryUsed + " MB");

       
        System.out.println("\nAlocările de proiecte:");
        for (int i = 0; i < numStudents; i++) {
            System.out.print(students[i].getName() + " -> ");
            
            Project allocatedProject = solution.getAllocatedProjects()[i];
            if (allocatedProject != null) {
                System.out.println(allocatedProject.getTitle());
            } else {
                System.out.println("No Project");
            }
        }
    }
}
