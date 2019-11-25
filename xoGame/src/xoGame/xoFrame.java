package xoGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class xoFrame extends JFrame
{
	private char whoseTurn = 'X';
	private boolean gameOver = false;
	
	private Cell[][] cells = new Cell[3][3];
	
	JLabel jlblStatus = new JLabel ("X's Turn to play");
	
	
	public xoFrame () {
		
		JPanel panel = new JPanel (new GridLayout(3, 3, 0, 0));
		for (int i=0; i<3 ; i++) 
			for(int j=0; j<3; j++)
				panel.add(cells[i][j]=new Cell());
		panel.setBorder(new LineBorder(Color.blue,1));
		jlblStatus.setBorder(new LineBorder(Color.GRAY,1));
		
		add(panel,BorderLayout.CENTER);
		add(jlblStatus, BorderLayout.SOUTH);
		
	}
	public boolean isFull()
    {
       for (int i = 0; i < 3; i++)
           for (int j = 0; j < 3; j++)
               if (cells[i][j].getToken() == ' ')
                   return false;
       return true;
    }
	public boolean isWon(char token) {
		for (int i = 0; i < 3; i++)
	           if ((cells[i][0].getToken() == token)
	                   && (cells[i][1].getToken() == token)
	                   && (cells[i][2].getToken() == token))
	           {
	               return true;
	           }
		if ((cells[0][0].getToken() == token)
	               && (cells[1][1].getToken() == token)
	               && (cells[2][2].getToken() == token))
	           {
	               return true;
	           }
	 
	       if ((cells[0][2].getToken() == token)
	               && (cells[1][1].getToken() == token)
	               && (cells[2][0].getToken() == token))
	           {
	               return true;
	           }
	 
	       return false;
	   }
	 
	    /**
	    * Defines a cell in a TicTacToe game board.
	    */
	    public class Cell extends JPanel
	    {
	       // token of this cell
	       private char token = ' ';
	 
	       /**
	        * Constructor
	        */
	       public Cell()
	       {
	           setBorder(new LineBorder(Color.black, 1));
	           addMouseListener(new MyMouseListener());
	       }
	 
	       /**
	        * Gets the token of the cell.
	        * @return The token value of the cell.
	        */
	       public char getToken()
	       {
	           return token;
	       }
	 
	       /**
	        * Sets the token of the cell.
	        * @param c Character to use as token value.
	        */
	       public void setToken(char c)
	       {
	           token = c;
	           repaint();
	       }
	 
	       @Override
	       protected void paintComponent(Graphics g)
	       {
	           super.paintComponent(g);
	 
	           if (token == 'X')
	           {
	               g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
	               g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
	           }
	 
	           else if (token == 'O')
	           {
	               g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
	           }
	       }
	 
	       private class MyMouseListener extends MouseAdapter
	       {
	           @Override
	           public void mouseClicked(MouseEvent e)
	           {
	               if (gameOver)
	                   return;
	               
	               // if the cell is empty and the game is not over
	               if (token == ' ' && whoseTurn != ' ')
	                   setToken(whoseTurn);
	 
	               // Check game status
	               if (isWon(whoseTurn))
	               {
	                   jlblStatus.setText(whoseTurn + " won! Game over!");
	                   whoseTurn = ' ';
	                   gameOver = true;
	               }
	               else if (isFull())
	               {
	                   jlblStatus.setText("Tie game! Game over!");
	                   whoseTurn = ' ';
	                   gameOver = true;
	               }
	               else
	               {
	                   whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
	                   jlblStatus.setText(whoseTurn + "'s turn.");
	               }
			
	}
}}}
