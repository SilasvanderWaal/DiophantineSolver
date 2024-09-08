package Engine;

import GUI.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Program extends JFrame {
    private int[] variables;
    private int[] coefficients;
    private DefaultListModel<String> calculation_result_buff;
    private int variables_sum;
    int total_amount_of_variables;
    int average_amount_of_variables;
    int range_min;
    int range_max;

    public Program() {
        ArrayList<String> list_of_strings;

        try {
            // Loop until a non-empty file is selected
            do {
                FileDialog dialog = new FileDialog((Frame) null, "Select file to open");
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                File file = new File(dialog.getDirectory(), dialog.getFile());
                dialog.dispose();

                BufferedReader bf = new BufferedReader(new FileReader(file));
                list_of_strings = new ArrayList<>();
                String line = bf.readLine();

                while (line != null) {
                    list_of_strings.add(line);
                    line = bf.readLine();
                }
                bf.close();

                if (list_of_strings.isEmpty())
                    JOptionPane.showMessageDialog(getRootPane(), "Template is corrupt, please select / create a new template");
            } while (list_of_strings.isEmpty());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialize variables from file
        total_amount_of_variables = list_of_strings.size();
        variables = new int[total_amount_of_variables];
        variables_sum = 0;
        for (int i = 0; i < total_amount_of_variables - 1; i++) {
            variables[i] = Integer.parseInt(list_of_strings.get(i));
            variables_sum += variables[i];
        }
    }

    public DefaultListModel<String> diophantine_bruteforce_setup(int total_amount) {
        coefficients = new int[total_amount_of_variables];
        average_amount_of_variables = total_amount / variables_sum;
        range_min = average_amount_of_variables;
        range_max = average_amount_of_variables + 2;
        calculation_result_buff = new DefaultListModel<>();

        if (total_amount <= variables_sum) {
            JOptionPane.showMessageDialog(getRootPane(), "The amount in EUROS must be more than the sum of all the variables");
            return calculation_result_buff;
        }

        // Try small-range brute-force first
        if (solve_diophantine(variables, total_amount_of_variables, coefficients, 0, total_amount, range_min, range_max)) {
            return calculation_result_buff;
        }

        // Try larger-range brute-force if small-range fails
        if (solve_diophantine(variables, total_amount_of_variables, coefficients, 0, total_amount)) {
            return calculation_result_buff;
        }

        JOptionPane.showMessageDialog(getRootPane(), "Unable to find a solution to the equation");
        return calculation_result_buff;
    }

    private boolean solve_diophantine(int[] a, int n, int[] x, int index, int remaining, int range_min, int range_max) {
        // If all variables are processed and remaining amount is 0, solution found
        if (index == n) {
            if (remaining == 0) {
                for (int i = 0; i < n; i++) {
                    calculation_result_buff.addElement(x[i] + " pcs of " + a[i] + " EURO");
                }
                return true;
            }
            return false;
        }

        // Try different values in the given range
        for (int i = range_min; i <= range_max; i++) {
            x[index] = i;
            if (solve_diophantine(a, n, x, index + 1, remaining - a[index] * i, range_min, range_max)) {
                return true;
            }
        }
        return false;
    }

    private boolean solve_diophantine(int[] a, int n, int[] x, int index, int remaining) {
        // Try larger coefficient range if smaller range fails
        if (index == n) {
            if (remaining == 0) {
                for (int i = 0; i < n; i++) {
                    calculation_result_buff.addElement(x[i] + " pcs of " + a[i] + " EURO");
                }
                return true;
            }
            return false;
        }

        for (int i = average_amount_of_variables - 10; i <= average_amount_of_variables + 10; i++) {
            x[index] = i;
            if (solve_diophantine(a, n, x, index + 1, remaining - a[index] * i)) {
                return true;
            }
        }
        return false;
    }

    public void change_equation(int[] variables) {
        variables_sum = 0;
        this.variables = variables;

        for (int i = 0; i < variables.length; i++) {
            variables_sum += variables[i];
        }

        total_amount_of_variables = variables.length;
    }

    public int[] get_amount_of_variables() {
        return coefficients;
    }
}
