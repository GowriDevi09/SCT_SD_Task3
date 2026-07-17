import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;

public class SudokuGUI extends JFrame {

    private JTextField[][] cells = new JTextField[9][9];
    private int[][] originalBoard = new int[9][9];

    public SudokuGUI() {

        setTitle("🧩 Sudoku Solver - Java Swing");
        setSize(650, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        JPanel grid = new JPanel(new GridLayout(9,9));

        Font font = new Font("Segoe UI", Font.BOLD, 26);

        for(int i=0;i<9;i++){

            for(int j=0;j<9;j++){

                JTextField tf = new JTextField();

                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(font);

                // Allow only one digit
                ((AbstractDocument)tf.getDocument())
                        .setDocumentFilter(new DigitFilter());

                // Alternate colors for 3x3 boxes
                if(((i/3)+(j/3))%2==0)
                    tf.setBackground(new Color(245,245,245));
                else
                    tf.setBackground(Color.WHITE);

                // Thick borders around each 3×3 box
                int top=(i%3==0)?3:1;
                int left=(j%3==0)?3:1;
                int bottom=(i==8)?3:1;
                int right=(j==8)?3:1;

                tf.setBorder(new MatteBorder(top,left,bottom,right,Color.BLACK));

                cells[i][j]=tf;
                final int row = i;
final int col = j;

tf.addActionListener(e -> {
    int nextRow = row;
    int nextCol = col + 1;

    if (nextCol == 9) {
        nextCol = 0;
        nextRow++;
    }

    if (nextRow < 9) {
        cells[nextRow][nextCol].requestFocus();
    }
});
                grid.add(tf);
                tf.setPreferredSize(new Dimension(60,60));
            }
        }

        JButton solve=new JButton("Solve");
        JButton clear=new JButton("Clear");
        JButton reset=new JButton("Reset");

        JPanel buttons=new JPanel();

        buttons.add(solve);
        buttons.add(clear);
        buttons.add(reset);
solve.setBackground(new Color(46,204,113));
solve.setForeground(Color.WHITE);

clear.setBackground(new Color(231,76,60));
clear.setForeground(Color.WHITE);

reset.setBackground(new Color(52,152,219));
reset.setForeground(Color.WHITE);
        solve.addActionListener(e->solveBoard());
        clear.addActionListener(e->clearBoard());
        reset.addActionListener(e->resetBoard());

       JPanel center = new JPanel(new GridBagLayout());
center.setBackground(new Color(240,248,255));
center.add(grid);

add(center, BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);
    }

    private void solveBoard(){

        int[][] board=new int[9][9];

        try{

            for(int i=0;i<9;i++){

                for(int j=0;j<9;j++){

                    String s=cells[i][j].getText().trim();

                    if(s.isEmpty())
                        board[i][j]=0;
                    else
                        board[i][j]=Integer.parseInt(s);

                    originalBoard[i][j]=board[i][j];
                }
            }

            if(SudokuSolver.solve(board)){

                for(int i=0;i<9;i++){

                    for(int j=0;j<9;j++){

                        cells[i][j].setText(String.valueOf(board[i][j]));

                        if(originalBoard[i][j]==0){

                            cells[i][j].setForeground(Color.BLUE);

                        }else{

                            cells[i][j].setForeground(Color.BLACK);
                            cells[i][j].setEditable(false);

                        }
                    }
                }

                JOptionPane.showMessageDialog(this,"Sudoku Solved!");

            }else{

                JOptionPane.showMessageDialog(this,"No Solution Exists.");

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(this,"Enter numbers between 1 and 9 only.");
        }
    }

    private void clearBoard(){

        for(int i=0;i<9;i++){

            for(int j=0;j<9;j++){

                cells[i][j].setText("");
                cells[i][j].setEditable(true);
                cells[i][j].setForeground(Color.BLACK);

            }
        }
    }

    private void resetBoard(){

        for(int i=0;i<9;i++){

            for(int j=0;j<9;j++){

                if(originalBoard[i][j]==0)
                    cells[i][j].setText("");
                else
                    cells[i][j].setText(String.valueOf(originalBoard[i][j]));

                cells[i][j].setEditable(true);
                cells[i][j].setForeground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(()->
                new SudokuGUI().setVisible(true));

    }

    // Only allows digits 1-9
    class DigitFilter extends DocumentFilter{

        @Override
        public void replace(FilterBypass fb,int offset,int length,
                            String text,AttributeSet attrs)
                throws BadLocationException{

            if(text.matches("[1-9]?") &&
                    fb.getDocument().getLength()+text.length()-length<=1){

                super.replace(fb,offset,length,text,attrs);
            }
        }
    }
}