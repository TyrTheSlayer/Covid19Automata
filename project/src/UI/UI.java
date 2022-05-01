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
        poplab.setFont(new Font("Verdana", Font.PLAIN, 14));

        frame.add(poplab);

        //% initial sick people
        JLabel inisick = new JLabel("% Initial Sick People");
        inisick.setBounds(85, 350, 100, 20);
        inisick.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(inisick);

        // JSlider insick = new JSlider();
        insick.setBounds(65, 365, 140, 40);
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
        vac.setBounds(100, 405, 100, 20);
        vac.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(vac);

        //JSlider vacc = new JSlider();
        vacc.setBounds(65, 420, 140, 40);
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
        mas.setBounds(113, 460, 100, 20);
        mas.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(mas);

        //  JSlider mask = new JSlider();
        mask.setBounds(65, 475, 140, 40);
        mask.setFont(new Font("Verdana", Font.PLAIN, 8));
        mask.setMinimum(0);
        mask.setMaximum(100);
        mask.setMinorTickSpacing(5);
        mask.setMajorTickSpacing(50);
        mask.setPaintTicks(true);
        mask.setPaintLabels(true);

        frame.add(mask);

        //% Social Distance
        JLabel SoDis = new JLabel("% Social Distanced");
        SoDis.setBounds(87, 515, 100, 20);
        SoDis.setFont(new Font("Verdana", Font.PLAIN, 10));

        frame.add(SoDis);

        //  JSlider mask = new JSlider();
        SocDis.setBounds(65, 530, 140, 40);
        SocDis.setFont(new Font("Verdana", Font.PLAIN, 8));
        SocDis.setMinimum(0);
        SocDis.setMaximum(100);
        SocDis.setMinorTickSpacing(5);
        SocDis.setMajorTickSpacing(50);
        SocDis.setPaintTicks(true);
        SocDis.setPaintLabels(true);

        frame.add(SocDis);

        //Quarentine required
        JCheckBox quar = new JCheckBox("Quarantine Required");
        quar.setBounds(67, 565, 170, 40);
        frame.add(quar);

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
        JLabel buildLabel = new JLabel("Add Buildings");
        buildLabel.setBounds( 450, 313, 200, 50);
        buildLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(buildLabel);

        /**
         * School Buttons
         */
        JLabel schoool = new JLabel("School");
        schoool.setBounds(385, 350, 100, 40);
        schoool.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(schoool);

        /* School Value Label*/
        final int[] numscho = {0};
        JLabel schoLab = new JLabel(String.valueOf(numscho[0]));
        schoLab.setBounds(402, 385, 29, 15);
        schoLab.setHorizontalTextPosition(SwingConstants.CENTER);
        schoLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(schoLab);

        /* School Subtract button */
        JButton schoolSub = new JButton("");
        schoolSub.setBounds(370, 380, 25, 25);
        JLabel schoSub = new JLabel("-");
        schoSub.setBounds(379, 379, 25, 25);
        schoSub.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(schoSub);
        frame.add(schoolSub);
        schoolSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numscho[0] > 0){
                    numscho[0] = numscho[0] - 1;
                }
                if(numscho[0] < 10){
                    schoLab.setBounds(402, 385, 29, 15);
                }
                schoLab.setText(String.valueOf(numscho[0]));
                frame.add(schoLab);
            }
        });

        /* School Add Button */
        JButton schoolAdd = new JButton("");
        schoolAdd.setBounds(420, 380, 25, 25);
        JLabel schoAdd = new JLabel("+");
        schoAdd.setBounds(426, 379, 25, 25);
        schoAdd.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(schoAdd);
        frame.add(schoolAdd);
        schoolAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numscho[0] < 20){
                    numscho[0] = numscho[0] + 1;
                }
                if(numscho[0] > 9){
                    schoLab.setBounds(399, 385, 29, 15);
                }
                schoLab.setText(String.valueOf(numscho[0]));
                frame.add(schoLab);
            }
        });







        /**
         * Store Buttons
         */
        JLabel storeLab = new JLabel("Store");
        storeLab.setBounds(390, 410, 100, 40);
        storeLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(storeLab);

        /* Store Value Label*/
        final int[] numStor = {0};
        JLabel StorLab = new JLabel(String.valueOf(numscho[0]));
        StorLab.setBounds(402, 445, 29, 15);
        StorLab.setHorizontalTextPosition(SwingConstants.CENTER);
        StorLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(StorLab);

        /* Store Subtract button */
        JButton storSubButt = new JButton("");
        storSubButt.setBounds(370, 440, 25, 25);
        JLabel storSubLab = new JLabel("-");
        storSubLab.setBounds(379, 439, 25, 25);
        storSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(storSubLab);
        frame.add(storSubButt);
        storSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numStor[0] > 0){
                    numStor[0] = numStor[0] - 1;
                }
                if(numStor[0] < 10){
                    StorLab.setBounds(402, 445, 29, 15);
                }
                StorLab.setText(String.valueOf(numStor[0]));
                frame.add(StorLab);
            }
        });

        /* Store Add Button */
        JButton storAddButt = new JButton("");
        storAddButt.setBounds(420, 440, 25, 25);
        JLabel storAddLab = new JLabel("+");
        storAddLab.setBounds(426, 439, 25, 25);
        storAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(storAddLab);
        frame.add(storAddButt);

        storAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numStor[0] < 20){
                    numStor[0] = numStor[0] + 1;
                }
                if(numStor[0] > 9){
                    StorLab.setBounds(399, 445, 29, 15);
                }
                StorLab.setText(String.valueOf(numStor[0]));
                frame.add(StorLab);
            }
        });

        /**
         * Hospital Buttons
         */
        JLabel HospitalLab = new JLabel("Hospital");
        HospitalLab.setBounds(380, 470, 100, 40);
        HospitalLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(HospitalLab);

        /* Hospital Value Label*/
        final int[] numHosp = {0};
        JLabel HospLab = new JLabel(String.valueOf(numscho[0]));
        HospLab.setBounds(402, 505, 29, 15);
        HospLab.setHorizontalTextPosition(SwingConstants.CENTER);
        HospLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(HospLab);

        /* Hospital Subtract button */
        JButton hospSubButt = new JButton("");
        hospSubButt.setBounds(370, 500, 25, 25);
        JLabel hospSubLab = new JLabel("-");
        hospSubLab.setBounds(379, 499, 25, 25);
        hospSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(hospSubLab);
        frame.add(hospSubButt);
        hospSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numHosp[0] > 0){
                    numHosp[0] = numHosp[0] - 1;
                }
                if(numHosp[0] < 10){
                    HospLab.setBounds(402, 505, 29, 15);
                }
                HospLab.setText(String.valueOf(numHosp[0]));
                frame.add(HospLab);
            }
        });

        /* Hospital Add Button */
        JButton hospAddButt = new JButton("");
        hospAddButt.setBounds(420, 500, 25, 25);
        JLabel hospAddLab = new JLabel("+");
        hospAddLab.setBounds(426, 499, 25, 25);
        hospAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(hospAddLab);
        frame.add(hospAddButt);

        hospAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numHosp[0] < 20){
                    numHosp[0] = numHosp[0] + 1;
                }
                if(numHosp[0] > 9){
                    HospLab.setBounds(399, 505, 29, 15);
                }
                HospLab.setText(String.valueOf(numHosp[0]));
                frame.add(HospLab);
            }
        });



        /**
         * Drug Store Buttons
         */
        JLabel DrugStoreLab = new JLabel("Drug Store");
        DrugStoreLab.setBounds(373, 530, 100, 40);
        DrugStoreLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(DrugStoreLab);

        /* Drug Store Value Label*/
        final int[] numDrug = {0};
        JLabel DrugLab = new JLabel(String.valueOf(numscho[0]));
        DrugLab.setBounds(402, 565, 29, 15);
        DrugLab.setHorizontalTextPosition(SwingConstants.CENTER);
        DrugLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(DrugLab);

        /* Drug Store Subtract button */
        JButton DrugSubButt = new JButton("");
        DrugSubButt.setBounds(370, 560, 25, 25);
        JLabel DrugSubLab = new JLabel("-");
        DrugSubLab.setBounds(379, 559, 25, 25);
        DrugSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(DrugSubLab);
        frame.add(DrugSubButt);
        DrugSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numDrug[0] > 0){
                    numDrug[0] = numDrug[0] - 1;
                }
                if(numDrug[0] < 10){
                    DrugLab.setBounds(402, 565, 29, 15);
                }
                DrugLab.setText(String.valueOf(numDrug[0]));
                frame.add(DrugLab);
            }
        });

        /* Drug Store Add Button */
        JButton DrugAddButt = new JButton("");
        DrugAddButt.setBounds(420, 560, 25, 25);
        JLabel DrugAddLab = new JLabel("+");
        DrugAddLab.setBounds(426, 559, 25, 25);
        DrugAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(DrugAddLab);
        frame.add(DrugAddButt);

        DrugAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numDrug[0] < 20){
                    numDrug[0] = numDrug[0] + 1;
                }
                if(numDrug[0] > 9){
                    DrugLab.setBounds(399, 565, 29, 15);
                }
                DrugLab.setText(String.valueOf(numDrug[0]));
                frame.add(DrugLab);
            }
        });


        /**
         *
         *
         *
         *
         *
         *
         *
         */

        /**
         * Concert Hall Buttons
         */
        JLabel concert = new JLabel("Concert Hall");
        concert.setBounds(545, 350, 100, 40);
        concert.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(concert);

        /* concert Hall Value Label*/
        final int[] numconc = {0};
        JLabel concLab = new JLabel(String.valueOf(numconc[0]));
        concLab.setBounds(582, 385, 29, 15);
        concLab.setHorizontalTextPosition(SwingConstants.CENTER);
        concLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(concLab);

        /* concert hall Subtract button */
        JButton concolSub = new JButton("");
        concolSub.setBounds(550, 380, 25, 25);
        JLabel concSub = new JLabel("-");
        concSub.setBounds(559, 379, 25, 25);
        concSub.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(concSub);
        frame.add(concolSub);
        concolSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numconc[0] > 0){
                    numconc[0] = numconc[0] - 1;
                }
                if(numconc[0] < 10){
                    concLab.setBounds(402, 385, 29, 15);
                }
                concLab.setText(String.valueOf(numconc[0]));
                frame.add(concLab);
            }
        });

        /* concert Add Button */
        JButton concolAdd = new JButton("");
        concolAdd.setBounds(600, 380, 25, 25);
        JLabel concAdd = new JLabel("+");
        concAdd.setBounds(606, 379, 25, 25);
        concAdd.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(concAdd);
        frame.add(concolAdd);
        concolAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numconc[0] < 20){
                    numconc[0] = numconc[0] + 1;
                }
                if(numconc[0] > 9){
                    concLab.setBounds(589, 385, 29, 15);
                }
                concLab.setText(String.valueOf(numconc[0]));
                frame.add(concLab);
            }
        });







        /**
         * casino Buttons
         **/
        JLabel casinoLab = new JLabel("Casino");
        casinoLab.setBounds(567, 410, 100, 40);
        casinoLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(casinoLab);

        /* casino Value Label*/
        final int[] numCasi = {0};
        JLabel CasiLab = new JLabel(String.valueOf(numscho[0]));
        CasiLab.setBounds(582, 445, 29, 15);
        CasiLab.setHorizontalTextPosition(SwingConstants.CENTER);
        CasiLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(CasiLab);

        /* casino Subtract button */
        JButton CasiSubButt = new JButton("");
        CasiSubButt.setBounds(550, 440, 25, 25);
        JLabel CasiSubLab = new JLabel("-");
        CasiSubLab.setBounds(559, 439, 25, 25);
        CasiSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(CasiSubLab);
        frame.add(CasiSubButt);
        CasiSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numCasi[0] > 0){
                    numCasi[0] = numCasi[0] - 1;
                }
                if(numCasi[0] < 10){
                    CasiLab.setBounds(582, 445, 29, 15);
                }
                CasiLab.setText(String.valueOf(numCasi[0]));
                frame.add(CasiLab);
            }
        });

        /* casino Add Button */
        JButton CasiAddButt = new JButton("");
        CasiAddButt.setBounds(600, 440, 25, 25);
        JLabel CasiAddLab = new JLabel("+");
        CasiAddLab.setBounds(606, 439, 25, 25);
        CasiAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(CasiAddLab);
        frame.add(CasiAddButt);

        CasiAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numCasi[0] < 20){
                    numCasi[0] = numCasi[0] + 1;
                }
                if(numCasi[0] > 9){
                    CasiLab.setBounds(579, 445, 29, 15);
                }
                CasiLab.setText(String.valueOf(numCasi[0]));
                frame.add(CasiLab);
            }
        });

        /**
         * RubberDuckFactory Buttons
         **/
        JLabel RubberDuckFactoryLab = new JLabel("Rubber Duck");
        RubberDuckFactoryLab.setBounds(547, 457, 150, 40);
        RubberDuckFactoryLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(RubberDuckFactoryLab);
        JLabel DuckFactoryLab = new JLabel("Factory");
        DuckFactoryLab.setBounds(564, 471, 150, 40);
        DuckFactoryLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(DuckFactoryLab);

        /* RubberDuckFactory Value Label*/
        final int[] numquack = {0};
        JLabel quackLab = new JLabel(String.valueOf(numscho[0]));
        quackLab.setBounds(582, 505, 29, 15);
        quackLab.setHorizontalTextPosition(SwingConstants.CENTER);
        quackLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(quackLab);

        /* RubberDuckFactory Subtract button */
        JButton quackSubButt = new JButton("");
        quackSubButt.setBounds(550, 500, 25, 25);
        JLabel quackSubLab = new JLabel("-");
        quackSubLab.setBounds(559, 499, 25, 25);
        quackSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(quackSubLab);
        frame.add(quackSubButt);
        quackSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numquack[0] > 0){
                    numquack[0] = numquack[0] - 1;
                }
                if(numquack[0] < 10){
                    quackLab.setBounds(582, 505, 29, 15);
                }
                quackLab.setText(String.valueOf(numquack[0]));
                frame.add(quackLab);
            }
        });

        /* RubberDuckFactory Add Button */
        JButton quackAddButt = new JButton("");
        quackAddButt.setBounds(600, 500, 25, 25);
        JLabel quackAddLab = new JLabel("+");
        quackAddLab.setBounds(606, 499, 25, 25);
        quackAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(quackAddLab);
        frame.add(quackAddButt);

        quackAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numquack[0] < 20){
                    numquack[0] = numquack[0] + 1;
                }
                if(numquack[0] > 9){
                    quackLab.setBounds(579, 505, 29, 15);
                }
                quackLab.setText(String.valueOf(numquack[0]));
                frame.add(quackLab);
            }
        });



        /**
         * Library Buttons
         */
        JLabel LibraryLab = new JLabel("Library");
        LibraryLab.setBounds(567, 530, 100, 40);
        LibraryLab.setFont(new Font("Verdana", Font.PLAIN, 13));
        frame.add(LibraryLab);

        /* Library Value Label*/
        final int[] numLib = {0};
        JLabel LibLab = new JLabel(String.valueOf(numscho[0]));
        LibLab.setBounds(582, 565, 29, 15);
        LibLab.setHorizontalTextPosition(SwingConstants.CENTER);
        LibLab.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(LibLab);

        /* Library Store Subtract button */
        JButton LibSubButt = new JButton("");
        LibSubButt.setBounds(550, 560, 25, 25);
        JLabel LibSubLab = new JLabel("-");
        LibSubLab.setBounds(559, 559, 25, 25);
        LibSubLab.setFont(new Font("Verdana", Font.PLAIN,17));
        frame.add(LibSubLab);
        frame.add(LibSubButt);
        LibSubButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numLib[0] > 0){
                    numLib[0] = numLib[0] - 1;
                }
                if(numLib[0] < 10){
                    LibLab.setBounds(582, 565, 29, 15);
                }
                LibLab.setText(String.valueOf(numLib[0]));
                frame.add(LibLab);
            }
        });

        /* Library Store Add Button */
        JButton LibAddButt = new JButton("");
        LibAddButt.setBounds(600, 560, 25, 25);
        JLabel LibAddLab = new JLabel("+");
        LibAddLab.setBounds(606, 559, 25, 25);
        LibAddLab.setFont(new Font("Verdana", Font.PLAIN,15));
        frame.add(LibAddLab);
        frame.add(LibAddButt);

        LibAddButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numLib[0] < 20){
                    numLib[0] = numLib[0] + 1;
                }
                if(numLib[0] > 9){
                    LibLab.setBounds(579, 565, 29, 15);
                }
                LibLab.setText(String.valueOf(numLib[0]));
                frame.add(LibLab);
            }
        });
        /**
         * Check boxes for virus settings
         */

        /**
         * Adding Buttons to add Schools, Stores, and Hospitals to the map
         */


        JLabel Inct = new JLabel("Infectivity");
        Inct.setBounds(790, 370, 110, 30);
        Inct.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(Inct);

        JRadioButton infelow = new JRadioButton("Low");
        infelow.setBounds(720, 400, 60, 30);
        // infelow.setFont(new Font("Verdana", Font.PLAIN, 5));
        frame.add(infelow);
        //  var numscho = 0;

        JRadioButton infemed = new JRadioButton("Med");
        infemed.setBounds(800, 400, 60, 30);
        frame.add(infemed);

        JRadioButton infehigh = new JRadioButton("High");
        infehigh.setBounds(880, 400, 60, 30);
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
        Sev.setBounds(795, 440, 110, 30);
        Sev.setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.add(Sev);

        JRadioButton sevlow = new JRadioButton("Low");
        sevlow.setBounds(720, 470, 60, 30);
        frame.add(sevlow);
        //  var numscho = 0;



        JRadioButton sevmed = new JRadioButton("Med");
        sevmed.setBounds(800, 470, 60, 30);
        frame.add(sevmed);

        JRadioButton sevhigh = new JRadioButton("High");
        sevhigh.setBounds(880, 470, 60, 30);
        frame.add(sevhigh);

        ButtonGroup sev = new ButtonGroup();
        sev.add(sevhigh);
        sev.add(sevmed);
        sev.add(sevlow);





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








