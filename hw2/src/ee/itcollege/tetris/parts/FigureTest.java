package ee.itcollege.tetris.parts;

import static org.junit.Assert.*;

import org.junit.Test;

public class FigureTest {
	
	private static final double PRECISION = .00001;

	@Test
	public void testMove() {
		
		Figure figure = new Figure();
		Block block = new Block(0, 0);
		figure.add(block);
		
		assertEquals(0, block.getX(), PRECISION);
		assertEquals(0, block.getY(), PRECISION);
		
		figure.move(1, 0);
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(0 * Block.SIZE, block.getY(), PRECISION);
		
		figure.move(0, 2);
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.move(3, 7);
		assertEquals(4 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(9 * Block.SIZE, block.getY(), PRECISION);
		
		figure.move(-2, -1);
		assertEquals(2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(8 * Block.SIZE, block.getY(), PRECISION);
		
		figure.move(0, -11);
		assertEquals( 2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-3 * Block.SIZE, block.getY(), PRECISION);
	}
	
	@Test
	public void testRotateZero() {
		Figure figure = new Figure();
		figure.add(new Block(0, 0));
		Block block = new Block(1, 2);
		figure.add(block);
		
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(-2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals( 1 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(-1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals( 2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-1 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(2 * Block.SIZE, block.getY(), PRECISION);
	}
	
	@Test
	public void testRotateCenter() {
		Figure figure = new Figure();
		figure.add(new Block(3, -1));
		Block block = new Block(1, 2);
		figure.add(block);
		
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals( 1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals( 0 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals( 2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-3 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals( 5 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(2 * Block.SIZE, block.getY(), PRECISION);
		
	}

}





