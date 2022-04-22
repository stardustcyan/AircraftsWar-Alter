package edu.hitsz.application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame menuFrame = new JFrame("开始选项");
        GameStartOption menuObj = new GameStartOption();
        menuFrame.setContentPane(menuObj.diffPanel);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.pack();

        Object lock = new Object();

        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Runnable rGame = () -> {
            synchronized (lock) {
                try {
                    lock.wait();
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Game game = new Game();
            frame.add(game);
            frame.setVisible(true);
            game.action();
        };

        Runnable rMenu = () -> {
            menuFrame.setVisible(true);
            menuObj.easyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    synchronized (lock) {
                        ImageManager.diffBG = "src/images/bg.jpg";
                        try {
                            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(ImageManager.diffBG));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println(ImageManager.diffBG);
                        menuFrame.setVisible(false);
                        lock.notify();
                    }
                }
            });
            menuObj.normalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    synchronized (lock) {
                        ImageManager.diffBG = "src/images/bg2.jpg";
                        try {
                            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(ImageManager.diffBG));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println(ImageManager.diffBG);
                        menuFrame.setVisible(false);
                        lock.notify();
                    }
                }
            });
            menuObj.hardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    synchronized (lock) {
                        ImageManager.diffBG = "src/images/bg3.jpg";
                        try {
                            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(ImageManager.diffBG));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println(ImageManager.diffBG);
                        menuFrame.setVisible(false);
                        lock.notify();
                    }
                }
            });
        };

        Thread menuThread = new Thread(rMenu, "Menu");
        Thread gameThread = new Thread(rGame, "Game");
        gameThread.start();
        menuThread.start();
    }
}
