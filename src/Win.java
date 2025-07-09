import faces.Interface;

import javax.swing.*;
import javax.swing.gui.ClickableJProgressBar;
import java.awt.*;
import java.io.File;

public class Win extends JFrame {
    File msc = null;
    Player plr = new Player();
    boolean loop = false;
    JLabel fname = new JLabel();
    ClickableJProgressBar jpr = new ClickableJProgressBar(new Interface() {
        @Override
        public void action(int v) {
            try {
                plr.setPlayedMS(v);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    });
    JPanel btnPanel = new JPanel();
    JPanel pn1 = new JPanel();
    JButton[] btns = new JButton[]{
            new JButton("Play"),
            new JButton("Pause"),
            new JButton("Resume"),
            new JButton("Stop"),
            new JButton("Start Looping")
    };
    JMenuBar j = new JMenuBar();
    JMenu[] i = new JMenu[]{
            new JMenu("File")
    };
    JMenuItem[][] n = new JMenuItem[][]{
            new JMenuItem[]{
                    new JMenuItem("Open")
            }
    };
    public Win(){
        setJMenuBar(j);
        for (int a = 0; a < i.length; a++) {
            for (int k = 0; k < n.length; k++) {
                for (int l = 0; l < n[k].length; l++) {
                    i[a].add(n[k][l]);
                }
            }
            j.add(i[a]);
        }
        n[0][0].addActionListener((e)->{
            msc = open();
            try {
                plr.stop();
                plr.init(msc.getPath());
                plr.loop(loop);
                jpr.setMaximum((int) plr.getTotalMS());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, Main.lang.translate("Failed to init player.",Main.language));
            }
        });
        for (JButton b : btns){
            btnPanel.add(b);
        }
        setLayout(new BorderLayout());
        btns[0].addActionListener((e)->{
            try {
                boolean bl = plr.play();
                if(!bl) throw new UnknownError();
            } catch (Exception | UnknownError ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, Main.lang.translate("Failed to play music.",Main.language));
            }
        });
        btns[1].addActionListener((e)->{
            try {
                boolean bl = plr.pause();
                if(!bl) throw new UnknownError();
            } catch (Exception | UnknownError ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, Main.lang.translate("Failed to pause music.",Main.language));
            }
        });
        btns[2].addActionListener((e)->{
            try {
                boolean bl = plr.resume();
                if(!bl) throw new UnknownError();
            } catch (Exception | UnknownError ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, Main.lang.translate("Failed to resume music.",Main.language));
            }
        });
        btns[3].addActionListener((e)->{
            try {
                boolean bl = plr.stop();
                if(!bl) throw new UnknownError();
            } catch (Exception | UnknownError ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, Main.lang.translate("Failed to stop music.",Main.language));
            }
        });
        btns[4].addActionListener((e)->{
            loop = !loop;
            try {
                plr.loop(loop);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            btns[4].setText(loop ? "Stop Looping" : "Start Looping");
        });
        SwingUtilities.invokeLater(this::t);
        pn1.setLayout(new BorderLayout());
        pn1.add(jpr,BorderLayout.SOUTH);
        pn1.add(fname,BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);
        add(pn1,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setVisible(true);
    }
    private void t(){
        new Thread(() -> {
            try {
                while (true){
                    SwingUtilities.invokeLater(() -> {
                        jpr.setValue((int)plr.getPlayedMS());
                        jpr.setClickable(plr.getSelected());
                        setTitle(plr.getFilename() + " - JAudioPlayer");
                        btns[0].setEnabled(plr.getSelected());
                        btns[1].setEnabled(plr.getSelected());
                        btns[2].setEnabled(plr.getSelected());
                        btns[3].setEnabled(plr.getSelected());
                        btns[4].setEnabled(plr.getSelected());
                        fname.setText(plr.fileName);
                    });
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
            }
        }).start();
    }
    private File open(){
        JFileChooser fc = new JFileChooser();
        if(fc.showDialog(null,"Open") == JFileChooser.CANCEL_OPTION)
            return null;
        return fc.getSelectedFile();
    }
    public void refresh(){
        btns[0].setText(Main.lang.translate("Play",Main.language));
    }
}
