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
    defalt.setBounds(125, 20, 85, 40);
    frame.add(defalt);

    JButton go = new JButton("Start");
    go.setBounds(495, 20, 85, 40);
    frame.add(go);

    JButton cust = new JButton("Customize Settings");
    cust.setBounds(277, 20, 150, 40);
    frame.add(cust);



    /**
     *  Duration Label, Layout, and Slider
     */

    //Label
    JLabel durr = new JLabel("Duration (Weeks)");
    durr.setBounds(493, 120, 110, 30);
    durr.setFont(new Font("Verdana", Font.PLAIN, 12));

    frame.add(durr);


    //slider
    //To Do: add listener to say current value
    JSlider dur = new JSlider();
    dur.setBounds(443, 150, 200, 60);
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
    pop.setBounds(110, 120, 65, 30);
    pop.setFont(new Font("Verdana", Font.PLAIN, 12));

    frame.add(pop);


    //slider
    //To Do: add listener to say current value
    JSlider poppu = new JSlider();
    poppu.setBounds(40, 150, 200, 60);
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
    JButton popset = new JButton("More Population Settings");
    popset.setBounds(37, 280, 200, 20);
    frame.add(popset);

    // Map button
    JButton map = new JButton("Modify Map");
    map.setBounds(293, 280, 100, 20);
    frame.add(map);

    //Virus Button
    JButton vir = new JButton("Contagous Level");
    vir.setBounds(445, 280, 200, 20);
    frame.add(vir);
       JSlider insick = new JSlider();
       JSlider vacc = new JSlider();
       JSlider mask = new JSlider();
       JSlider SocDis = new JSlider();
    /**
     * Additional settings for Population
     */

    //% initial sick people
    JLabel inisick = new JLabel("% Initial Sick People");
    inisick.setBounds(85, 340, 100, 20);
    inisick.setFont(new Font("Verdana", Font.PLAIN, 10));

    frame.add(inisick);

   // JSlider insick = new JSlider();
    insick.setBounds(67, 373, 140, 40);
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
    vac.setBounds(103, 405, 100, 20);
    vac.setFont(new Font("Verdana", Font.PLAIN, 10));

   frame.add(vac);

    //JSlider vacc = new JSlider();
    vacc.setBounds(67, 428, 140, 40);
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
    mas.setBounds(114, 470, 100, 20);
    mas.setFont(new Font("Verdana", Font.PLAIN, 10));

    frame.add(mas);

  //  JSlider mask = new JSlider();
    mask.setBounds(67, 492, 140, 40);
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

        JButton infelow = new JButton("Low");
        infelow.setBounds(500, 360, 100, 40);
        frame.add(infelow);
      //  var numscho = 0;



        JButton infemed = new JButton("Medium");
        infemed.setBounds(500, 440, 100, 40);
        frame.add(infemed);

        JButton infehigh = new JButton("High");
        infehigh.setBounds(500, 520, 100, 40);
        frame.add(infehigh);


        JButton sevlow = new JButton("Low");
        infelow.setBounds(500, 360, 100, 40);
        frame.add(infelow);
        //  var numscho = 0;



        JButton sevmed = new JButton("Medium");
        sevmed.setBounds(500, 440, 100, 40);
        frame.add(sevmed);

        JButton sevhigh = new JButton("High");
        sevhigh.setBounds(500, 520, 100, 40);
        frame.add(sevhigh);

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
        frame.setSize(900, 650) ;
    }
});

cust.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setSize(900, 650) ;
    }
});

map.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setSize(900, 650) ;
    }
});

vir.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setSize(900, 650) ;
    }
});

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
       frame.setSize(900, 370) ;
       // uses no layout managers
       frame.setLayout(null);

       // makes the frame visible
       frame.setVisible(true);


   }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}








