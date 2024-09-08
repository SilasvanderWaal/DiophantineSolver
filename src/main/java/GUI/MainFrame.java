package GUI;

import Engine.Program;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.io.*;

public class MainFrame {
    private JPanel main_panel;
    private JLabel main_header;
    private JPanel content_pane;
    private JButton calculate_button;
    private JTextField input_field;
    private JLabel input_field_header;
    private JPanel input_panel;
    private JLabel list_header;
    private JList result_list;
    private JPanel settings_panel;
    private JButton copy_button;
    private JButton switch_template;
    private JButton create_mew_template;
    private DefaultListModel<String> result_list_input;
    private Program program;

    public MainFrame() {
        program = new Program();

        // Action to perform when the "Calculate" button is clicked
        calculate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount;
                String amountBuff = input_field.getText().replaceAll("\\s", ""); // Remove spaces

                if (amountBuff.isEmpty()) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Enter a value", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isNumeric(amountBuff)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Enter a number, not text", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    amount = Integer.parseInt(amountBuff);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "The entered value is not compatible with the calculator");
                    return;
                }

                result_list.setModel(program.diophantine_bruteforce_setup(amount));
            }
        });

        // Action to copy the result list to the clipboard
        copy_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringBuilder content_to_copy = new StringBuilder();
                int[] arr = program.get_amount_of_variables();

                for (int i = 0; i < arr.length; i++) {
                    content_to_copy.append(arr[i]).append("\n");
                }

                StringSelection strse1 = new StringSelection(content_to_copy.toString());
                clip.setContents(strse1, strse1);
            }
        });

        // Action to create a new template
        create_mew_template.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = create_file();
                String buff = JOptionPane.showInputDialog("How many variables would you like to include in the equation?");
                buff = buff.replaceAll("\\s", "");

                if (!isNumeric(buff) || buff.isEmpty()) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error! Please enter numbers only");
                    return;
                }

                int n = Integer.parseInt(buff);
                int[] new_values = new int[n];

                // Prompt the user to input each variable
                for (int i = 0; i < n; i++) {
                    do {
                        buff = JOptionPane.showInputDialog("Please enter the " + (i + 1) + ":e variable");
                    } while (buff.isEmpty() || !isNumeric(buff));
                    new_values[i] = Integer.parseInt(buff);
                }

                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(file));

                    // Write the new values to the file
                    for (int writing_buffert : new_values) {
                        bf.write(Integer.toString(writing_buffert) + "\n");
                    }

                    bf.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                program.change_equation(new_values);
            }
        });

        // Action to switch to a new template (reload Program)
        switch_template.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program = new Program();
            }
        });

        // Trigger calculation on pressing Enter in the input field
        input_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate_button.doClick();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Diophantine equation solver");
        frame.setContentPane(new MainFrame().main_panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Create a new file using FileDialog
    public File create_file() {
        FileDialog dialog = new FileDialog((Frame) null, "Create a new .txt file");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        return new File(dialog.getDirectory(), dialog.getFile());
    }
}
