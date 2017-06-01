package ee.itcollege.tetris.parts;

import java.util.ArrayList;

import ee.itcollege.tetris.lib.CollisionDetector;
import javafx.scene.shape.Shape;

@SuppressWarnings("serial")
public class Figure extends ArrayList<Block> {
	
	public double getCenterX() {
		return getCenterBlock().getX();
	}
	
	public double getCenterY() {
		return getCenterBlock().getY();
	}
	
	private Block getCenterBlock() {
		return get(0);
	}

	public void move(int deltaX, int deltaY) {
		for (Block block : this) {
			block.setX(block.getX() + deltaX * Block.SIZE);
			block.setY(block.getY() + deltaY * Block.SIZE);
		}
	}

	public void rotate() {
		double centerX = getCenterX();
		double centerY = getCenterY();
		
		for (Block block : this) {
			double x = block.getX() - centerX;
			double y = block.getY() - centerY;
			
			block.setX(-y + centerX);
			block.setY(x + centerY);
		}
	}

	public boolean moveIfDidntCollide(ArrayList<Shape> field, int x, int y) {
		move(x, y);
		if (CollisionDetector.collide(this, field)) {
			move(-x, -y);
			return false;
		}
		return true;
	}

	public void rotateIfNotCollides(ArrayList<Shape> field) {
		rotate();
		if (CollisionDetector.collide(this, field)) {
			for (int i = 0; i < 3; i++) {
				rotate();
			}
		}
		
	}
	
}





