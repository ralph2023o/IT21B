
package midterm.require;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JOptionPane;
public class MidtermRequire {

    public static void main(String[] args) {
         LinkedList<String> ralph = new LinkedList<>();
        Stack<String> boss = new Stack<>();
        LinkedList<String> completedTasks = new LinkedList<>();  
        
        boolean running = true;

        while (running) {
            JOptionPane.showMessageDialog(null, "Welcome to our Todo List");

            String choice = JOptionPane.showInputDialog(null, "What would you like to do:"
                    + "\n 1. Add Task"
                    + "\n 2. Mark Task as Done"
                    + "\n 3. Undo"
                    + "\n 4. View To-Do List"
                    + "\n 5. View Completed Task"
                    + "\n 6. Exit");

            int number;
            try {
                number = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (number) {
                case 1:
                    String Task = JOptionPane.showInputDialog(null, "What Task would you like to add?");
                    if (Task == null) {
                        JOptionPane.showMessageDialog(null, "Task input was cancelled.");
                    } else {
                        ralph.add(Task);
                        boss.push("add:" + Task); 
                        JOptionPane.showMessageDialog(null, "Added Task: " + Task);
                    }
                    break;
                case 2:
                    if (!ralph.isEmpty()) {
                        StringBuilder taskList = new StringBuilder("Select a task to mark as done:\n");
                        for (int i = 0; i < ralph.size(); i++) {
                            taskList.append(i + 1).append(". ").append(ralph.get(i)).append("\n");
                        }

                        String hehe = JOptionPane.showInputDialog(null, taskList.toString());

                        try {
                            int kini = Integer.parseInt(hehe) - 1;
                            if (kini >= 0 && kini < ralph.size()) {
                                String completedTask = ralph.remove(kini);  
                                completedTasks.add(completedTask);  
                                JOptionPane.showMessageDialog(null, "Task marked as done: " + completedTask);
                                boss.push("done:" + completedTask); 
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid task selection.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tasks left to mark as done.");
                    }
                    break;
                case 3:
                    if (!boss.isEmpty()) {
                        String lastAction = boss.pop();
                        String[] parts = lastAction.split(":");

                        if (parts[0].equals("add")) {
                            ralph.removeLast();
                            JOptionPane.showMessageDialog(null, "Removed task: " + parts[1]);
                        } else if (parts[0].equals("done")) {
                            ralph.addFirst(parts[1]);
                            completedTasks.remove(parts[1]);  
                            JOptionPane.showMessageDialog(null, "Task undone: " + parts[1]);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No actions to undo.");
                    }
                    break;
                case 4:
                    if (!ralph.isEmpty()) {
                        String todoList = "To-Do List:\n";
                        for (String taskItem : ralph) {
                            todoList += taskItem + "\n";
                        }
                        JOptionPane.showMessageDialog(null, todoList);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your To-Do List is empty.");
                    }
                    break;
                case 5:
                    if (!completedTasks.isEmpty()) {
                        String completedTask = "Completed Tasks:\n";
                        for (String taskItem : completedTasks) {
                            completedTask += taskItem + "\n";
                        }
                        JOptionPane.showMessageDialog(null, completedTask);
                    } else {
                        JOptionPane.showMessageDialog(null, "No completed tasks yet.");
                    }
                    break;
                case 6:
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using the Todo List!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        
    }
    
}
    
    

