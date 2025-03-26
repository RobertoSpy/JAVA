public class Solution {
    private Problem problem;
    private Project[] allocatedProjects;

    public Solution(Problem problem) {
        this.problem = problem;
        this.allocatedProjects = new Project[problem.getStudents().length];
    }
//greedy cu index care este valabil prima data
    public void allocateProjects() {
        Student[] students = problem.getStudents();
        Project[] allProjects = getAllProjects();
        boolean[] projectAssigned = new boolean[allProjects.length];

        for (int i = 0; i < students.length; i++) {
            Project[] preferences = students[i].getPreferredProjects();

            for (int j = 0; j < preferences.length; j++) {
                int projectIndex = getProjectIndex(preferences[j], allProjects);

                if (projectIndex != -1 && !projectAssigned[projectIndex]) {
                    allocatedProjects[i] = preferences[j];
                    projectAssigned[projectIndex] = true;
                    break;
                }
            }
        }
    }

    private Project[] getAllProjects() {
        Teacher[] teachers = problem.getTeachers();
        int totalProjects = 0;

        for (Teacher teacher : teachers) {
            totalProjects += teacher.getProjects().length;
        }

        Project[] allProjects = new Project[totalProjects];
        int index = 0;

        for (Teacher teacher : teachers) {
            for (Project project : teacher.getProjects()) {
                if (!containsProject(allProjects, index, project)) {
                    allProjects[index++] = project;
                }
            }
        }

        Project[] uniqueProjects = new Project[index];
        System.arraycopy(allProjects, 0, uniqueProjects, 0, index);

        return uniqueProjects;
    }

    private boolean containsProject(Project[] projects, int size, Project project) {
        for (int i = 0; i < size; i++) {
            if (projects[i].equals(project)) {
                return true;
            }
        }
        return false;
    }
//verific proiectul de la index respectiv
    private int getProjectIndex(Project project, Project[] allProjects) {
        for (int i = 0; i < allProjects.length; i++) {
            if (allProjects[i].equals(project)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Student[] students = problem.getStudents();
        for (int i = 0; i < students.length; i++) {
            sb.append(students[i].getName())
              .append(" -> ")
              .append(allocatedProjects[i] != null ? allocatedProjects[i].getTitle() : "No Project")
              .append("\n");
        }
        return sb.toString();
    }
}
