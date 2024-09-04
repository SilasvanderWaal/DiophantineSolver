package Engine;

import GUI.MainFrame;

import javax.swing.*;

public class Program extends JFrame {
    private int[] weld_prices = {255, 278, 348,395, 56, 61, 65, 69, 244, 278, 285, 299, 62, 65, 70, 78};
    private int[] amount_of_welds;
    private DefaultListModel<String> buff;
    private int total_weld_prices = 2908;

    public DefaultListModel<String> solve_diophantine(int total_amount){
        int total_amount_of_welds = weld_prices.length;
        amount_of_welds = new int[total_amount_of_welds];
        System.out.println(total_weld_prices);
        int average_amount_of_welds = total_amount / total_weld_prices;
        int range_min = average_amount_of_welds;
        int range_max = average_amount_of_welds + 4;
        buff = new DefaultListModel<>();

        if(solveDiophantine(weld_prices, total_amount_of_welds, total_amount, amount_of_welds, 0, total_amount, range_min, range_max )){
            return buff;
        }

        return buff;
    }

    // Rekursiv funktion för att hitta heltalslösningar till en diofantisk ekvation
    private boolean solveDiophantine(int[] a, int n, int b, int[] x, int index, int remaining, int range_min, int range_max) {
        if (index == n) {
            if (remaining == 0) {
                for (int i = 0; i < n; i++) {
                        buff.addElement(x[i] + "st av " + a[i] + " EURO");
                }
                return true;
            }
            return false;
        }

        for (int i = range_min; i <= range_max; i++) {
            x[index] = i;
            if (solveDiophantine(a, n, b, x, index + 1, remaining - a[index] * i, range_min, range_max)) {
                return true ;
            }
        }

        return false;
    }

    public void change_equation(int[] weld_prices){
        int sum = 0;
        this.weld_prices = weld_prices;


        for (int i = 0; i < weld_prices.length; i++) {
            sum += weld_prices[i];
        }

        total_weld_prices = sum;
    }

    public int[] get_amount_of_welds(){
        return amount_of_welds;
    }
}


