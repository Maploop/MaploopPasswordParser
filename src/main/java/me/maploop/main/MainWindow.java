package me.maploop.main;

import me.maploop.util.SUtil;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private JPanel panel;
    public static List<Button> buttons = new ArrayList<>();
    public static List<JLabel> labels = new ArrayList<>();

    public static MainWindow instance;

    public MainWindow() {
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Maploop Password Manager");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(SUtil.hex2Rgb("#212121"));
        Button btn = new Button("Save New", (e) -> {
            new FormWindow(null, null);
        });
        btn.setBounds(450, 500, 100, 40);
        panel.add(btn);

        refresh();

        setContentPane(panel);
        setVisible(true);
        instance = this;
    }

    public void refresh() {
        for (Button b : buttons) {
            panel.remove(b);
        }
        for (JLabel l : labels) {
            panel.remove(l);
        }

        JSONObject allPws = DataRetriever.fetchAll();
        int y = 10;
        for (Object k : allPws.keySet()) {
            String name = (String) ((JSONObject) allPws.get(k)).getOrDefault("title", "NO TITLE");
            String username = (String) ((JSONObject) allPws.get(k)).getOrDefault("username", "nouser");
            String pw = (String) ((JSONObject) allPws.get(k)).getOrDefault("password", "nopw");

            JLabel title = new JLabel(name);
            Button copyUsername = new Button("Copy Username", (e) -> {
                StringSelection selection = new StringSelection(username);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                e.setBackground(Color.GREEN);
                e.setText("Copied!");

                new Thread(() -> {
                    try { Thread.sleep(3000); } catch (Exception ignored) { }
                    e.setBackground(SUtil.hex2Rgb("#ae00ff"));
                    e.setText("Copy Username");
                });
            });
            Button copyPw = new Button("Copy Password", (e) -> {
                StringSelection selection = new StringSelection(pw);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                e.setBackground(Color.GREEN);
                e.setText("Copied!");

                new Thread(() -> {
                    try { Thread.sleep(3000); } catch (Exception ignored) { }
                    e.setBackground(SUtil.hex2Rgb("#ae00ff"));
                    e.setText("Copy Password");
                });
            });
            Button edit = new Button("Edit", (e) -> {
                new FormWindow(k.toString(), (JSONObject) allPws.get(k));
            });
            int x = 20;
            title.setBounds(x, y, 200, 50);
            copyUsername.setBounds(x += 250, y, 100, 30);
            copyPw.setBounds(x += 120, y, 100, 30);
            edit.setBounds(x + 120, y, 50, 30);
            title.setForeground(Color.WHITE);
            title.setFont(title.getFont().deriveFont(Font.PLAIN, 16));
            labels.add(title);
            buttons.add(copyPw);
            buttons.add(copyUsername);
            buttons.add(edit);

            panel.add(title);
            panel.add(copyUsername);
            panel.add(copyPw);
            panel.add(edit);

            y += 50;
        }

        update(getGraphics());
    }
}
