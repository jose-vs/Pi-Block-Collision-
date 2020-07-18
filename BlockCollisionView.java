import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.lang.Math;

public class BlockCollisionView extends JPanel{
	
	int collisionCount = 0;
	final double timeSteps = 1000000;
	
	File clack = new File("clack.wav");
	Timer timer = new Timer(5, new bounceListener()); 
	Block block1 = new Block(200, 50, 0); 
	Block block2 = new Block(500, 150, -1/timeSteps);
	
	JLabel title, numOfCollisions, digitsLabel, errorMessage, m1Details, m2Details;
	JButton startBtn, restartBtn; 
	JTextField amountOfDigits; 
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		 
		 g.setColor(new Color(255,255,255));
		 g.drawLine(100, 0, 100, 360);
		 g.drawLine(100, 360, 980, 360);
		 g.fillRect((int)block1.getX(),(int)block1.getY(),(int)block1.getWidth(),(int)block1.getWidth()); 
		 g.fillRect((int)block2.getX(),(int)block2.getY(),(int)block2.getWidth(),(int)block2.getWidth()); 
		 
	}
	
	public BlockCollisionView() {
		
		title = new JLabel("Finding Pi Through Block Collision"); 
		title.setBounds(200, 40, 1000, 100);
		title.setFont(new Font("Arial", Font.PLAIN, 42));
		title.setForeground(new Color(255,255,255));

		numOfCollisions = new JLabel("#" +  collisionCount); 
		numOfCollisions.setBounds(200, 100, 200, 100);
		numOfCollisions.setFont(new Font("Arial", Font.PLAIN, 22));
		numOfCollisions.setForeground(new Color(255,255,255));
		
		digitsLabel = new JLabel("Amount of Digits of PI to uncover?");
		digitsLabel.setBounds(100, 300, 500, 200);
		digitsLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		digitsLabel.setForeground(new Color(255,255,255));
		
		m1Details = new JLabel("<html>Block 1 <br>" + "Mass: " + block1.getMass() + "<br>" + 
				"Velocity: " + String.format("%.4f", (block1.getVelocity()*10000000)) + "</html>");
		m1Details.setBounds(100, 500, 500, 200);
		m1Details.setFont(new Font("Arial", Font.PLAIN, 22));
		m1Details.setForeground(new Color(255,255,255));
		
		m2Details = new JLabel("<html>Block 2 <br>" + "Mass: " + "<br>" + 
				"Velocity: " + String.format("%.4f",block2.getVelocity()*10000000) + "</html>");
		m2Details.setBounds(400, 500, 500, 200);
		m2Details.setFont(new Font("Arial", Font.PLAIN, 22));
		m2Details.setForeground(new Color(255,255,255));
		
		
		errorMessage = new JLabel("Only Enter Numerical Values With No Decimals!");
		errorMessage.setBounds(100, 430, 500, 200);
		errorMessage.setFont(new Font("Arial", Font.PLAIN, 22));
		errorMessage.setForeground(new Color(255,255,255));
		errorMessage.setVisible(false);
		
		
		startBtn = new JButton("Start"); 
		startBtn.setBounds(270, 430, 100, 25);
		startBtn.addActionListener(new StartListener());
		
		restartBtn = new JButton("Stop"); 
		restartBtn.setBounds(270, 460, 100, 25);
		restartBtn.addActionListener(new StopListener());
		
		amountOfDigits = new JTextField(10); 
		amountOfDigits.setBounds(100,430,100,25);
		amountOfDigits.addActionListener(new StartListener());
		
		
		
		add(title);
		add(numOfCollisions);
		add(startBtn); 
		add(restartBtn); 
		add(digitsLabel);
		add(amountOfDigits); 
		add(m1Details); 
		add(m2Details); 
		add(errorMessage); 
		
		setLayout(null);
		setPreferredSize(new Dimension(980,720)); 
		setBackground(new Color(50, 50, 50)); 
		
		repaint(); 
	}
	
	
	public void playClack(File sound) {
		
		try {
			
			Clip clip = AudioSystem.getClip(); 
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
		} catch (Exception e ) { 
			
			System.err.println("file not found"); 
		}
		
	}
	
	public class bounceListener implements ActionListener { 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			boolean playClack = false; 
			
			for (int i = 0; i < timeSteps; i++) { 
				
				if (block1.blockCollision(block2)) {
					
					double v1 = block1.blockBounce(block2); 
					double v2 = block2.blockBounce(block1);
					
					block1.setVelocity(v1);
					block2.setVelocity(v2);
					
					playClack = true; 
					collisionCount++;
				
				} else if (block1.blockHitsWall()) {
					
					block1.reverse(); 
					
					collisionCount++;
					playClack = true; 
				}
				
				block1.xUpdate();
				block2.xUpdate();
			}
			
			if (playClack) { 
				playClack(clack); 
			}
			
			numOfCollisions.setText("#" +  collisionCount);
			
			m1Details.setText("<html>Block 1 <br>" + "Mass: " + block1.getMass() + "<br>" + 
				"Velocity: " + String.format("%.4f", (block1.getVelocity()*10000000)) + "</html>");
			m2Details.setText("<html>Block 2 <br>" + "Mass: " + block2.getMass() + "<br>" + 
					"Velocity: " + String.format("%.4f", (block2.getVelocity()*10000000)) + "</html>");
			
			repaint(); 
			
		}

	}
	
	public class StartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				
				block2.setMass(Math.pow(100, Integer.parseInt(amountOfDigits.getText())-1));
				errorMessage.setVisible(false);
				restartSetup();
				
				timer.start();
				
			} catch (NumberFormatException o) {
				
				errorMessage.setVisible(true);
				
			}

		} 
		
	}
	
	public class StopListener implements ActionListener { 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			timer.stop(); 
			
			restartSetup();
			
		}
	}
	
	public void restartSetup()  { 
		
		collisionCount = 0; 
		block1.setX(200);
		block1.setVelocity(0);
		block2.setX(500);
		block2.setVelocity(-1/timeSteps);
		repaint();
	}
	

}