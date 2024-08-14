package com.example.demoProject.Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame implements ActionListener {

    private static final int TILE_SIZE = 30;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int INITIAL_LENGTH = 3;
    private static final int DELAY = 100;

    private ArrayList<Point> snake;
    private Point fruit;
    private char direction;
    private boolean gameOver;
    private Timer timer; // Timer for game loop

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setFocusable(true);

        initializeGame();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                }
            }
        });

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initializeGame() {
        snake = new ArrayList<>();
        for (int i = INITIAL_LENGTH - 1; i >= 0; i--) {
            snake.add(new Point(5, i + 5));
        }
        direction = 'R';
        spawnFruit();
        gameOver = false;
    }

    private void spawnFruit() {
        Random rand = new Random();
        fruit = new Point(rand.nextInt(WIDTH / TILE_SIZE), rand.nextInt(HEIGHT / TILE_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moveSnake();
            checkCollisions();
            repaint();
        }
    }

    private void moveSnake() {
        Point head = new Point(snake.get(0));

        switch (direction) {
            case 'U':
                head.translate(0, -1);
                break;
            case 'D':
                head.translate(0, 1);
                break;
            case 'L':
                head.translate(-1, 0);
                break;
            case 'R':
                head.translate(1, 0);
                break;
        }

        snake.add(0, head);
        if (head.equals(fruit)) {
            spawnFruit(); // Eat fruit
        } else {
            snake.remove(snake.size() - 1); // Remove the tail
        }
    }

    private void checkCollisions() {
        Point head = snake.get(0);

        // Check if snake has collided with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                return;
            }
        }

        // Check if snake has hit the boundaries
        if (head.x < 0 || head.x >= WIDTH / TILE_SIZE || head.y < 0 || head.y >= HEIGHT / TILE_SIZE) {
            gameOver = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString("Game Over", WIDTH / 4, HEIGHT / 2);
            return;
        }

        g2d.setColor(Color.BLACK);
        for (Point p : snake) {
            g2d.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        g2d.setColor(Color.BLACK);
        g2d.fillRect(fruit.x * TILE_SIZE, fruit.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
