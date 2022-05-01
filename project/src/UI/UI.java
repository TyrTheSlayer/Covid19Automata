/**
 * @author Summer Bronson
 * @author Aedan Wells
 *
 * @description UI for users to put in parameters for the simulation
 */

package UI;

import Grid.BuildingType;
import Simulator.SimSettings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class UI extends JFrame{


    public static void main(String[] args) {

        SimSettings simSet = new SimSettings();
        JFrame frame = new JFrame();

        ArrayList<BuildingType> builArrList = new ArrayList<>();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * Create Default, Cufstomize Settings, and Start buttons
         */


        JButton defalt = new JButton("Default");
        defalt.setBounds(140, 60, 120, 50);
        defalt.setFont(new Font("Verdana", Font.PLAIN, 15));
        frame.add(defalt);

        JButton go = new JButton("Start");
        go.setBounds(430, 35, 120, 90);
        go.setFont(new Font("Verdana", Font.PLAIN, 19));
       //go.setForeground(Color.BLUE);
        frame.add(go);



        JButton cust = new JButton("Reset");
        cust.setBounds(720, 60, 120, 50);
        cust.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.add(cust);



        /**
         *  Duration Label, Layout, and Slider
         */

        //Label
        JLabel durr = new JLabel("Duration (Weeks)");

        durr.setBounds(660, 150, 200, 50);
        durr.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.add(durr);


        //slider
        //To Do: add listener to say current value
        JSlider dur = new JSlider();
       // dur.setBounds(443, 150, 200, 60);
        dur.setBounds(640, 200, 200, 60);
        dur.setMinimum(4);
        dur.setMaximum(52);
        dur.setMajorTickSpacing(12);
        dur.setMinorTickSpacing(4);
        dur.setPaintTicks(true);
        dur.setPaintLabels(true);

        frame.add(dur);


        /**
         *  Population Label, Layout, and Slider
         */

        //Label
        JLabel pop = new JLabel("Population");
        pop.setBounds(195, 150, 120, 50);
        pop.setFont(new Font("Verdana", Font.PLAIN, 18));


        frame.add(pop);


        //slider
        //To Do: add listener to say current value
        JSlider poppu = new JSlider();
        poppu.setBounds(140, 200, 200, 60);
        poppu.setMinimum(100);
        poppu.setMaximum(1000);
        poppu.setMajorTickSpacing(300);
        poppu.setMinorTickSpacing(50);
        poppu.setPaintTicks(true);
        poppu.setPaintLabels(true);

        frame.add(poppu);


        /**
         * Buttons to open more settings for Population, map, and virus
         */

        // Population button
        JButton popset = new JButton("Customize");
        popset.setBounds(420, 260, 140, 50);
        popset.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.add(popset);

        // Map button
       /* JButton map = new JButton("Modify Map");
        map.setBounds(293, 280, 100, 20);
        frame.add(map);

        //Virus Button
        JButton vir = new JButton("Contagous Level");
        vir.setBounds(445, 280, 200, 20);*/
       // frame.add(vir);
        JSlider insick = new JSlider();
        JSlider vacc = new JSlider();
        JSlider mask = new JSlider();
        JSlider SocDis = new JSlider();
        /**
         * Additional settings for Population
         */

        JLabel poplab = new JLabel("More Population Settings");
        poplab.setBounds( 60, 313, 200, 50);
        poplab.setFont(new Font("Verdana", Font.PLAIN, 12));

        frame.add(poplab);

        //% initial sick people
        JLabel inisick = new JLabel("% Initial Sick People");
        inisick.setBounds(85, 360, 100, 20);
        inisick.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(inisick);

        // JSlider insick = new JSlider();
        insick.setBounds(67, 393, 140, 40);
        insick.setFont(new Font("Verdana", Font.PLAIN, 8));
        insick.setMinimum(0);
        insick.setMaximum(100);
        insick.setMinorTickSpacing(5);
        insick.setMajorTickSpacing(50);
        insick.setPaintTicks(true);
        insick.setPaintLabels(true);

        frame.add(insick);

        //% Vaccinated
        JLabel vac = new JLabel("% Vaccinated");
        vac.setBounds(103, 425, 100, 20);
        vac.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(vac);

        //JSlider vacc = new JSlider();
        vacc.setBounds(67, 448, 140, 40);
        vacc.setFont(new Font("Verdana", Font.PLAIN, 8));
        vacc.setMinimum(0);
        vacc.setMaximum(100);
        vacc.setMinorTickSpacing(5);
        vacc.setMajorTickSpacing(50);
        vacc.setPaintTicks(true);
        vacc.setPaintLabels(true);

        frame.add(vacc);

        //% Masked
        JLabel mas = new JLabel("% Mask");
        mas.setBounds(114, 490, 100, 20);
        mas.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(mas);

        //  JSlider mask = new JSlider();
        mask.setBounds(67, 512, 140, 40);
        mask.setFont(new Font("Verdana", Font.PLAIN, 8));
        mask.setMinimum(0);
        mask.setMaximum(100);
        mask.setMinorTickSpacing(5);
        mask.setMajorTickSpacing(50);
        mask.setPaintTicks(true);
        mask.setPaintLabels(true);

        frame.add(mask);


        // % Social Distanced
   /* JLabel socD = new JLabel("% Social Distanced");
    socD.setBounds(88, 535, 100, 20);
    socD.setFont(new Font("Verdana", Font.PLAIN, 10));

    frame.add(socD);

   // JSlider SocDis = new JSlider();
    SocDis.setBounds(67, 557, 140, 40);
    SocDis.setFont(new Font("Verdana", Font.PLAIN, 8));
    SocDis.setMinimum(0);
    SocDis.setMaximum(100);
    SocDis.setMinorTickSpacing(5);
    SocDis.setMajorTickSpacing(50);
    SocDis.setPaintTicks(true);
    SocDis.setPaintLabels(true);

    frame.add(SocDis);*/


        /**
         * Adding Buttons to add Schools, Stores, and Hospitals to the map
         */

        JButton school = new JButton("Add School");
        school.setBounds(293, 360, 100, 40);
        frame.add(school);
        var numscho = 0;
        JLabel schoLab = new JLabel("whyyy \n test", numscho);
        schoLab.setBounds(293, 420, 100, 40);
        //  schoLab.setFont(new Font("Verdana", Font.PLAIN, 10));


        JButton store = new JButton("Add Store");
        store.setBounds(293, 440, 100, 40);
        frame.add(store);

        JButton hosp = new JButton("Add Hospital");
        hosp.setBounds(293, 520, 100, 40);
        frame.add(hosp);


        school.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                builArrList.add(BuildingType.SCHOOL);
                //  System.out.println(builArrList);

            }
        });
        store.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                builArrList.add(BuildingType.STORE);
                //  System.out.println(builArrList);
            }
        });

        hosp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                builArrList.add(BuildingType.HOSPITAL);
                // System.out.println(builArrList);
            }
        });
        /**
         * Check boxes for virus settings
         */

        /**
         * Adding Buttons to add Schools, Stores, and Hospitals to the map
         */


        JLabel Inct = new JLabel("Infectivity");
        Inct.setBounds(724, 350, 110, 30);
        Inct.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(Inct);

        JRadioButton infelow = new JRadioButton("Low");
        infelow.setBounds(650, 400, 60, 30);
        // infelow.setFont(new Font("Verdana", Font.PLAIN, 5));
        frame.add(infelow);
        //  var numscho = 0;

        JRadioButton infemed = new JRadioButton("Med");
        infemed.setBounds(730, 400, 60, 30);
        frame.add(infemed);

        JRadioButton infehigh = new JRadioButton("High");
        infehigh.setBounds(810, 400, 60, 30);
        frame.add(infehigh);


        ButtonGroup inf = new ButtonGroup();
        inf.add(infehigh);
        inf.add(infemed);
        inf.add(infelow);

        infehigh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSet.setInfectChance(0.8);
            }
        });

        infemed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSet.setInfectChance(0.4);
            }
        });

        infelow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSet.setInfectChance(0.2);
            }
        });

      /*  JLabel durr = new JLabel("Duration (Weeks)");
        durr.setBounds(493, 120, 110, 30);
        durr.setFont(new Font("Verdana", Font.PLAIN, 12));
        */
        JLabel Sev = new JLabel("Severity");
        Sev.setBounds(732, 480, 110, 30);
        Sev.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(Sev);

        JRadioButton sevlow = new JRadioButton("Low");
        sevlow.setBounds(650, 520, 60, 30);
        frame.add(sevlow);
        //  var numscho = 0;



        JRadioButton sevmed = new JRadioButton("Med");
        sevmed.setBounds(730, 520, 60, 30);
        frame.add(sevmed);

        JRadioButton sevhigh = new JRadioButton("High");
        sevhigh.setBounds(810, 520, 60, 30);
        frame.add(sevhigh);

        ButtonGroup sev = new ButtonGroup();
        sev.add(sevhigh);
        sev.add(sevmed);
        sev.add(sevlow);

        //Quarentine required
        JCheckBox quar = new JCheckBox("Quarantine Required");
        quar.setBounds(70, 540, 170, 40);
        frame.add(quar);

        //Treatment Exists
/*
    JCheckBox treat = new JCheckBox("Treatment Exists");
    treat.setBounds(495, 400, 170, 40);
    frame.add(treat);
*/

        /**
         * Text boxes to enter numbers for virus varients
         */
/*

    JTextField minCon = new JTextField("Contagious Time ");
    minCon.setBounds(495, 440, 170, 20);
    frame.add(minCon);

    JTextField sym = new JTextField("Symptom Time ");
    sym.setBounds(495, 480, 170, 20);
    frame.add(sym);

    JTextField rec = new JTextField("Recovery Time ");
    rec.setBounds(495, 520, 170, 20);
    frame.add(rec);

    JTextField death = new JTextField(" Death Time ");
    death.setBounds(495, 560, 170, 20);
    frame.add(death);
*/



        /**
         * Updating the SimSettings values when any of the sliders are changed
         */

        //population

        poppu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Population: " + poppu.getValue());
                simSet.setPopulation(poppu.getValue());
            }
        });

        // duration
        dur.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Duration: " +dur.getValue());
                simSet.setSimDuration(dur.getValue());
            }
        });

        // initial Infected
        insick.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double fina = insick.getValue() * .01;
                System.out.println("Initial Infected: " + fina);
                simSet.setInitialInfected(fina);
            }
        });

        // Vaxinated
        vacc.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double fina = vacc.getValue() * .01;
                System.out.println("Vaxed: " + fina);
                simSet.setVaxRate(fina);
            }
        });

        // Masked
        mask.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double fina = mask.getValue() * .01;
                System.out.println("Masked: " + fina);
                simSet.setMaskRate(fina);
            }
        });

        // Social Distanced
    /*SocDis.addChangeListener(new ChangeListener() {
     @Override
     public void stateChanged(ChangeEvent e) {
      double fina = SocDis.getValue() * .01;
      System.out.println("Social Distanced " + fina);
      simSet.setSocialDistRate(fina);
     }
    });*/

        // More Population settings

        /**
         * Expanding frame when a costumize button is pressed
         */
        popset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
                frame.setSize(1000, 650) ;
            }
        });

        cust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(1000, 650) ;
            }
        });

       /* map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(1000, 650) ;
            }
        });

        vir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(1000, 650) ;
            }
        });*/

        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<BuildingType> IfNoBuild = new ArrayList<>();

                if(builArrList.size() == 0){
                    IfNoBuild.add(BuildingType.STORE);
                    simSet.setBuTy(IfNoBuild);
                }
                else {
                    simSet.setBuTy(builArrList);
                }

                MainFrame mainFrame = new MainFrame("Simulation", simSet);
                mainFrame.startWindow();
                frame.dispose();


            }
        });
        //Set size
        frame.setSize(1000, 370) ;
        // uses no layout managers
        frame.setLayout(null);

        // makes the frame visible
        frame.setVisible(true);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}








