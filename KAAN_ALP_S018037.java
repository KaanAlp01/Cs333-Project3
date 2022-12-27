import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class KAAN_ALP_S018037 {
    static int total;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of proposed venture projects:");
        int n = Integer.parseInt(scan.nextLine());
        String[] projects = new String[n];
        Vector<Integer> outcome = new Vector<Integer>();
        String[][] prerequisites = new String[n][2];
        System.out.println("Enter the venture projects:");
        for (int i = 0; i < n; i++)
            projects[i] = scan.next();
        System.out.println("Enter the costs and profits for the venture projects:");
        for(int i = 0; i < n; i++)
            outcome.add(scan.nextInt());
        System.out.println("Enter the prerequisites for the venture projects:");
        for (int i = 0; i < n; i++) {
            String tempS = scan.next();
            prerequisites[i][0] = tempS.charAt(1) + "";
            prerequisites[i][1] = tempS.charAt(3) + "";
        }
        System.out.println("Enter 'Decide' to continue");
        while(true){
           String temp = scan.next();
            if(temp.equals("Decide")){
                decisionLogic(prerequisites,projects,outcome);
                break;
            }
        }
    }
    static void decisionLogic(String[][] prerequisites, String[] projects, Vector<Integer> outcome){
        Vector<Integer> [] connections = new Vector[1000];
        for(int i = 0; i < connections.length; i++)
            connections[i] = new Vector<Integer>();
        for(int i= 0; i < projects.length; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < projects.length; j++) {
                if (projects[j].equals(prerequisites[i][0]))
                    x = j;
                if (projects[j].equals(prerequisites[i][1]))
                    y = j;
            }
            connections[x].add(y);
        }
        boolean[] visited = new boolean[outcome.size()+1];
        int maximum = 0;
        String[] nodes = new String[outcome.size()+1];
        for(int i = 0; i < projects.length; i++){
            if(!visited[i]){
                total = 0;
                String[] connected = new String[outcome.size()];
                depthFirstSearch(i,connections,visited,outcome,connected,projects);
                if(total > maximum) {
                    maximum = total;
                    nodes = connected;
                }
            }
        }
        System.out.println(" ");
        System.out.print("Venture Projects: ");
        for (String node : nodes) {
            if (node != null)
                System.out.print(node + " ");
        }
        System.out.println(" ");
        System.out.println("Maximum Profit: "+ maximum);
    }
        static void depthFirstSearch(int value, Vector<Integer> connections[], boolean[] visited, Vector<Integer> outcome, String[] connected,String[] projects){
            visited[value] = true;
            total+= outcome.get(value);
            connected[value]= projects[value];
            for(int i: connections[value]){
                if(visited[i] == false )
                    depthFirstSearch(i,connections,visited,outcome,connected,projects);
            }
        }

}


