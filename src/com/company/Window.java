package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.max;

public class Window implements Runnable {
    private class TimerListener implements ActionListener{
        boolean flop = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            for (int x = 0; x < Config.WIDTH; x++)
                for (int y = 0; y < Config.HEIGHT; y++){
                    if (flop){
                        boxes[x][y].step1();
                    }
                    else {
                        boxes[x][y].step2();
                    }
                }
        }
    }

    private JFrame frame;
    private JButton butStart;
    private JButton butStop;
    private JButton butSpeedUp;
    private JButton butSpeedDown;
    private Box[][] boxes;

    private Timer timer;
    private TimerListener tl;

    @Override
    public void run() {
        initTimer();
        initButton();
        initFrame();
        initBoxes();

    }

    void initButton() {
        butStart = new JButton("Start");
        butStart.setBounds((Config.SIZE * Config.WIDTH) / 2 - 115, Config.SIZE * Config.HEIGHT+20, 105, 30);
        butStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer(timer);
            }

        });
        butStop = new JButton("Stop");
        butStop.setBounds((Config.SIZE * Config.WIDTH) / 2 + 10, Config.SIZE * Config.HEIGHT+20, 105, 30);
        butStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer(timer);
            }
        });
        butSpeedUp = new JButton("Speed Up");
        butSpeedUp.setBounds(Config.SIZE * Config.WIDTH-145, Config.SIZE * Config.HEIGHT+20, 105, 30);
        butSpeedUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speedUp();
            }
        });
        butSpeedDown = new JButton("Speed Down");
        butSpeedDown.setBounds(30, Config.SIZE * Config.HEIGHT+20, 125, 30);
        butSpeedDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speedDown();
            }
        });
    }
    void initFrame() {
        frame = new JFrame();
        frame.add(butStart);
        frame.add(butStop);
        frame.add(butSpeedUp);
        frame.add(butSpeedDown);
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * Config.WIDTH, Config.SIZE * Config.HEIGHT+100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Game of Life");

    }

    void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                Cell cell = new Cell();
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                for (int sx = -1; sx <= +1; sx++)
                    for (int sy = -1; sy <= +1; sy++) {
                        if (!(sx == 0 && sy == 0)) {
                            boxes[x][y].cell.addNear(boxes[(x + sx + Config.WIDTH) % Config.WIDTH][(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);

                        }
                    }

    }
    private void initTimer(){
        TimerListener tl = new TimerListener();
        timer = new Timer(Config.SLEEPMS,tl);
    }
    private void stopTimer(Timer timer){
        timer.stop();
    }
    private void startTimer(Timer timer){
        timer.start();
    }
    private void speedUp() {
        Config.SLEEPMS = max(Config.SLEEPMS - 20, 20);
        timer.setDelay(Config.SLEEPMS);
    }
    private void speedDown() {
        Config.SLEEPMS = Config.SLEEPMS + 20;
        timer.setDelay(Config.SLEEPMS);
    }
}
