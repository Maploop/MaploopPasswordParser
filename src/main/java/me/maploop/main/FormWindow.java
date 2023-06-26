package me.maploop.main;

import me.maploop.util.SUtil;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.UUID;

public class FormWindow extends JFrame {
    private JPanel panel;

    public FormWindow(String id, JSONObject obj) {
        setSize(300, 400);
        setVisible(false);
        panel = new JPanel();
        boolean useJS = obj != null;
        if (useJS) {
            setTitle("Editing " + obj.get("title"));
        } else {
            setTitle("Creating new Password");
        }

        int y = 20;

        panel.setBackground(SUtil.hex2Rgb("#212121"));
        panel.setLayout(null);
        JTextArea area = new JTextArea();
        if (useJS) area.setText(obj.get("title").toString());
        JLabel label = new JLabel("Title");
        label.setBounds(10, y, 200, 20);
        label.setForeground(SUtil.hex2Rgb("#dbdbdb"));
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 24));
        area.setBounds(10, y + 30, 200, 20);
        panel.add(area);
        panel.add(label);
        y += 80;

        panel.setBackground(SUtil.hex2Rgb("#212121"));
        panel.setLayout(null);
        JTextArea area2 = new JTextArea();
        if (useJS) area2.setText(obj.get("username").toString());
        JLabel label2 = new JLabel("Username");
        label2.setBounds(10, y, 200, 20);
        label2.setForeground(SUtil.hex2Rgb("#dbdbdb"));
        label2.setFont(label2.getFont().deriveFont(Font.PLAIN, 24));
        area2.setBounds(10, y + 30, 200, 20);
        panel.add(area2);
        panel.add(label2);
        y += 80;

        panel.setBackground(SUtil.hex2Rgb("#212121"));
        panel.setLayout(null);
        JTextArea area1 = new JTextArea();
        if (useJS) area1.setText(obj.get("password").toString());
        JLabel label1 = new JLabel("Password");
        label1.setBounds(10, y, 200, 20);
        label1.setForeground(SUtil.hex2Rgb("#dbdbdb"));
        label1.setFont(label1.getFont().deriveFont(Font.PLAIN, 24));
        area1.setBounds(10, y + 30, 200, 20);
        panel.add(area1);
        panel.add(label1);

        Button done = new Button("Done", (e) -> {
            JSONObject jsonObject = useJS ? obj : new JSONObject();

            String identification = useJS ? id : UUID.randomUUID().toString().replaceAll("-", "");
            jsonObject.put("title", area.getText());
            jsonObject.put("username", area2.getText());
            jsonObject.put("password", area1.getText());

            DataRetriever.save(identification, jsonObject);
            MainWindow.instance.refresh();
            FormWindow.this.dispose();
        });
        setLocationRelativeTo(null);
            done.setBounds(220, 330, 50, 20);
        panel.add(done);

        setContentPane(panel);

        setVisible(true);
    }
}
