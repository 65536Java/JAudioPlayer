package javax.swing.gui;

import faces.Interface;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableJProgressBar extends JProgressBar {
    private int initialMouseValue;
    boolean clickable = true;
    Interface i2;
    boolean pressed = false;
    final MouseAdapter MA = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!clickable) return;
            int mouseX = e.getX();
            int width = getWidth();
            int newValue = (int) Math.round((double) mouseX / width * (getMaximum() - getMinimum()) + getMinimum());
            setValue(newValue);
            i2.action(getValue());
            pressed = true;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if(!clickable) return;
            initialMouseValue = getValueFromMouseEvent(e);
            pressed = true;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(!clickable) return;
            setValue(getValueFromMouseEvent(e));
            i2.action(getValue());
        }
        @Override
        public void mouseReleased(MouseEvent e){
            pressed = false;
        }
    };

    public ClickableJProgressBar(Interface ac) {
        super();
        i2 = ac;
        addMouseListener(MA);
    }

    private int getValueFromMouseEvent(MouseEvent e) {
        if (getOrientation() == JProgressBar.HORIZONTAL) {
            int mouseX = e.getX();
            int width = getWidth();
            return (int) Math.round((double) mouseX / width * (getMaximum() - getMinimum()) + getMinimum());
        } else {
            int mouseY = e.getY();
            int height = getHeight();
            return (int) Math.round((double) (height - mouseY) / height * (getMaximum() - getMinimum()) + getMinimum());
        }
    }

    public void setClickable(boolean c) {
        clickable = c;
    }
    public boolean getClickable(){
        return clickable;
    }
}