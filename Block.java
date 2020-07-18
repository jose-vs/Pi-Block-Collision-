
public class Block {
	
	private double x; 
	private int y; 
	private int width; 
	private double velocity; 
	private double mass; 
	
	public Block(double x, int width, double velocity) {
		this.setX(x);
		this.setWidth(width);
		this.setY(360-this.getWidth());
		this.setVelocity(velocity);
		this.setMass(1);
	}

	public void xUpdate() { 
		this.setX(this.getX() + this.getVelocity());
		
	}
	
	public boolean blockHitsWall() { 
		return (this.getX() <= 100);
	}
	
	public void reverse() { 
		this.setVelocity(this.getVelocity() * -1);
	}
	
	public boolean blockCollision(Block b) {
		return !(this.getX() + this.getWidth() < b.getX() ||
				this.getX() > b.getX() + b.getWidth());
	}
	
	public double blockBounce(Block b) { 
		double massSum = this.getMass() + b.getMass();
		double velUpdate = (double)((this.getMass() - b.getMass()) / massSum) * this.getVelocity()
				+((2 * b.getMass())  / massSum) * b.getVelocity();
		
		return velUpdate;
		
	}
	
	public double getMass() {
		return mass;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) { 
		this.width = width; 
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
		
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}

}