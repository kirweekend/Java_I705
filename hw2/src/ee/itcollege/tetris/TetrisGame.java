package ee.itcollege.tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TetrisGame extends Application {

	public static final int FIELD_HEIGHT = 30;
	public static final int FIELD_WIDHT = 15;

	FigureGenerator figureGenerator = new FigureGenerator();
	Figure figure = figureGenerator.createFigure();
	Figure nextFigure = figureGenerator.createFigure();
	ArrayList<Shape> gameField = new ArrayList<>();
	ArrayList<Block> fallenBlocks = new ArrayList<>();
	Pane layout = new Pane();

	private void createField() {
		// left
		Rectangle rectangle = new Rectangle(
				0, 0, Block.SIZE, FIELD_HEIGHT * Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		// right
		rectangle = new Rectangle(
				(FIELD_WIDHT - 1) * Block.SIZE, 0, Block.SIZE * 11, FIELD_HEIGHT * Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		
		// bottom
		rectangle = new Rectangle(
				0, (FIELD_HEIGHT - 1) * Block.SIZE, FIELD_WIDHT * Block.SIZE, Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		
		layout.getChildren().addAll(gameField);
		

	}
	
	private void createNewFigure() {
		Platform.runLater(() -> {
			gameField.addAll(figure);
			fallenBlocks.addAll(figure);
			Collections.sort(fallenBlocks);
			figure = nextFigure;
			figure.move(-5 - FIELD_WIDHT / 2, 0);
			nextFigure = figureGenerator.createFigure();
			nextFigure.move(FIELD_WIDHT + 5, 1);
			layout.getChildren().addAll(nextFigure);
		});
	}
	
	
	@Override
	public void start(Stage window) throws Exception {
		createField();
		createNewFigure();
		createNewFigure();
		
		Label label = new Label("some text");
		label.setLayoutX(100);
		label.setLayoutY(100);
		label.setFont(new Font(Font.getFamilies().get(0), 50));
		layout.getChildren().add(label);
		
		Scene scene = new Scene(layout,
				Block.SIZE * (FIELD_WIDHT + 10),
				Block.SIZE * FIELD_HEIGHT);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!figure.moveIfDidntCollide(gameField, 0, 1)) {
					createNewFigure();
				}
			}
		}, 400, 400);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);

		window.setResizable(false);
		window.setOnCloseRequest(e -> System.exit(0));
		window.setScene(scene);
		window.show();
	}
	
	private void handleKeyPress(KeyEvent event) {
		switch (event.getCode()) {
		case RIGHT:
			figure.moveIfDidntCollide(gameField, 1, 0); break;
		case LEFT:
			figure.moveIfDidntCollide(gameField, -1, 0); break;
		case UP:
			figure.rotateIfNotCollides(gameField); break;
		case DOWN:
			figure.moveIfDidntCollide(gameField, 0, 1); break;
		case SPACE:
			while (figure.moveIfDidntCollide(gameField, 0, 1))
				;
			createNewFigure();
			break;
		
		case ESCAPE:
			System.exit(0);
		
		default:
		}
	}
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}

}
