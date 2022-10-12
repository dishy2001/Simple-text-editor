import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*; // theme we're using
import javax.swing.text.*;


class editor extends JFrame implements ActionListener{  //actionlistener tells the action
    // text object
    JTextArea t;
    // frame object
    JFrame f;

    editor(){  //constructor
        // initialize frame to editor
        f=new JFrame("editor");
        try {
            // set theme
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){}

        //initialise textarea
        t=new JTextArea();

        //initialise menubar
        JMenuBar mb=new JMenuBar();

        //create file menu for the menu bar
        JMenu m1=new JMenu("File");

        //create menu items for the file menu
        JMenuItem i1=new JMenuItem("New");
        JMenuItem i2=new JMenuItem("Open");
        JMenuItem i3=new JMenuItem("Save");
        JMenuItem i4=new JMenuItem("Print");

        // for making these items to act so adding actionlistener to each menu itme
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        // adding items to the desired menu
        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
        m1.add(i4);

        // create Edit menu for menu bar
        JMenu m2=new JMenu("Edit");

        //create menu items for the Edit menu
        JMenuItem i5=new JMenuItem("Cut");
        JMenuItem i6=new JMenuItem("Copy");
        JMenuItem i7=new JMenuItem("Paste");

        //adding actionlistener to these items
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);

        // adding items to the desired menu
        m2.add(i5);
        m2.add(i6);
        m2.add(i7);

        //creating the close button
        JMenuItem c=new JMenuItem("Close");
        c.addActionListener(this);

        //adding all menus to menubar
        mb.add(m1);
        mb.add(m2);
        mb.add(c);

        //set menubar to current frame of editor
        f.setJMenuBar(mb);
        // add frame to t(textarea)
        f.add(t);
        f.setSize(500,500);
        f.show();



    }
    // to add actions to the buttons
    public void actionPerformed(ActionEvent e){
        // extract the button pressed by the user into string s
        String s=e.getActionCommand();

        //create if else conditional statements for each case
        if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Save")){
            JFileChooser j=new JFileChooser("C:");
            // invoke the save dialog box
            int r=j.showSaveDialog(null);
            // set file label to the path of selected directory
            if(r==JFileChooser.APPROVE_OPTION){
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //create file writer
                    FileWriter write=new FileWriter(fi,false); // false is that it'll always override

                    //create bufferwriter to create file
                    BufferedWriter buffer=new BufferedWriter(write);
                    buffer.write(t.getText()); // writes to the buffer memory
                    buffer.flush(); //to flush the buffer because we don't want it now, it's imp to flush before closing
                    buffer.close();
                }
                catch(Exception ev){
                    JOptionPane.showMessageDialog(f,ev.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f,"User has cancelled the operation");
            }
        }

        else if(s.equals("Print")){
            try{
                t.print();
            }
            catch(Exception evt){
                JOptionPane.showMessageDialog(f,evt.getMessage());
            }
        }

        else if(s.equals("Open")){
            JFileChooser j=new JFileChooser("C:");

            int r=j.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION){
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    String s1="",s2="";
                    FileReader read=new FileReader((fi));
                    BufferedReader buffer=new BufferedReader(read);
                    s1=buffer.readLine();
                    while((s2= buffer.readLine())!=null){
                        s1=s1+"\n"+s2;
                    }
                    t.setText(s1);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f,evt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f,"User has cancelled the operation");
            }
        }

        else if(s.equals("New")){
            t.setText(""); // will set textarea to null string that is nothing will show on screen
        }
        else if(s.equals("Close")){
//          JOptionPane.showConfirmDialog(f,"the work is not saved"); // to add a popup whenever close is clicked
            f.setVisible(false); // terminates the window of the program
        }

    }

    // main class to call the editor
    public static void main(String args[]){
        editor e=new editor();
    }
}

