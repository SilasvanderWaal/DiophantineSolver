package GUI;

import Engine.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;

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
    private DefaultListModel<String> result_list_input;

    public MainFrame(){
        Program program = new Program();

        calculate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountBuff = input_field.getText();
               amountBuff = amountBuff.replaceAll("\\s","");

                if(amountBuff.isEmpty()){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Enter a value","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(isNumeric(amountBuff) == false){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Enter a number, not text","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int amount = Integer.parseInt(amountBuff);
                result_list.setModel(program.solve_diophantine(amount));
            }
        });
        copy_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                String content_to_copy = "";
                int[] arr = program.get_amount_of_welds();

                for (int i = 0; i < arr.length; i++){
                    content_to_copy += arr[i] + "\n";
                }

                StringSelection strse1 = new StringSelection(content_to_copy);
                clip.setContents(strse1, strse1);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Salary to welds converter");
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
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
