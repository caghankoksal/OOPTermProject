package si.um.opj.koksal.ui;


/*
 * Gui.java
 *
 */

import si.um.opj.koksal.logic.FoodItem;
import si.um.opj.koksal.logic.FoodItemType;
import si.um.opj.koksal.logic.Location;
import si.um.opj.koksal.logic.facility.BusinessFacility;
import si.um.opj.koksal.logic.facility.Store;
import si.um.opj.koksal.logic.facility.Warehouse;
import si.um.opj.koksal.logic.transport.Truck;
import si.um.opj.koksal.logic.transport.Van;
import si.um.opj.koksal.logic.transport.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public  class Gui implements ItemListener {

     public static Vector<BusinessFacility> facilitiesALL = new Vector<BusinessFacility>();
    public static Vector<Vehicle> vehicleALL = new Vector<Vehicle>();
    public static Vector<FoodItem> foodItemsALL = new Vector<FoodItem>();

     public static File file=new File("output.txt");



     public static FileOutputStream writer;

     {
         try {
             writer = new FileOutputStream(file);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             try {
                 writer.write(("FileNotFound Exception occured at +" + LocalDateTime.now() + ",CRITICAL" + '\n').getBytes());
             } catch (IOException e1) {
                 e1.printStackTrace();
             }
         }
     }

     //public static java.util.List<BusinessFacility> facilitiesALL=new java.util.ArrayList<BusinessFacility>();
     //public static java.util.List<Vehicle> vehicleALL=new java.util.ArrayList<Vehicle>();
     //public static java.util.List<FoodItem> foodItemsALL=new java.util.ArrayList<FoodItem>();


    public static JPanel cards; //a panel that uses CardLayout
    public final static String BUSINESS = "Business Facilities Management ";
    public final static String VEHICLE = "Vehicle Management ";
    final static String FOODITEMS = "Food Items Management ";
    final static String WAREHOUSEFUNCTIONALITY = "Warehouse Functionalities";
    final static String VEHICLEFUNCTIONALITY = "Vehicle Functionalities";


    private JButton businessCreate;
    private JButton businessEdit;
    private JButton businessDelete;

    public static JTextField t;



     //CARD 4 COMBOBOXES
     public static JComboBox foodItemList = new JComboBox(foodItemsALL);
     public static JComboBox wareList2  =new JComboBox(facilitiesALL);

     //CARD 4 COMBOBOXES


     //CARD5 COMBOBOXES
     public static JComboBox carList = new JComboBox(vehicleALL);
     public static JComboBox wareList  =new JComboBox(facilitiesALL);

     //CARD5 COMBOBOXES

     public static JComboBox editFacilities = new JComboBox(facilitiesALL);

     public static JComboBox comboCar = new JComboBox(vehicleALL);
     public static JComboBox comboFood = new JComboBox(foodItemsALL);
     //JComboBox comboCar;
     //CARD1
     public static JPanel card1 = new JPanel();
     public static JPanel card5  = new JPanel(new FlowLayout());
     public static JPanel card2 = new JPanel();


     public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        comboBoxPane.setLayout(new FlowLayout());
        String comboBoxItems[] = { BUSINESS, VEHICLE,FOODITEMS ,WAREHOUSEFUNCTIONALITY,VEHICLEFUNCTIONALITY};
        JComboBox cb = new JComboBox(comboBoxItems);

        cb.setEditable(false);

        cb.addItemListener(this);
        comboBoxPane.add(cb);




        card1.setLayout(new GridLayout(4, 1));


        JPanel addingButtons = new JPanel(new FlowLayout());

        businessCreate  = new JButton("CREATE");
        businessDelete = new JButton("DELETE");
        businessEdit = new JButton("EDIT");
        JButton serializeBut = new JButton("SERIALIZE");


        editFacilities.setEditable(false);


        addingButtons.add(editFacilities);
        addingButtons.add(businessDelete);
        addingButtons.add(businessCreate);
        addingButtons.add(businessEdit);
        addingButtons.add(serializeBut);

        card1.add(addingButtons);

        //Radio buttons
        final JRadioButton radioStoreButton = new JRadioButton("STORE", true);
        final JRadioButton radioWareHouseBut = new JRadioButton("WAREHOUSE");



        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(radioStoreButton);
        group.add(radioWareHouseBut);


        JPanel addingRadio = new JPanel(new FlowLayout());
        addingRadio.add(radioStoreButton);
        addingRadio.add(radioWareHouseBut);

        card1.add(addingRadio);


        JPanel addingText = new JPanel(new FlowLayout());

        JTextField t1 = new JTextField("Enter the city", 16);
        JLabel l1 = new JLabel("City :");

        JTextField t2 = new JTextField("Enter the country", 16);
        JLabel l2 = new JLabel("Country :");

        JTextField t3 = new JTextField("Enter the name of Facility", 16);
        JLabel l3 = new JLabel("Facility Name :");

        JTextField t15 = new JTextField("Enter the capacity", 16);
        JLabel l15= new JLabel("Capacity :");

        addingText.add(l1);
        addingText.add(t1);

        addingText.add(l2);
        addingText.add(t2);

        addingText.add(l3);
        addingText.add(t3);

        addingText.add(l15);
        addingText.add(t15);
        t15.setEnabled(false); //First start with Store button since Store does not have capacity its closed




        card1.add(addingText);








        radioStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioStoreButton.isSelected())
                System.out.println("STORE BUTTON SELECTED");
                t15.setEnabled(false);


            }
        });
        radioWareHouseBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        t15.setEnabled(true);
                        System.out.println("WarehouseButton SELECTED");

                    }

                }
        );


        businessCreate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("BUSINESS CREATED");

                        String city =t1.getText();
                        String country = t2.getText();
                        String facility_name = t3.getText();
                        Location createdLocation = new Location(city,country);



                        editFacilities.revalidate();
                        editFacilities.repaint();
                        addingButtons.revalidate();
                        addingButtons.repaint();





                        if(radioStoreButton.isSelected()) //STORE BUTTON
                        {

                            Store createdStore = new Store(facility_name,createdLocation);
                            facilitiesALL.add(createdStore);
                            System.out.println("New Store CREATED");
                            try {
                                writer.write(("New Store name: "+facility_name+" country: " +country+ "  city: " +city + "Created at: "+ LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL").getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }

                            editFacilities.updateUI();
                            wareList2.updateUI();
                            wareList.updateUI();


                        }
                        else if(radioWareHouseBut.isSelected())
                        {
                            int capacity = Integer.parseInt(t15.getText());
                            System.out.println("city is "+city+" country is : "+country + " facility name is :" + facility_name);
                            Warehouse createdWarehouse = new Warehouse(facility_name,createdLocation,capacity);
                            facilitiesALL.add(createdWarehouse);
                            System.out.println("New Warehouse CREATED");
                            try {
                                writer.write(("New Warehouse name: "+facility_name+" country: " +country+ "  city: " +city +" capacity: "+capacity
                                        +" Created at: "+ LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }

                            editFacilities.updateUI();
                            wareList2.updateUI();
                            wareList.updateUI();

                        }
                    }
                }
        );
        businessDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int selectedIndex = editFacilities.getSelectedIndex();
                        System.out.println(facilitiesALL.get(selectedIndex).getName() + " is deleted ");
                        try{
                            writer.write((facilitiesALL.get(selectedIndex).getName() + " is deleted ").getBytes());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            try {
                                writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }

                        facilitiesALL.remove(selectedIndex);


                        editFacilities.updateUI();
                        wareList2.updateUI();
                        wareList.updateUI();

                        card1.revalidate();
                        card1.repaint();


                    }
                }
        );

        businessEdit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String city =t1.getText();
                        String country = t2.getText();
                        String facility_name = t3.getText();
                        int capacity = Integer.parseInt(t15.getText());

                        Location createdLocation = new Location(city,country);

                        int selectedIndex = editFacilities.getSelectedIndex();
                        if(facilitiesALL.get(selectedIndex) instanceof  Store) {
                            System.out.println(facilitiesALL.get(editFacilities.getSelectedIndex()).getName() + " is edited to " + facility_name + " " +
                                    facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCity() + " is edit to : " + city + " " +
                                    facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCountry() + " is edit to : " + country + " ");
                                    try {
                                        writer.write(("Store "+facilitiesALL.get(editFacilities.getSelectedIndex()).getName() + " is edited to " + facility_name + " " +
                                                facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCity() + " is edit to : " + city + " " +
                                                facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCountry() + " is edit to : "
                                                + country + " at:" +LocalDateTime.now()+",REMARK"+'\n').getBytes());
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                        try {
                                            writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                            facilitiesALL.get(selectedIndex).setLocation(createdLocation);
                            facilitiesALL.get(selectedIndex).setName(facility_name);
                        }




                        else if(facilitiesALL.get(selectedIndex) instanceof Warehouse)
                        {
                            System.out.println(facilitiesALL.get(editFacilities.getSelectedIndex()).getName() + " is edited to " + facility_name + " " +
                                    facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCity() + " is edit to : " + city + " " +
                                    facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCountry() + " is edit to : " + country + " "+
                                    "capacity is set to: " +capacity);
                            try {
                                writer.write(("Warehouse "+facilitiesALL.get(editFacilities.getSelectedIndex()).getName() + " is edited to " + facility_name + " " +
                                        facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCity() + " is edit to : " + city + " " +
                                        facilitiesALL.get(editFacilities.getSelectedIndex()).getLocation().getCountry() + " is edit to : " + country + " "+
                                        "capacity is set to: " +capacity + " at "+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            }
                            catch (IOException ei)
                            {
                                ei.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }

                            facilitiesALL.get(selectedIndex).setLocation(createdLocation);
                            facilitiesALL.get(selectedIndex).setName(facility_name);
                            ( (Warehouse)(facilitiesALL.get(selectedIndex))).setCapacity(capacity);
                        }



                        /*



                        STORE U WAREHOUSE EDİTLEYEBİLİYOR MUYUZZ????
                        CAPACITY SEKMESIYLE ALAKALI BIR KAC SORUN VAR


                        TRUCK NUMBER OF TRAİLERS EDİTT YOK


                         */

                        editFacilities.updateUI();
                        wareList2.updateUI();
                        wareList.updateUI();

                        card1.revalidate();
                        card1.repaint();
                    }
                }
        );

        //Create the "cards".




        card2.setLayout(new GridLayout(4, 1));


        JPanel addingButtonsVehicle = new JPanel();

        JButton vehicleCreate  = new JButton("CREATE");
        JButton vehicleDelete = new JButton("DELETE");
        JButton vehicleEdit = new JButton("EDIT");


        comboCar.setEditable(false);


        addingButtonsVehicle.add(comboCar);
        addingButtonsVehicle.add(vehicleCreate);
        addingButtonsVehicle.add(vehicleDelete);
        addingButtonsVehicle.add(vehicleEdit);


        card2.add(addingButtonsVehicle);

        //Radio buttons
        final JRadioButton truckBut = new JRadioButton("TRUCK", true);
        final JRadioButton vanBut = new JRadioButton("VAN");


        //Group the radio buttons.
        ButtonGroup group1 = new ButtonGroup();
        group1.add(truckBut);
        group1.add(vanBut);


        JPanel addingRadioVehicle = new JPanel(new FlowLayout());
        addingRadioVehicle.add(truckBut);
        addingRadioVehicle.add(vanBut);

        card2.add(addingRadioVehicle);


        JPanel addingTextVehicle = new JPanel(new GridLayout(3,2));

        JTextField t4 = new JTextField("Enter the register number", 8);
        JLabel l4 = new JLabel("Register Number :");

        JTextField t5 = new JTextField("Enter the Average Speed", 8);
        JLabel l5 = new JLabel("Average Speed :");

        JTextField t6 = new JTextField("Enter the maxWeight", 8);
        JLabel l6 = new JLabel("maxWeight :");

        JTextField t7 = new JTextField("Enter the volume ", 8);
        JLabel l7 = new JLabel("Volume :");


        JTextField t8 = new JTextField("Enter the numberOfTrailers ", 8); //Sadece truck da var
        JLabel l8 = new JLabel("Number Of Trailers :");

        addingTextVehicle.add(l4); //registration number
        addingTextVehicle.add(t4);

        addingTextVehicle.add(l5); // average speed
        addingTextVehicle.add(t5);

        addingTextVehicle.add(l6); //max weight
        addingTextVehicle.add(t6);

        addingTextVehicle.add(l7); //max volume
        addingTextVehicle.add(t7);

        addingTextVehicle.add(l8);// number of trailers
        addingTextVehicle.add(t8);



        card2.add(addingTextVehicle);


        //Radio buttons  //sadece VAN
        final JRadioButton freshBut = new JRadioButton("FRESH", true);
        final JRadioButton frozenBut = new JRadioButton("FROZEN");


        //Group the radio buttons.
        ButtonGroup group3 = new ButtonGroup();
        group3.add(freshBut);
        group3.add(frozenBut);


        JPanel addingFrozenFreshVehicle = new JPanel(new FlowLayout());
        addingFrozenFreshVehicle.add(freshBut);
        addingFrozenFreshVehicle.add(frozenBut);

        frozenBut.setEnabled(false);
        freshBut.setEnabled(false);

        card2.add(addingFrozenFreshVehicle);



        truckBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(truckBut.isSelected())
                        {
                            System.out.println("TRUCK BUTTON IS SELECTED");
                            freshBut.setEnabled(false);
                            frozenBut.setEnabled(false);
                            t8.setEnabled(true);

                        }
                    }
                }
        );
        vanBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(vanBut.isSelected())
                        {
                            freshBut.setEnabled(true);
                            frozenBut.setEnabled(true);
                            t8.setEnabled(false);
                        }
                    }
                }
        );
        vehicleCreate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int registrationNumber = Integer.parseInt(t4.getText());
                        int averageSpeed = Integer.parseInt(t5.getText());
                        int maxWeight = Integer.parseInt(t6.getText());
                        int maxVolume = Integer.parseInt(t7.getText());

                        if(vanBut.isSelected() )
                        {
                            if(frozenBut.isSelected()) {
                                Van createdVan = new Van(registrationNumber, averageSpeed, maxWeight, maxVolume, FoodItemType.FROZEN);
                                System.out.println("VAN WITH FROZEN FOOD IS CREATED ");
                                try {
                                    writer.write(("Van with registerationNumber :" +registrationNumber + "averageSpeed: " + averageSpeed +"max weight :"+maxWeight+"MaxVolume : "
                                    +maxVolume+"WITH FROZEN FOOD is created at:"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                    try {
                                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                vehicleALL.add(createdVan);
                                comboCar.updateUI();
                                carList.updateUI();

                                card2.revalidate();
                                card2.repaint();
                            }
                            else if(freshBut.isSelected())
                            {
                                Van createdVan = new Van(registrationNumber,averageSpeed,maxWeight,maxVolume, FoodItemType.FRESH);
                                System.out.println("VAN WITH FRESH FOOD IS CREATED ");
                                try {
                                    writer.write(("Van with registerationNumber :" +registrationNumber + "averageSpeed: " + averageSpeed +"max weight :"+maxWeight+"MaxVolume : "
                                            +maxVolume+"WITH FRESH FOOD is created at:"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                    try {
                                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                vehicleALL.add(createdVan);

                                comboCar.updateUI();
                                carList.updateUI();

                                card2.revalidate();
                                card2.repaint();
                            }
                        }
                        else if(truckBut.isSelected())
                        {
                            int numberOfTrailers = Integer.parseInt((t8.getText()));
                            System.out.println("TRUCK IS CREATED ");
                            try {
                                writer.write(("Truck with registerationNumber :" +registrationNumber + "averageSpeed: " + averageSpeed +"max weight :"+maxWeight+"MaxVolume : "
                                        +maxVolume+"is created at:"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Truck createdTruck = new Truck(registrationNumber,averageSpeed,maxWeight,maxVolume,numberOfTrailers);
                            vehicleALL.add(createdTruck);

                            comboCar.updateUI();
                            carList.updateUI();

                            card2.revalidate();
                            card2.repaint();
                        }
                    }
                }
        );
        /*
        vehicleDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Vehicle with registration number : " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber() + " is deleted");
                        vehicleALL.remove(comboCar.getSelectedIndex());

                        comboCar.updateUI();
                        card2.revalidate();
                        card2.repaint();



                    }
                }
        );*/
        vehicleDelete.addActionListener(new Independent());


        vehicleEdit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int registrationNumber = Integer.parseInt(t4.getText());
                        int averageSpeed = Integer.parseInt(t5.getText());
                        int maxWeight = Integer.parseInt(t6.getText());
                        int maxVolume = Integer.parseInt(t7.getText());

                        if(vehicleALL.get(comboCar.getSelectedIndex()) instanceof Truck) {
                            System.out.println("Truck 's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                    " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume);
                            try{
                                writer.write(
                                        ("Truck 's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                        " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume+"edited at"
                                        + LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            }
                            catch (IOException io)
                            {
                                io.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }

                            vehicleALL.get(comboCar.getSelectedIndex()).setAverageSpeed(averageSpeed);
                            vehicleALL.get(comboCar.getSelectedIndex()).setMaxWeight(maxWeight);
                            vehicleALL.get(comboCar.getSelectedIndex()).setRegistrationNumber(registrationNumber);
                            vehicleALL.get(comboCar.getSelectedIndex()).setVolume(maxVolume);


                            comboCar.updateUI();
                            card2.revalidate();
                            card2.repaint();

                        }
                        else if(vehicleALL.get(comboCar.getSelectedIndex()) instanceof Van)
                        {
                            if(frozenBut.isSelected())
                            {
                                System.out.println("Van with frozen food 's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                        " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume);

                                try{
                                    writer.write(
                                            ("Van with frozen food 's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                                    " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume+"edited at"
                                                    + LocalDateTime.now()+",REMARK"+'\n').getBytes());
                                }
                                catch (IOException io)
                                {
                                    io.printStackTrace();
                                    try {
                                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                vehicleALL.get(comboCar.getSelectedIndex()).setAverageSpeed(averageSpeed);
                                vehicleALL.get(comboCar.getSelectedIndex()).setMaxWeight(maxWeight);
                                vehicleALL.get(comboCar.getSelectedIndex()).setRegistrationNumber(registrationNumber);
                                vehicleALL.get(comboCar.getSelectedIndex()).setVolume(maxVolume);
                                ((Van) vehicleALL.get(comboCar.getSelectedIndex())).setFoodItemType(FoodItemType.FROZEN);
                                comboCar.updateUI();
                                card2.revalidate();
                                card2.repaint();

                            }
                            else if(freshBut.isSelected())
                            {
                                System.out.println("Van with Fresh food 's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                        " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume);
                                try{
                                    writer.write(
                                            ("Van with FRESH Food's registration number edited to :  " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber()+
                                                    " average speed is edited to " +averageSpeed + " max weight is edited to: "+maxWeight + " max volume is edited to: " + maxVolume+"edited at"
                                                    + LocalDateTime.now()+",REMARK"+'\n').getBytes());
                                }
                                catch (IOException io)
                                {
                                    io.printStackTrace();
                                    try {
                                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                vehicleALL.get(comboCar.getSelectedIndex()).setAverageSpeed(averageSpeed);
                                vehicleALL.get(comboCar.getSelectedIndex()).setMaxWeight(maxWeight);
                                vehicleALL.get(comboCar.getSelectedIndex()).setRegistrationNumber(registrationNumber);
                                vehicleALL.get(comboCar.getSelectedIndex()).setVolume(maxVolume);
                                ((Van) vehicleALL.get(comboCar.getSelectedIndex())).setFoodItemType(FoodItemType.FRESH);
                                comboCar.updateUI();
                                card2.revalidate();
                                card2.repaint();

                            }

                        }

                    }
                }
        );

        ///////
        //CARD3
        JPanel card3 = new JPanel();
        card3.setLayout(new GridLayout(3, 1));


        JPanel addingButtonsFoodItems = new JPanel(new FlowLayout());


        comboFood.setEditable(false);

        JButton foodItemCreate  = new JButton("CREATE");
        JButton foodItemDelete = new JButton("DELETE");
        JButton foodItemEdit = new JButton("EDIT");

        addingButtonsFoodItems.add(comboFood);
        addingButtonsFoodItems.add(foodItemCreate);
        addingButtonsFoodItems.add(foodItemDelete);
        addingButtonsFoodItems.add(foodItemEdit);


        card3.add(addingButtonsFoodItems);

        //Radio buttons
        final JRadioButton foodItemTypeFresh = new JRadioButton("FRESH", true);
        final JRadioButton foodItemTypeFrozen = new JRadioButton("FROZEN");

        //Group the radio buttons.
        ButtonGroup group6 = new ButtonGroup();
        group6.add(foodItemTypeFresh);
        group6.add(foodItemTypeFrozen);


        JPanel addingRadioFoodItems = new JPanel(new FlowLayout());
        addingRadioFoodItems.add(foodItemTypeFresh);
        addingRadioFoodItems.add(foodItemTypeFrozen);

        card3.add(addingRadioFoodItems);


        JPanel addingTextFoodItems = new JPanel(new FlowLayout());

        //String label , int volume , int weight , LocalDate expirationDate, FoodItemType type
        JTextField t9 = new JTextField("Enter the label", 16);
        JLabel l9 = new JLabel("Label :");

        JTextField t10 = new JTextField("Enter the weight", 16);
        JLabel l10 = new JLabel("Weight :");

        JTextField t11 = new JTextField("Enter the expirationDate", 16);
        JLabel l11 = new JLabel("Expiration Date :");

        JTextField t16 = new JTextField("Enter the volume", 16);
        JLabel l16 = new JLabel("Volume :");

        addingTextFoodItems.add(l9);
        addingTextFoodItems.add(t9); //label

        addingTextFoodItems.add(l10);
        addingTextFoodItems.add(t10);//weight

        addingTextFoodItems.add(l11);
        addingTextFoodItems.add(t11);//expiration

        addingTextFoodItems.add(l16);//volume
        addingTextFoodItems.add(t16);

        card3.add(addingTextFoodItems);


        foodItemCreate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String takenLabel = t9.getText();
                        int takenWeight = Integer.parseInt(t10.getText());
                        String takenExpDate = t11.getText();
                        int takenVolume = Integer.parseInt(t16.getText());
                        System.out.println(takenExpDate);
                        String [] result  = takenExpDate.split("\\.",0);
                        LocalDate createdDate = LocalDate.of(Integer.parseInt(result[2]),Integer.parseInt(result[1]),Integer.parseInt(result[0]));


                        if(foodItemTypeFresh.isSelected())
                        {
                            FoodItem createdFoodItem = new FoodItem(takenLabel,takenVolume,takenWeight,createdDate,FoodItemType.FRESH  );
                            foodItemsALL.add(createdFoodItem);
                            System.out.println("FRESH FOOD ITEM CREATED");
                            try{
                                writer.write(("FRESH FOOD ITEM label : "+takenLabel+" Volume:"+takenVolume+"Weight:"+takenWeight
                                        + "with Expiration date :"+createdDate+" is created at:"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            }catch (IOException io)
                            {
                                io.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            comboFood.updateUI();
                            foodItemList.updateUI();
                            card3.revalidate();
                            card3.repaint();


                        }
                        else if(foodItemTypeFrozen.isSelected())
                        {
                            FoodItem createdFoodItem = new FoodItem(takenLabel,takenVolume,takenWeight,createdDate,FoodItemType.FROZEN  );
                            foodItemsALL.add(createdFoodItem);
                            System.out.println("FROZEN FOOD ITEM CREATED");
                            try{
                                writer.write(("FROOZENs FOOD ITEM label : "+takenLabel+" Volume:"+takenVolume+"Weight:"+takenWeight
                                        + "with Expiration date :"+createdDate+" is created at:"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            }catch (IOException io)
                            {
                                io.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            comboFood.updateUI();
                            foodItemList.updateUI();

                            card3.revalidate();
                            card3.repaint();

                        }

                    }
                }
        );
        foodItemDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(foodItemsALL.get(comboFood.getSelectedIndex()).getLabel() + " is deleted");
                        try{
                            writer.write((foodItemsALL.get(comboFood.getSelectedIndex()).getLabel() + "is deleted at :"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            try {
                                writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL").getBytes());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        foodItemsALL.remove(comboFood.getSelectedIndex());

                        comboFood.updateUI();
                        foodItemList.updateUI();

                        card3.revalidate();
                        card3.repaint();
                    }
                }
        );
        foodItemEdit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String takenLabel = t9.getText();
                        int takenWeight = Integer.parseInt(t10.getText());
                        String takenExpDate = t11.getText();
                        int takenVolume = Integer.parseInt(t16.getText());
                        System.out.println(takenExpDate);
                        String [] result  = takenExpDate.split("\\.",0);
                        LocalDate createdDate = LocalDate.of(Integer.parseInt(result[2]),Integer.parseInt(result[1]),Integer.parseInt(result[0]));


                        if(foodItemTypeFresh.isSelected())
                        {
                            System.out.println("FoodItem with label : " +foodItemsALL.get( comboFood.getSelectedIndex()).getLabel() + " is edited to : " +takenLabel
                                    + " foodItemType edited to FRESH " + " volume is edited to " + takenVolume + " weight is edited to " + takenWeight );
                            try{
                                writer.write(("FoodItem with label : " +foodItemsALL.get( comboFood.getSelectedIndex()).getLabel() + " is edited to : " +takenLabel
                                        + " foodItemType edited to FRESH " + " volume is edited to " + takenVolume + " weight is edited to "
                                        + takenWeight+ "edited at "+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            foodItemsALL.get(comboFood.getSelectedIndex()).setExpirationDate(createdDate);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setFoodItemType(FoodItemType.FRESH);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setVolume(takenVolume);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setLabel(takenLabel);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setWeight(takenWeight);

                            comboFood.updateUI();
                            foodItemList.updateUI();

                        }
                        else if(foodItemTypeFrozen.isSelected())
                        {
                            System.out.println("FoodItem with label : " +foodItemsALL.get( comboFood.getSelectedIndex()).getLabel() + " is edited to : " +takenLabel
                                    + " foodItemType edited to FROZEN " + " volume is edited to " + takenVolume + " weight is edited to " + takenWeight );
                            try{
                                writer.write(("FoodItem with label : " +foodItemsALL.get( comboFood.getSelectedIndex()).getLabel() + " is edited to : " +takenLabel
                                        + " foodItemType edited to FROZEN " + " volume is edited to " + takenVolume
                                        + " weight is edited to " + takenWeight+ "edited at "+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }

                            foodItemsALL.get(comboFood.getSelectedIndex()).setExpirationDate(createdDate);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setFoodItemType(FoodItemType.FROZEN);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setVolume(takenVolume);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setLabel(takenLabel);
                            foodItemsALL.get(comboFood.getSelectedIndex()).setWeight(takenWeight);
                            comboFood.updateUI();
                            foodItemList.updateUI();

                        }
                    }
                }
        );



        //CARD4 STARTS HERE

        JPanel card4 = new JPanel();
        card4.setLayout(new FlowLayout());


        JPanel carrier = new JPanel(new GridLayout(1,3));


        foodItemList.setPreferredSize(new Dimension(300,30));

        wareList2.setPreferredSize(new Dimension(300,30));

        foodItemList.repaint();
        wareList2.repaint();
        card4.repaint();

        carrier.add(foodItemList);
        carrier.add(wareList2);

        card4.add(carrier);
        JButton addItemToWareHouse = new JButton("ADDtoWAREHOUSE");

        card4.add(addItemToWareHouse);

        addItemToWareHouse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (facilitiesALL.get(wareList2.getSelectedIndex()) instanceof  Warehouse ) {
                            ((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex())).addItem(foodItemsALL.get(foodItemList.getSelectedIndex()));


                            String ware_house = "";
                            for(int k = 0 ; k < ((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex())).returnTheNumberOfFoodItems() ; k++)
                            {
                                ware_house = ware_house + ((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex())).getFoodItems()[k] + '\n' ;
                            }

                            System.out.println((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex()) +ware_house);
                            try{
                                writer.write(("Food added to warehouse at"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }


                        }
                        else
                            JOptionPane.showMessageDialog(card4,"THERE IS ERROR IN BUJSINESS FACILITY TYPE");
                    }
                }
        );


        //CARD 5 STARTSSS
        //CARD 5 STARTSSS

        JPanel vehicleCont = new JPanel(new FlowLayout());

        wareList.setEditable(false);
        carList.setEditable(true);

        vehicleCont.add(carList);
        vehicleCont.add(wareList);

        JPanel buttonCont = new JPanel(new FlowLayout());

        JButton loadItemButton = new JButton("LOADtoWarehouse");
        JButton unloadItemButton = new JButton("UNLOADfromStore");
        buttonCont.add(loadItemButton);
        buttonCont.add(unloadItemButton);


        card5.add(vehicleCont);
        card5.add(buttonCont);


//ACCEPT VEHICLE VEHICLE A LOAD YAPIYOR
        loadItemButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(facilitiesALL.get(wareList.getSelectedIndex()) instanceof  Warehouse) {
                            try {
                                ((Warehouse) facilitiesALL.get(wareList.getSelectedIndex())).acceptVehicle(vehicleALL.get(carList.getSelectedIndex()));
                                System.out.println("Warehouse accpeted car");
                                System.out.println("AFTER ACCEPT METHOD ON WAREHOUSE :");
                                System.out.println("WAREHOUSE :::::" );
                                String ware_house = "";
                                for(int k = 0 ; k < ((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex())).returnTheNumberOfFoodItems() ; k++)
                                {
                                    ware_house = ware_house + ((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex())).getFoodItems()[k] + '\n' ;
                                }

                                System.out.println((Warehouse) facilitiesALL.get(wareList2.getSelectedIndex()) +ware_house);
                                System.out.println(vehicleALL.get(carList.getSelectedIndex()).getTakenSpace());

                                writer.write(("Warehouse Accepted vehicle at"+LocalDateTime.now()+",REMARK"+'\n').getBytes());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                try {
                                    writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }



                        }
                        else {
                            JOptionPane.showMessageDialog(card5, "THERE IS ERROR IN BUSSINESS FACILITY TYPE CHOOSE WAREHOUSE");
                            try {
                                writer.write(("THERE IS ERROR IN BUSSINESS FACILITY TYPE CHOOSE WAREHOUSE error occured at: " + LocalDateTime.now()+'\n').getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
        );

        unloadItemButton.addActionListener(new InnerClass2());
        /*unloadItemButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                         if(facilitiesALL.get(wareList.getSelectedIndex()) instanceof  Store)
                        {
                            ((Store) facilitiesALL.get(wareList.getSelectedIndex())).acceptVehicle(vehicleALL.get(carList.getSelectedIndex()));
                            System.out.println("Store accpeted car");
                            System.out.println("FOODITEMS UNLOADED FROM VEHICLE");
                            System.out.println("AFTER UNLOADING VEHICLE " + vehicleALL.get(carList.getSelectedIndex()));

                        }
                        else{
                             JOptionPane.showMessageDialog(card5,"THERE IS ERROR IN BUJSINESS FACILITY TYPE CHOOSE STORE");
                         }


                    }
                }
        ); */
        serializeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] objArrayVehicleAll = vehicleALL.toArray();
                Object[] objArrayfacilitiesALL= facilitiesALL.toArray();
                Object[] objArrayfoodItemsALL= foodItemsALL.toArray();
                try {
                    FileOutputStream fos = new FileOutputStream("vehicle.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    for(int k = 0 ; k < objArrayVehicleAll.length;k++)
                        oos.writeObject(objArrayVehicleAll[k]);
                    oos.flush();
                    fos.flush();
                    oos.close();
                    fos.close();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    try {
                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }


                try {
                    FileOutputStream fos2 = new FileOutputStream("facilities.ser");
                    ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                    for(int i =0; i < objArrayfacilitiesALL.length ; i++) {
                        oos2.writeObject(objArrayfacilitiesALL[i]);
                    }
                    oos2.flush();
                    fos2.flush();
                    oos2.close();
                    fos2.close();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    try {
                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }

                try {
                    FileOutputStream fos3 = new FileOutputStream("foodItems.ser");
                    ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
                    for(int j = 0 ; j<objArrayfoodItemsALL.length;j++)
                        oos3.writeObject(objArrayfoodItemsALL[j]);
                    fos3.flush();
                    oos3.flush();
                    oos3.close();
                    fos3.close();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    try {
                        writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }

            }
        });




        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUSINESS);
        cards.add(card2, VEHICLE);
        cards.add(card3, FOODITEMS);
        cards.add(card4,WAREHOUSEFUNCTIONALITY);
        cards.add(card5,VEHICLEFUNCTIONALITY);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);








    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());

    }


     public class InnerClass2 implements ActionListener
     {

         @Override
         public void actionPerformed(ActionEvent e) {

             if(facilitiesALL.get(wareList.getSelectedIndex()) instanceof  Store)
             {
                 ((Store) facilitiesALL.get(wareList.getSelectedIndex())).acceptVehicle(vehicleALL.get(carList.getSelectedIndex()));
                 System.out.println("Store accpeted car");
                 System.out.println("FOODITEMS UNLOADED FROM VEHICLE");
                 System.out.println("AFTER UNLOADING VEHICLE " + vehicleALL.get(carList.getSelectedIndex()));
                 try {
                     writer.write(("Store unloaded at: "+LocalDateTime.now()+'\n').getBytes());
                 } catch (IOException e1) {
                     e1.printStackTrace();
                     try {
                         writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                     } catch (IOException e2) {
                         e2.printStackTrace();
                     }
                 }

             }
             else{
                 JOptionPane.showMessageDialog(card5,"THERE IS ERROR IN BUSSINESS FACILITY TYPE CHOOSE STORE");
                 try {
                     writer.write(("THERE IS ERROR IN BUSSINESS FACILITY TYPE CHOOSE STORE error occured at: "+LocalDateTime.now()+'\n').getBytes());
                 } catch (IOException e1) {
                     e1.printStackTrace();
                     try {
                         writer.write(("IOException occured at: "+LocalDateTime.now()+",CRITCAL"+'\n').getBytes());
                     } catch (IOException e2) {
                         e2.printStackTrace();
                     }
                 }
             }

         }
     }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() throws IOException {

        //Create and set up the window.
        JFrame frame = new JFrame("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Gui demo = new Gui();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);



        FileInputStream fis = null;
        //uncompressWithGZIP("vehicle.gz","vehicle.ser");
        //uncompressWithGZIP("facilities.gz","facilities.ser");
        //uncompressWithGZIP("foodItems.gz","foodItems.ser");
        try {
            fis = new FileInputStream("vehicle.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                vehicleALL.add((Vehicle) ois.readObject());
            }
        } catch (OptionalDataException e) {
            try {
                writer.write(("OptionalData Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        catch (EOFException eofExcp)
        {
            try {
                writer.write(("EOF Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        catch (FileNotFoundException e)
        {
            try {
                writer.write(("FileNotFound Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fis != null) {
                fis.close();
                frame.repaint();
                editFacilities.updateUI();
                wareList2.updateUI();
                wareList.updateUI();
                carList.updateUI();
                foodItemList.updateUI();
                foodItemList.revalidate();
                carList.revalidate();
                wareList.revalidate();
                carList.revalidate();
                wareList2.revalidate();
                comboCar.updateUI();
                comboCar.revalidate();
                comboFood.updateUI();
                comboFood.revalidate();
                fis.close();
            }
            }


            //FACILITIES SER
            try {

                FileInputStream fis2 = new FileInputStream("facilities.ser");
                ObjectInputStream ois2 = new ObjectInputStream(fis2);
                while (true)
                {
                    facilitiesALL.add((BusinessFacility) ois2.readObject());
                }


            } catch (OptionalDataException e) {
                try {
                    writer.write(("OptionalDataExceotioon occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            catch (EOFException eofExcp)
            {
                try {
                    writer.write(("EOF Exception  occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            catch (FileNotFoundException e)
            {
                try {
                    writer.write(("FileNotFound Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                frame.repaint();
                editFacilities.updateUI();
                wareList2.updateUI();
                wareList.updateUI();
                carList.updateUI();
                foodItemList.updateUI();
                foodItemList.revalidate();
                carList.revalidate();
                wareList.revalidate();
                carList.revalidate();
                wareList2.revalidate();
                comboCar.updateUI();
                comboCar.revalidate();
                comboFood.updateUI();
                comboFood.revalidate();

            }


            boolean check3 = true;


            Vector<Vector<FoodItem>> result3 = new Vector<>();

            //FOODITEMS READING
            try {
                FileInputStream fis3 = new FileInputStream("foodItems.ser");
                ObjectInputStream ois3 = new ObjectInputStream(fis3);
                while (true)
                {
                foodItemsALL.add((FoodItem) ois3.readObject());
                }

            } catch (OptionalDataException e) {
                try {
                    writer.write(("OptionalData Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            catch (EOFException eofExcp)
            {
                try {
                    writer.write(("EOF Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            catch (FileNotFoundException e)
            {
                try {
                    writer.write(("FileNotFound Exception occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                frame.repaint();
                editFacilities.updateUI();
                wareList2.updateUI();
                wareList.updateUI();
                carList.updateUI();
                foodItemList.updateUI();
                foodItemList.revalidate();
                carList.revalidate();
                wareList.revalidate();
                carList.revalidate();
                wareList2.revalidate();
                comboCar.updateUI();
                comboCar.revalidate();
                comboFood.updateUI();
                comboFood.revalidate();
            }
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                    super.windowClosed(e);
                    System.out.println("WindowListener method called: windowClosed.");

                    Object[] objArrayVehicleAll = vehicleALL.toArray();
                    Object[] objArrayfacilitiesALL = facilitiesALL.toArray();
                    Object[] objArrayfoodItemsALL = foodItemsALL.toArray();
                    try {
                        FileOutputStream fos = new FileOutputStream("vehicle.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);

                        for (int k = 0; k < objArrayVehicleAll.length; k++)
                            oos.writeObject(objArrayVehicleAll[k]);
                        oos.flush();
                        fos.flush();
                        oos.close();
                        fos.close();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                        try {
                            writer.write(("IOException occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }


                    try {
                        FileOutputStream fos2 = new FileOutputStream("facilities.ser");
                        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                        for (int i = 0; i < objArrayfacilitiesALL.length; i++) {
                            oos2.writeObject(objArrayfacilitiesALL[i]);
                        }
                        oos2.flush();
                        fos2.flush();
                        oos2.close();
                        fos2.close();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                        try {
                            writer.write(("IOException occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }

                    try {
                        FileOutputStream fos3 = new FileOutputStream("foodItems.ser");
                        ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
                        for (int j = 0; j < objArrayfoodItemsALL.length; j++)
                            oos3.writeObject(objArrayfoodItemsALL[j]);
                        oos3.flush();
                        fos3.flush();
                        oos3.close();
                        fos3.close();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                        try {
                            writer.write(("IOException occured at: "+LocalDateTime.now()+",WARNING"+'\n').getBytes());
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    compressWithGZIP("vehicle.ser","vehicle.gz");
                    compressWithGZIP("facilities.ser","facilities.gz");
                    compressWithGZIP("foodItems.ser","foodItems.gz");

                }
            }
            );




        }

     private static void compressWithGZIP(String filePath, String outputPath) {
         if (outputPath == null || outputPath.isEmpty()
                 || !outputPath.endsWith(".gz")) {
             outputPath += "compressed.gz";
         }

         BufferedReader br = null;
         BufferedOutputStream bo = null;
         try {
             br = new BufferedReader(new FileReader(filePath));
             bo = new BufferedOutputStream(new GZIPOutputStream(
                     new FileOutputStream(outputPath)));
             int c;
             while ((c = br.read()) != -1) {
                 bo.write(c);
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (br != null) {
                     br.close();
                 }
                 if (bo != null) {
                     bo.close();
                 }
             } catch (Exception e) {
                 e.printStackTrace();

             }
         }

     }

    private static void uncompressWithGZIP(String oripath, String outputPath) {
        BufferedInputStream bi = null;
        BufferedOutputStream bo = null;
        try {
            bi = new BufferedInputStream(new GZIPInputStream(
                    new FileInputStream(oripath)));
            bo = new BufferedOutputStream(new FileOutputStream(outputPath));
            int c;
            while ((c = bi.read()) != -1) {
                bo.write(c);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bi != null) {
                    bi.close();
                }
                if (bo != null) {
                    bo.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }






    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /* Use an appropriate Look and Feel */

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
        });


    }
}