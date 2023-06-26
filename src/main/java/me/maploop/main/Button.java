package me.maploop.main;

import me.maploop.util.SUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class Button extends JButton {
    private final Rectangle rv = new Rectangle();

    @Override
    public void updateUI() {
        super.updateUI();
        setBorder(new EmptyBorder(1, 3, 1, 3));
    }

    public Button(String text, Consumer<Button> handler) {
        setText(text);
        setBackground(SUtil.hex2Rgb("#ae00ff"));
        setFont(getFont().deriveFont(Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handler.accept(Button.this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setBackground(SUtil.hex2Rgb("#c753fc"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setBackground(SUtil.hex2Rgb("#ae00ff"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(SUtil.hex2Rgb("#7500ab"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(SUtil.hex2Rgb("#ae00ff"));
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        getBounds(rv);
        var g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(rv.x, rv.y, rv.width, rv.height, 8, 8);
        super.paintComponent(g);
    }
}
