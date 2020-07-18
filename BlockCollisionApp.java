import javax.swing.JFrame;

public class BlockCollisionApp {
	
	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("Pi Block Collision"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BlockCollisionView()); 
		frame.setResizable(false);
		frame.pack(); 
		frame.setVisible(true);
		
	}

}
