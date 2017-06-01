package ee.itcollege.tetris.lib;

import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

import java.util.Random;

public class FigureGenerator {

	public Figure createFigure() {

		Figure figure = new Figure();
		int first = (int) Math.round(Math.random());
		Random rn = new Random();
		int second = rn.nextInt(4) + 1;
		int third = rn.nextInt(3) + 1;

		figure.add(new Block(0, 0));
		figure.add(new Block(0, 1));

		if (first == 0) {
			figure.add(new Block(0, 2));
			if (third == 1) {
				figure.add(new Block(1, 2));
			}
			else if (third == 2){
				figure.add(new Block(0, 3));
			}
			else {
				figure.add(new Block(-1, 2));
			}

		}

		else {
			figure.add(new Block(1, 1));
			if (second == 1){
				figure.add(new Block(-1, 0));
			}
			else if (second == 2){
				figure.add(new Block(1, 0));
			}
			else if (second == 3){
				figure.add(new Block(1, 2));
			}
			else {
				figure.add(new Block(0, 2));
			}
		}

		return figure;
	}

}
