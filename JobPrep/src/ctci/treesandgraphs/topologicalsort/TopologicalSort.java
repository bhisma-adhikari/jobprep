package ctci.treesandgraphs.topologicalsort;

import utils.Utils;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        String[] projects = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        String[][] dependencies = new String[8][];
        dependencies[0] = new String[]{"f", "c"};
        dependencies[1] = new String[]{"f", "a"};
        dependencies[2] = new String[]{"f", "b"};
        dependencies[3] = new String[]{"c", "a"};
        dependencies[4] = new String[]{"b", "a"};
        dependencies[5] = new String[]{"a", "e"};
        dependencies[6] = new String[]{"b", "e"};
        dependencies[7] = new String[]{"d", "g"};

        String[] buildOrder = buildOrder(projects, dependencies);
        Utils.printArray(buildOrder);

    }

    private static String[] buildOrder(String[] projects, String[][] dependencies) {
        Graph graph = new Graph(projects, dependencies);
        Stack<String> orderReversed = new Stack<>();

        for (Project project : graph.getProjects()) {
            if (!doDfs(project, orderReversed)) {
                return null;
            }
        }

        return reverseStack(orderReversed);

    }

    private static String[] reverseStack(Stack<String> stack) {
        String[] order = new String[stack.size()];
        int index = 0;
        while(!stack.empty()){
            order[index] = stack.pop();
            index++;
        }
        return order;
    }

    private static boolean doDfs(Project project, Stack<String> orderReversed) {

        if (project.getState() == State.PARTIAL) {  // graph contains cycle
            return false;
        }

        if (project.getState() == State.COMPLETE) { // already added to orderReversed
            return true;
        }

        project.setState(State.PARTIAL);
        for (Project child : project.getChildren()) {
            doDfs(child, orderReversed);
        }

        project.setState(State.COMPLETE);
        orderReversed.push(project.getName());

        return true;

    }


}

class Graph {
    private List<Project> projects;
    private Map<String, Project> projectsMap;  // projectName : project

    public Graph(String[] projectNames, String[][] dependencies) {
        projects = new ArrayList<>();
        projectsMap = new HashMap<>();

        // initialize this.projects (without setting children)
        // initialize this.projectsMap
        for (String projectName : projectNames) {
            Project project = new Project(projectName);
            projects.add(project);
            projectsMap.put(projectName, project);
        }

        // set children for this.projects
        for (String[] dependency : dependencies) {
            Project independent = getProjectByName(dependency[0]);
            Project dependent = getProjectByName(dependency[1]);

            // add dependent project to the children of independent project
            independent.getChildren().add(dependent);
        }
    }

    public Project getProjectByName(String name) {
        return projectsMap.get(name);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}


class Project {
    private String name;
    private List<Project> children;
    private State state;

    public Project(String name){
        this.name = name;
        this.children = new ArrayList<>();
        this.state = State.BLANK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getChildren() {
        return children;
    }

    public void setChildren(List<Project> children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

enum State {
    BLANK,
    PARTIAL,
    COMPLETE
}