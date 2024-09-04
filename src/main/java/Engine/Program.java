package Engine;

import GUI.MainFrame;

import javax.swing.*;

public class Program extends JFrame {
    private int total_amount_of_welds = 16;
    private int total_weld_prices = 2908;
    private int weld_prices[] = {255, 278, 348,395, 56, 61, 65, 69, 244, 278, 285, 299, 62, 65, 70, 78};
    private int[] amount_of_welds;

    public DefaultListModel<String> solve_diophantine(int total_amount){
        amount_of_welds = new int[total_amount_of_welds];
        int average_amount_of_welds = total_amount / total_weld_prices;
        int range_min = average_amount_of_welds;
        int range_max = average_amount_of_welds + 2;

        DefaultListModel<String> buff = new DefaultListModel<>();

        for (amount_of_welds[0] = range_min; amount_of_welds[0] <= range_max; amount_of_welds[0]++) {
            for (amount_of_welds[1] = range_min; amount_of_welds[1] <= range_max; amount_of_welds[1]++) {
                for (amount_of_welds[2] = range_min; amount_of_welds[2] <= range_max; amount_of_welds[2]++) {
                    for (amount_of_welds[3] = range_min; amount_of_welds[3] <= range_max; amount_of_welds[3]++) {
                        for (amount_of_welds[4] = range_min; amount_of_welds[4] <= range_max; amount_of_welds[4]++) {
                            for (amount_of_welds[5] = range_min; amount_of_welds[5] <= range_max; amount_of_welds[5]++) {
                                for (amount_of_welds[6] = range_min; amount_of_welds[6] <= range_max; amount_of_welds[6]++) {
                                    for (amount_of_welds[7] = range_min; amount_of_welds[7] <= range_max; amount_of_welds[7]++) {
                                        for (amount_of_welds[8] = range_min; amount_of_welds[8] <= range_max; amount_of_welds[8]++) {
                                            for (amount_of_welds[9] = range_min; amount_of_welds[9] <= range_max; amount_of_welds[9]++) {
                                                for (amount_of_welds[10] = range_min; amount_of_welds[10] <= range_max; amount_of_welds[10]++) {
                                                    for (amount_of_welds[11] = range_min; amount_of_welds[11] <= range_max; amount_of_welds[11]++) {
                                                        for (amount_of_welds[12] = range_min; amount_of_welds[12] <= range_max; amount_of_welds[12]++) {
                                                            for (amount_of_welds[13] = range_min; amount_of_welds[13] <= range_max; amount_of_welds[13]++) {
                                                                for (amount_of_welds[14] = range_min; amount_of_welds[14] <= range_max; amount_of_welds[14]++) {
                                                                    for (amount_of_welds[15] = range_min; amount_of_welds[15] <= range_max; amount_of_welds[15]++) {
                                                                        int sum = 0;
                                                                        for (int i = 0; i < 16; i++) {
                                                                            sum += weld_prices[i] * amount_of_welds[i];
                                                                        }
                                                                        if (sum == total_amount) {
                                                                            for (int i = 0; i < 16; i++) {
                                                                                buff.addElement(amount_of_welds[i] + "st av " + weld_prices[i] + " EURO");
                                                                            }
                                                                            return buff;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return buff;
    }

    public int[] get_amount_of_welds(){
        return amount_of_welds;
    }


}


