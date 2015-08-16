import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;

public class SudokuSolverGUI {

	private JFrame frame;
	private JTable table;
	private boolean SOLVED = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuSolverGUI window = new SudokuSolverGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SudokuSolverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		table = new JTable(9,9);
		frame.getContentPane().add(table);

		JButton btnSolve = new JButton("Solve!");
		btnSolve.addMouseListener(new buttonListner());
		frame.getContentPane().add(btnSolve);
		table.getModel().addTableModelListener(new tableListner());
	}

	private class buttonListner implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Solving!");

			int predefCellCount = 0;

			for(int i = 0; i < table.getRowCount(); ++i){
				for(int j = 0; j < table.getColumnCount(); ++j){
					if(table.getValueAt(i, j) != null)
						++predefCellCount;
				}
			}

			if(predefCellCount < 17){
				System.out.println("Cannot Solve! Too few values provided!");
			}
			else{
				main solver = new main();
				SudokuCell[][] sudoku = new SudokuCell[table.getRowCount()][table.getColumnCount()];

				for(int i = 0; i < table.getRowCount(); ++i){
					for(int j = 0; j < table.getColumnCount(); ++j){
						sudoku[i][j] = new SudokuCell();
					}
				}
				
				for(int i = 0; i < table.getRowCount(); ++i){
					for(int j = 0; j < table.getColumnCount(); ++j){
						if(table.getValueAt(i, j) != null){
							int num = Integer.parseInt((String) table.getValueAt(i, j));
							sudoku = solver.setNum(sudoku, num, i, j);
						}
					}
				}

				sudoku = solver.solve(sudoku);
				
				SOLVED = true;

				for(int i = 0; i < table.getRowCount(); ++i){
					for(int j = 0; j < table.getColumnCount(); ++j){
						table.setValueAt(sudoku[i][j].getCellVal(), i, j);
					}
				}
			}


		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class tableListner implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			
			if (SOLVED) return;
			
			int row = e.getLastRow();
			int col = e.getColumn();

			if(table.getValueAt(row, col) != null && (Integer.parseInt((String) table.getValueAt(row, col)) > 9 || Integer.parseInt((String) table.getValueAt(row, col)) < 1)){
				table.setValueAt(null, row, col);
			}

		}

	}

}
