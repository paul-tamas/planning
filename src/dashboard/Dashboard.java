/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import Programari.AdaugaProgramare;
import Programari.Programari;
import Programari.ProgramariTableModel;
import clienti.AdaugaClient;
import clienti.ClientDashboard;
import clienti.Clienti;
import dataBaseModification.ModifytablesStructure;
import database.DbConnection;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
//import static database.DbConnection.programari;
import drepturi.Privilegii;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import login.DatabaseConnection;
import login.Login;
import login.LoginMode;
import utilizatori.AdaugaUtilizatori;
import utilizatori.UtilizatoriDashboard;
//import static sun.java2d.d3d.D3DRenderQueue.sync;
//import static sun.java2d.opengl.OGLRenderQueue.sync;

/**
 *
 * @author Paul
 */
public class Dashboard extends javax.swing.JFrame {

    private int rowSelectedForDelete = -1;

    /**
     * Creates new form Login
     */
    public Dashboard() throws SQLException {
        if (sessionActive == false) {
            initComponents();
        }
        this.setTitle("CentrulASCO-CRM tablou aplicatie");
        tableSort.add(ascendentBTN);
        tableSort.add(descendentBTN);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Determine the new location of the window
        this.setSize(dim.width, dim.height - 150);
        dbConnection();

        System.out.println("prg");
        ProgramariTableModel prgMod = new ProgramariTableModel(programari, client);

        programariTable.setModel(prgMod);
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Move the window
        this.setLocation(x, y);
        checkURL();
        createDirectory();
        
        
        // info pane font size 
       
        Font font = infoPane.getFont();
float size = font.getSize() ;
size=15;
fontChanger.setValue((int) size);
 fontChanger.setMaximum(80);
 fontChanger.setMinimum(8);
infoPane.setFont( font.deriveFont(size) );
        fontSize.setText(Float.toString(size));
//      //  fontChanger.addChangeListener(l);
    }
//
  
    

    static void createDirectory() {
        String PATH = "C:/";

        File directory = new File("C:/Export_centrulASCO");
        File downloadDir = new File("Downloads");
        if (!directory.exists()) {
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        if (!downloadDir.exists()) {
            downloadDir.mkdir();
            System.out.println("download dir");
        }
    }

    public static String getConnectedUser() {
        return sessionUsername;
    }
    static String sessionUsername;
    static boolean sessionActive = false;

    // init data vector;
    public ArrayList<Programari> programari = new ArrayList<Programari>();
    public ArrayList<Clienti> client = new ArrayList<Clienti>();
    ArrayList<LoginMode> date = new ArrayList<LoginMode>();

    ArrayList<Privilegii> privilegii = new ArrayList<Privilegii>();
    static String DBserver = null;
    static String database = null;
    static String Dbuser = null;
    static String password = null;
    static String url = "";
    static String certificates = "?verifyServerCertificate=false"
            + "&useSSL=true"
            + "&requireSSL=false";
    static File configFile;

    static void checkURL() {
        if (url.isEmpty()) {
            configFile = new File("config.ini");
        }
        Scanner myReader = null;
        try {
            myReader = new Scanner(configFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            new DatabaseConnection().setVisible(true);
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String parts[] = data.split("=");

            System.out.println(parts[0] + "|||||| " + parts[1]);

            System.out.println(data);
            if (parts[0].equals("STRING") || parts[0].equals("string")) {
                String[] sp = data.split(";");
                url = parts[1] + "=false";
                Dbuser = sp[1];
                password = sp[2];

                System.out.println("url+" + url);
            }
            if (parts[0].equals("BAZADEDATE")) {
                database = parts[1];
                System.out.println("database" + database);
            }
        }
        myReader.close();
        // url+=certificates;
        System.out.println(" url:" + url);
        System.out.println(" user:" + Dbuser);
        System.out.println(" parola:" + password);
        //  url = url+database;
    }

    private void sortareDupaData() throws SQLException, ParseException {
        String query = "select*from programari";
        if (ascendentBTN.isSelected()) {

            query = "Select * from programari order by data asc";
        }
        if (descendentBTN.isSelected()) {
            query = "Select * from programari order by data desc";
        }
        programari.clear();
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {
            ResultSet rs = null;
            Statement stmt = null;
            stmt = connection.createStatement();
            System.out.println("testtt");
            rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                //System.out.println(rs.getInt("id"), rs.getString("ora"),rs.getTimestamp("data1"), rs.getString("descriereProgramare"),rs.getInt("idClient"), rs.getInt("idUser") +" test");
                //  System.out.println("ora:" + rs.getTime("ora"));
                //  System.out.println("data:" + rs.getDate("data"));
                programari.add(new Programari(rs.getInt("id"), rs.getString("ora"), rs.getTimestamp("data"), rs.getString("descriereProgramare"), rs.getInt("idClient"), rs.getInt("idUser")));
                System.out.println("data din sortare" + programari.get(i++).getData());
            }
//                usr.close();
        }

    }

    public void dbConnection() throws SQLException {

        programari = prg();
        client = cl();
    }

    public void setSession(String user, boolean isSessionActive) {
        sessionUsername = user;
        sessionActive = isSessionActive;
        System.out.println(sessionUsername + "-------------------------------");
        messageForLogUser = "Bine ai venit in aplicatie: " + sessionUsername;
        setMessage();
    }
    String messageForLogUser;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jFrame1 = new javax.swing.JFrame();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        tableSort = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        programariTable = new javax.swing.JTable();
        refreshBTN = new java.awt.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoPane = new javax.swing.JTextPane();
        jSeparator2 = new javax.swing.JSeparator();
        logUser = new javax.swing.JLabel();
        AdaugaProgramare = new javax.swing.JButton();
        stergere = new javax.swing.JButton();
        clienti = new javax.swing.JButton();
        adaugaClienti = new javax.swing.JButton();
        inchidere = new javax.swing.JButton();
        utilizatori = new javax.swing.JButton();
        adaugaUtilizatori = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ascendentBTN = new javax.swing.JRadioButton();
        descendentBTN = new javax.swing.JRadioButton();
        textAreaFontDecrement = new javax.swing.JButton();
        textAreaFontIncrement = new javax.swing.JButton();
        fontChanger = new javax.swing.JSlider();
        fontSize = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        programTab = new javax.swing.JMenu();
        addBTN = new javax.swing.JMenuItem();
        deleteBTN = new javax.swing.JMenuItem();
        clientTab = new javax.swing.JMenu();
        usersTab = new javax.swing.JMenu();
        administrare = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenu();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("jCheckBoxMenuItem3");

        jMenuItem2.setText("jMenuItem2");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        programariTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        programariTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                programariTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(programariTable);

        refreshBTN.setLabel("Refresh");
        refreshBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBTNActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setViewportView(infoPane);

        logUser.setText("Bine ai venit in aplicatie: ");

        AdaugaProgramare.setText("Adauga programare");
        AdaugaProgramare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdaugaProgramareActionPerformed(evt);
            }
        });

        stergere.setText("Sterge programare");
        stergere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stergereActionPerformed(evt);
            }
        });

        clienti.setText("Clienti");
        clienti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientiActionPerformed(evt);
            }
        });

        adaugaClienti.setText("Adauga Clienti");
        adaugaClienti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaClientiActionPerformed(evt);
            }
        });

        inchidere.setText("Inchide");
        inchidere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inchidereActionPerformed(evt);
            }
        });

        utilizatori.setText("Utilizatori");
        utilizatori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilizatoriActionPerformed(evt);
            }
        });

        adaugaUtilizatori.setText("Adauga utilizatori");
        adaugaUtilizatori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaUtilizatoriActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_49x49.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_49x49.png"))); // NOI18N
        jLabel3.setText("jLabel2");
        jLabel3.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_49x49.png"))); // NOI18N
        jLabel4.setText("jLabel2");
        jLabel4.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_60x49.png"))); // NOI18N
        jLabel5.setText("jLabel2");
        jLabel5.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_49x49.png"))); // NOI18N
        jLabel7.setText("jLabel2");
        jLabel7.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inteprindere_de_insertie_49x49.jpg"))); // NOI18N
        jLabel12.setText("jLabel2");
        jLabel12.setPreferredSize(new java.awt.Dimension(49, 49));

        ascendentBTN.setText("Sortare ascendent dupa data ");

        descendentBTN.setText("Sortare descendent dupa data");

        textAreaFontDecrement.setText("-");
        textAreaFontDecrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAreaFontDecrementActionPerformed(evt);
            }
        });

        textAreaFontIncrement.setText("+");
        textAreaFontIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAreaFontIncrementActionPerformed(evt);
            }
        });

        fontChanger.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fontChangerStateChanged(evt);
            }
        });

        fontSize.setText("label1");

        programTab.setText("Programari");
        programTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                programTabMouseClicked(evt);
            }
        });

        addBTN.setText("Adauga programare");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });
        programTab.add(addBTN);

        deleteBTN.setText("Sterge programare");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });
        programTab.add(deleteBTN);

        jMenuBar1.add(programTab);

        clientTab.setText("Clienti");
        clientTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientTabMouseClicked(evt);
            }
        });
        jMenuBar1.add(clientTab);

        usersTab.setText("Utilizatori");
        usersTab.setToolTipText("");
        usersTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTabMouseClicked(evt);
            }
        });
        jMenuBar1.add(usersTab);

        administrare.setText("Administrare");

        jMenuItem3.setText("Actualizare Baza de date");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        administrare.add(jMenuItem3);

        jMenuBar1.add(administrare);

        logout.setText("Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stergere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AdaugaProgramare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adaugaClienti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clienti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adaugaUtilizatori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(utilizatori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(981, 981, 981))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(logUser)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textAreaFontDecrement)
                                                .addGap(40, 40, 40)
                                                .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(textAreaFontIncrement))
                                            .addComponent(fontChanger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(inchidere)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(descendentBTN)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(refreshBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(ascendentBTN)))
                                .addGap(18, 18, 18)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ascendentBTN)
                            .addComponent(refreshBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descendentBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textAreaFontIncrement)
                                .addComponent(textAreaFontDecrement))
                            .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fontChanger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(inchidere)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AdaugaProgramare)
                            .addComponent(clienti)
                            .addComponent(utilizatori))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stergere)
                            .addComponent(adaugaClienti)
                            .addComponent(adaugaUtilizatori))
                        .addGap(22, 22, 22)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logUser)
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
void setMessage() {
        logUser.setText(messageForLogUser);
    }
    private void programTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_programTabMouseClicked
        // TODO add your handling code here:
        ProgramariTableModel prgMod = new ProgramariTableModel(programari, client);
        programariTable.setModel(prgMod);
    }//GEN-LAST:event_programTabMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        sessionActive = false;
        new Login().setVisible(true);
        //   System.out.println(programari.get(2));

    }//GEN-LAST:event_logoutMouseClicked

    private void refreshBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBTNActionPerformed
        // clear content from table
        programariTable.removeAll();
        if (ascendentBTN.isSelected() || descendentBTN.isSelected()) {
            try {
                sortareDupaData();
                System.out.println("sortare");
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {

                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                // TODO add your handling code here:
                dbConnection();
                System.out.println("db");
            } catch (SQLException ex) {
                System.out.println("prg");
            }
        }

        for (Programari p : programari) {
            System.out.println(p.getData() + " 1");
        }
        ProgramariTableModel prgMod = new ProgramariTableModel(programari, client);

        programariTable.setModel(prgMod);       // TODO add your handling code here:
    }//GEN-LAST:event_refreshBTNActionPerformed

    private void programariTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_programariTableMouseClicked
        // TODO add your handling code here:
        String paneTxt = null;
        System.out.println("row count" + (programariTable.getSelectedRow()));
        int row = programariTable.getSelectedRow();
        rowSelectedForDelete = row;
        Programari p1 = programari.get(row);
        System.out.println("id Client din programare " + p1.idClient());
        int idC = p1.idClient();
        infoPane.setText(paneTxt);

//     paneTxt ="Nume Client: "+client.get(idC).getName()+"\nDescriere Client\n"+ client.get(idC).getDescription() +"\nNumarul de telefon: "
//             + "" + client.get(idC).getPhoneNumber()+ "\nAdresa de email: " + client.get(idC-1).getMailAddress()+"\nVarsta Clientului: "
//             +client.get(idC).getAge()+"\nDescriere programare:\n"+programari.get(row).descriereProgramare();
        checkURL();
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from date_clienti where id=" + p1.idClient());
                while (rs.next()) {
                    paneTxt = "Nume Client: " + rs.getString("nume") + "\nDescriere Client\n" + rs.getString("descriere") + "\nNumarul de telefon: "
                            + "" + rs.getString("telefon") + "\nAdresa de email: " + rs.getString("mail") + "\nVarsta Clientului: "
                            + rs.getInt("varsta") + "\nDescriere programare:\n" + programari.get(row).descriereProgramare();
                }
            }

        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
            String message = "Cannot find the driver in the classpath!" + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            String message = "Cannot connect the database!" + " " + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }

        infoPane.setText(paneTxt);

    }//GEN-LAST:event_programariTableMouseClicked

    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
        // TODO add your handling code here:
        new AdaugaProgramare().setVisible(true);
    }//GEN-LAST:event_addBTNActionPerformed

    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTNActionPerformed
        // TODO add your handling code here:
        int row = rowSelectedForDelete;
        programari.get(row);
        // de conectat la bd si folosit delete* where id = programari.get(row).getID();
        //sper sa mearga

        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        // ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                stmt.executeUpdate("DELETE  FROM programari WHERE id =" + programari.get(row).getID() + "");
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
            String message = "Cannot find the driver in the classpath!" + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            String message = "Cannot connect the database!" + " " + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
//JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed

    private void clientTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientTabMouseClicked
        // TODO add your handling code here:
        new ClientDashboard().setVisible(true);
    }//GEN-LAST:event_clientTabMouseClicked

    private void usersTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTabMouseClicked
        // TODO add your handling code here:
        new UtilizatoriDashboard().setVisible(true);
    }//GEN-LAST:event_usersTabMouseClicked

    private void AdaugaProgramareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdaugaProgramareActionPerformed
        // TODO add your handling code here:
        new AdaugaProgramare().setVisible(true);
    }//GEN-LAST:event_AdaugaProgramareActionPerformed

    private void stergereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergereActionPerformed
        // TODO add your handling code here:
        int row = rowSelectedForDelete;
        programari.get(row);
        // de conectat la bd si folosit delete* where id = programari.get(row).getID();
        //sper sa mearga

        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        // ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                stmt.executeUpdate("DELETE  FROM programari WHERE id =" + programari.get(row).getID() + "");
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
            String message = "Cannot find the driver in the classpath!" + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            String message = "Cannot connect the database!" + " " + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
//JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }

    }//GEN-LAST:event_stergereActionPerformed

    private void clientiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientiActionPerformed
        // TODO add your handling code here:
        new ClientDashboard().setVisible(true);
    }//GEN-LAST:event_clientiActionPerformed

    private void adaugaClientiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaClientiActionPerformed
        // TODO add your handling code here:
        new AdaugaClient().setVisible(true);
    }//GEN-LAST:event_adaugaClientiActionPerformed

    private void utilizatoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilizatoriActionPerformed
        // TODO add your handling code here:
        new UtilizatoriDashboard().setVisible(true);
    }//GEN-LAST:event_utilizatoriActionPerformed

    private void adaugaUtilizatoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaUtilizatoriActionPerformed
        // TODO add your handling code here:
        new AdaugaUtilizatori().setVisible(true);

    }//GEN-LAST:event_adaugaUtilizatoriActionPerformed

    private void inchidereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inchidereActionPerformed
        // TODO add your handling code here:
        this.dispose();
        sessionActive = false;
        new Login().setVisible(true);
    }//GEN-LAST:event_inchidereActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            // TODO add your handling code here:
            new ModifytablesStructure().modifyTable();
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void textAreaFontIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAreaFontIncrementActionPerformed
        // TODO add your handling code here:
        
        
        Font font = infoPane.getFont();
float size = font.getSize() + 1.0f;
infoPane.setFont( font.deriveFont(size) );
fontChanger.setValue((int) size);
    }//GEN-LAST:event_textAreaFontIncrementActionPerformed

    private void textAreaFontDecrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAreaFontDecrementActionPerformed
        // TODO add your handling code here:
             Font font = infoPane.getFont();
float size = font.getSize() - 1.0f;
infoPane.setFont( font.deriveFont(size) );
fontChanger.setValue((int) size);
    }//GEN-LAST:event_textAreaFontDecrementActionPerformed

    private void fontChangerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fontChangerStateChanged
 fontChanger = (JSlider) evt.getSource();
        float size  ;
        Font font = infoPane.getFont();
       // fontChanger.setValue((int) size);
        // Get the selection value of JSlider
        fontSize.setText(String.valueOf(fontChanger.getValue()));
         size = Float.valueOf(fontChanger.getValue());
         System.out.println("size: "+ size);
       infoPane.setFont( font.deriveFont(size) );
        System.out.println("slider");        // TODO add your handling code here:
    }//GEN-LAST:event_fontChangerStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Dashboard().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdaugaProgramare;
    private javax.swing.JButton adaugaClienti;
    private javax.swing.JButton adaugaUtilizatori;
    private javax.swing.JMenuItem addBTN;
    private javax.swing.JMenu administrare;
    private javax.swing.JRadioButton ascendentBTN;
    private javax.swing.JMenu clientTab;
    private javax.swing.JButton clienti;
    private javax.swing.JMenuItem deleteBTN;
    private javax.swing.JRadioButton descendentBTN;
    private javax.swing.JSlider fontChanger;
    private java.awt.Label fontSize;
    private javax.swing.JButton inchidere;
    private javax.swing.JTextPane infoPane;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logUser;
    private javax.swing.JMenu logout;
    private javax.swing.JMenu programTab;
    private javax.swing.JTable programariTable;
    private java.awt.Button refreshBTN;
    private javax.swing.JButton stergere;
    private javax.swing.ButtonGroup tableSort;
    private javax.swing.JButton textAreaFontDecrement;
    private javax.swing.JButton textAreaFontIncrement;
    private javax.swing.JMenu usersTab;
    private javax.swing.JButton utilizatori;
    // End of variables declaration//GEN-END:variables
}
