package com.xhh.demo.http.practice.algorithms.util;

/**
 * DrawListener
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-3-27
 * @since 1.6
 */
public interface DrawListener {

    void mousePressed(double x, double y);

    void mouseDragged(double x, double y);

    void mouseReleased(double x, double y);

    void keyTyped(char c);
}
