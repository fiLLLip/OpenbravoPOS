//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.ReportUtils;
import com.openbravo.pos.util.StringParser;
import java.io.File;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.skin.SkinInfo;

/**
 *
 * @author adrianromero
 */
public class JPanelConfigGeneral extends javax.swing.JPanel implements PanelConfig {

    private DirtyManager dirty = new DirtyManager();

    private ParametersConfig printer1printerparams;

    private ParametersConfig printer2printerparams;

    private ParametersConfig printer3printerparams;
    private JFileChooser m_jDrawerExecFileChooser;

    /**
     * Creates new form JPanelConfigGeneral
     */
    public JPanelConfigGeneral() {

        initComponents();

        String[] printernames = ReportUtils.getPrintNames();

        jtxtMachineHostname.getDocument().addDocumentListener(dirty);
        jcboLAF.addActionListener(dirty);
        jcboMachineScreenmode.addActionListener(dirty);
        jcboTicketsBag.addActionListener(dirty);

        jcboMachineDisplay.addActionListener(dirty);
        jcboConnDisplay.addActionListener(dirty);
        jcboSerialDisplay.addActionListener(dirty);
        m_jtxtJPOSName.getDocument().addDocumentListener(dirty);

        jcboMachinePrinter.addActionListener(dirty);
        jcboConnPrinter.addActionListener(dirty);
        jcboSerialPrinter.addActionListener(dirty);
        m_jtxtJPOSPrinter.getDocument().addDocumentListener(dirty);
        m_jtxtJPOSDrawer.getDocument().addDocumentListener(dirty);

        printer1printerparams = new ParametersPrinter(printernames);
        printer1printerparams.addDirtyManager(dirty);
        m_jPrinterParams1.add(printer1printerparams.getComponent(), "printer");

        jcboMachinePrinter2.addActionListener(dirty);
        jcboConnPrinter2.addActionListener(dirty);
        jcboSerialPrinter2.addActionListener(dirty);
        m_jtxtJPOSPrinter2.getDocument().addDocumentListener(dirty);
        m_jtxtJPOSDrawer2.getDocument().addDocumentListener(dirty);

        printer2printerparams = new ParametersPrinter(printernames);
        printer2printerparams.addDirtyManager(dirty);
        m_jPrinterParams2.add(printer2printerparams.getComponent(), "printer");

        jcboMachinePrinter3.addActionListener(dirty);
        jcboConnPrinter3.addActionListener(dirty);
        jcboSerialPrinter3.addActionListener(dirty);
        m_jtxtJPOSPrinter3.getDocument().addDocumentListener(dirty);
        m_jtxtJPOSDrawer3.getDocument().addDocumentListener(dirty);

        printer3printerparams = new ParametersPrinter(printernames);
        printer3printerparams.addDirtyManager(dirty);
        m_jPrinterParams3.add(printer3printerparams.getComponent(), "printer");

        jcboMachineScale.addActionListener(dirty);
        jcboSerialScale.addActionListener(dirty);

        jcboMachineScanner.addActionListener(dirty);
        jcboSerialScanner.addActionListener(dirty);

        cboPrinters.addActionListener(dirty);

//        // Openbravo Skin
//        jcboLAF.addItem(new UIManager.LookAndFeelInfo("Openbravo", "com.openbravo.pos.skin.OpenbravoLookAndFeel"));
        // Installed skins
        LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lafs.length; i++) {
            jcboLAF.addItem(new LAFInfo(lafs[i].getName(), lafs[i].getClassName()));
        }

        // Substance skins
        // new SubstanceLookAndFeel(); // TODO: Remove in Substance 5.0. Workaround for Substance 4.3 to initialize static variables
        Map<String, SkinInfo> skins = SubstanceLookAndFeel.getAllSkins();
        for (SkinInfo skin : skins.values()) {
            jcboLAF.addItem(new LAFInfo(skin.getDisplayName(), skin.getClassName()));
        }

        jcboLAF.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLAF();
            }
        });

        jcboMachineScreenmode.addItem("window");
        jcboMachineScreenmode.addItem("fullscreen");

        jcboTicketsBag.addItem("simple");
        jcboTicketsBag.addItem("standard");
        jcboTicketsBag.addItem("restaurant");

        // Printer 1
        jcboMachinePrinter.addItem("screen");
        jcboMachinePrinter.addItem("printer");
        jcboMachinePrinter.addItem("epson");
        jcboMachinePrinter.addItem("tmu220");
        jcboMachinePrinter.addItem("star");
        jcboMachinePrinter.addItem("ithaca");
        jcboMachinePrinter.addItem("surepos");
        jcboMachinePrinter.addItem("plain");
        jcboMachinePrinter.addItem("javapos");
        jcboMachinePrinter.addItem("epos");
        jcboMachinePrinter.addItem("Not defined");

        jcboConnPrinter.addItem("serial");
        jcboConnPrinter.addItem("file");
        jcboConnPrinter.addItem("tcp");

        jcboSerialPrinter.addItem("COM1");
        jcboSerialPrinter.addItem("COM2");
        jcboSerialPrinter.addItem("COM3");
        jcboSerialPrinter.addItem("COM4");
        jcboSerialPrinter.addItem("LPT1");
        jcboSerialPrinter.addItem("/dev/ttyS0");
        jcboSerialPrinter.addItem("/dev/ttyS1");
        jcboSerialPrinter.addItem("/dev/ttyS2");
        jcboSerialPrinter.addItem("/dev/ttyS3");

        // Printer 2        
        jcboMachinePrinter2.addItem("screen");
        jcboMachinePrinter2.addItem("printer");
        jcboMachinePrinter2.addItem("epson");
        jcboMachinePrinter2.addItem("tmu220");
        jcboMachinePrinter2.addItem("star");
        jcboMachinePrinter2.addItem("ithaca");
        jcboMachinePrinter2.addItem("surepos");
        jcboMachinePrinter2.addItem("plain");
        jcboMachinePrinter2.addItem("javapos");
        jcboMachinePrinter2.addItem("epos");
        jcboMachinePrinter2.addItem("Not defined");

        jcboConnPrinter2.addItem("serial");
        jcboConnPrinter2.addItem("file");
        jcboConnPrinter2.addItem("tcp");

        jcboSerialPrinter2.addItem("COM1");
        jcboSerialPrinter2.addItem("COM2");
        jcboSerialPrinter2.addItem("COM3");
        jcboSerialPrinter2.addItem("COM4");
        jcboSerialPrinter2.addItem("LPT1");
        jcboSerialPrinter2.addItem("/dev/ttyS0");
        jcboSerialPrinter2.addItem("/dev/ttyS1");
        jcboSerialPrinter2.addItem("/dev/ttyS2");
        jcboSerialPrinter2.addItem("/dev/ttyS3");

        // Printer 3
        jcboMachinePrinter3.addItem("screen");
        jcboMachinePrinter3.addItem("printer");
        jcboMachinePrinter3.addItem("epson");
        jcboMachinePrinter3.addItem("tmu220");
        jcboMachinePrinter3.addItem("star");
        jcboMachinePrinter3.addItem("ithaca");
        jcboMachinePrinter3.addItem("surepos");
        jcboMachinePrinter3.addItem("plain");
        jcboMachinePrinter3.addItem("javapos");
        jcboMachinePrinter3.addItem("epos");
        jcboMachinePrinter3.addItem("Not defined");

        jcboConnPrinter3.addItem("serial");
        jcboConnPrinter3.addItem("file");
        jcboConnPrinter3.addItem("tcp");

        jcboSerialPrinter3.addItem("COM1");
        jcboSerialPrinter3.addItem("COM2");
        jcboSerialPrinter3.addItem("COM3");
        jcboSerialPrinter3.addItem("COM4");
        jcboSerialPrinter3.addItem("LPT1");
        jcboSerialPrinter3.addItem("/dev/ttyS0");
        jcboSerialPrinter3.addItem("/dev/ttyS1");
        jcboSerialPrinter3.addItem("/dev/ttyS2");
        jcboSerialPrinter3.addItem("/dev/ttyS3");

        // Display
        jcboMachineDisplay.addItem("screen");
        jcboMachineDisplay.addItem("window");
        jcboMachineDisplay.addItem("javapos");
        jcboMachineDisplay.addItem("epson");
        jcboMachineDisplay.addItem("ld200");
        jcboMachineDisplay.addItem("surepos");
        jcboMachineDisplay.addItem("Not defined");

        jcboConnDisplay.addItem("serial");
        jcboConnDisplay.addItem("file");

        jcboSerialDisplay.addItem("COM1");
        jcboSerialDisplay.addItem("COM2");
        jcboSerialDisplay.addItem("COM3");
        jcboSerialDisplay.addItem("COM4");
        jcboSerialDisplay.addItem("LPT1");
        jcboSerialDisplay.addItem("/dev/ttyS0");
        jcboSerialDisplay.addItem("/dev/ttyS1");
        jcboSerialDisplay.addItem("/dev/ttyS2");
        jcboSerialDisplay.addItem("/dev/ttyS3");

        // Scale
        jcboMachineScale.addItem("screen");
        jcboMachineScale.addItem("dialog1");
        jcboMachineScale.addItem("samsungesp");
        jcboMachineScale.addItem("Not defined");

        jcboSerialScale.addItem("COM1");
        jcboSerialScale.addItem("COM2");
        jcboSerialScale.addItem("COM3");
        jcboSerialScale.addItem("COM4");
        jcboSerialScale.addItem("/dev/ttyS0");
        jcboSerialScale.addItem("/dev/ttyS1");
        jcboSerialScale.addItem("/dev/ttyS2");
        jcboSerialScale.addItem("/dev/ttyS3");

        // Scanner
        jcboMachineScanner.addItem("scanpal2");
        jcboMachineScanner.addItem("Not defined");

        jcboSerialScanner.addItem("COM1");
        jcboSerialScanner.addItem("COM2");
        jcboSerialScanner.addItem("COM3");
        jcboSerialScanner.addItem("COM4");
        jcboSerialScanner.addItem("/dev/ttyS0");
        jcboSerialScanner.addItem("/dev/ttyS1");
        jcboSerialScanner.addItem("/dev/ttyS2");
        jcboSerialScanner.addItem("/dev/ttyS3");

        // Printers
        cboPrinters.addItem("(Default)");
        cboPrinters.addItem("(Show dialog)");
        for (String name : printernames) {
            cboPrinters.addItem(name);
        }

        jcboMachineDrawer.addItem("printer");
        jcboMachineDrawer.addItem("executable");
        jcboMachineDrawer.addItem("Not defined");
    }

    public boolean hasChanged() {
        return dirty.isDirty();
    }

    public Component getConfigComponent() {
        return this;
    }

    public void loadProperties(AppConfig config) {

        jtxtMachineHostname.setText(config.getProperty("machine.hostname"));

        String lafclass = config.getProperty("swing.defaultlaf");
        jcboLAF.setSelectedItem(null);
        for (int i = 0; i < jcboLAF.getItemCount(); i++) {
            LAFInfo lafinfo = (LAFInfo) jcboLAF.getItemAt(i);
            if (lafinfo.getClassName().equals(lafclass)) {
                jcboLAF.setSelectedIndex(i);
                break;
            }
        }
        // jcboLAF.setSelectedItem(new LookAndFeelInfo());

        jcboMachineScreenmode.setSelectedItem(config.getProperty("machine.screenmode"));
        jcboTicketsBag.setSelectedItem(config.getProperty("machine.ticketsbag"));

        StringParser p = new StringParser(config.getProperty("machine.printer"));
        String sparam = unifySerialInterface(p.nextToken(':'));
        if ("serial".equals(sparam) || "file".equals(sparam) || "tcp".equals(sparam)) {
            jcboMachinePrinter.setSelectedItem("epson");
            jcboConnPrinter.setSelectedItem(sparam);
            jcboSerialPrinter.setSelectedItem(p.nextToken(','));
        } else if ("javapos".equals(sparam)) {
            jcboMachinePrinter.setSelectedItem(sparam);
            m_jtxtJPOSPrinter.setText(p.nextToken(','));
            m_jtxtJPOSDrawer.setText(p.nextToken(','));
        } else if ("printer".equals(sparam)) {
            jcboMachinePrinter.setSelectedItem(sparam);
            printer1printerparams.setParameters(p);
        } else if ("epos".equals(sparam)) {
            jcboMachinePrinter.setSelectedItem(sparam);
            m_jtxtEPOSPrinterIP.setText(p.nextToken(','));
            m_jtxtEPOSPrinterID.setText(p.nextToken(','));
        } else {
            jcboMachinePrinter.setSelectedItem(sparam);
            jcboConnPrinter.setSelectedItem(unifySerialInterface(p.nextToken(',')));
            jcboSerialPrinter.setSelectedItem(p.nextToken(','));
        }

        p = new StringParser(config.getProperty("machine.printer.2"));
        sparam = unifySerialInterface(p.nextToken(':'));
        if ("serial".equals(sparam) || "file".equals(sparam) || "tcp".equals(sparam)) {
            jcboMachinePrinter2.setSelectedItem("epson");
            jcboConnPrinter2.setSelectedItem(sparam);
            jcboSerialPrinter2.setSelectedItem(p.nextToken(','));
        } else if ("javapos".equals(sparam)) {
            jcboMachinePrinter2.setSelectedItem(sparam);
            m_jtxtJPOSPrinter2.setText(p.nextToken(','));
            m_jtxtJPOSDrawer2.setText(p.nextToken(','));
        } else if ("printer".equals(sparam)) {
            jcboMachinePrinter2.setSelectedItem(sparam);
            printer2printerparams.setParameters(p);
        } else if ("epos".equals(sparam)) {
            jcboMachinePrinter2.setSelectedItem(sparam);
            m_jtxtEPOSPrinterIP2.setText(p.nextToken(','));
            m_jtxtEPOSPrinterID2.setText(p.nextToken(','));
        } else {
            jcboMachinePrinter2.setSelectedItem(sparam);
            jcboConnPrinter2.setSelectedItem(unifySerialInterface(p.nextToken(',')));
            jcboSerialPrinter2.setSelectedItem(p.nextToken(','));
        }

        p = new StringParser(config.getProperty("machine.printer.3"));
        sparam = unifySerialInterface(p.nextToken(':'));
        if ("serial".equals(sparam) || "file".equals(sparam) || "tcp".equals(sparam)) {
            jcboMachinePrinter3.setSelectedItem("epson");
            jcboConnPrinter3.setSelectedItem(sparam);
            jcboSerialPrinter3.setSelectedItem(p.nextToken(','));
        } else if ("javapos".equals(sparam)) {
            jcboMachinePrinter3.setSelectedItem(sparam);
            m_jtxtJPOSPrinter3.setText(p.nextToken(','));
            m_jtxtJPOSDrawer3.setText(p.nextToken(','));
        } else if ("printer".equals(sparam)) {
            jcboMachinePrinter3.setSelectedItem(sparam);
            printer3printerparams.setParameters(p);
        } else if ("epos".equals(sparam)) {
            jcboMachinePrinter3.setSelectedItem(sparam);
            m_jtxtEPOSPrinterIP3.setText(p.nextToken(','));
            m_jtxtEPOSPrinterID3.setText(p.nextToken(','));
        } else {
            jcboMachinePrinter3.setSelectedItem(sparam);
            jcboConnPrinter3.setSelectedItem(unifySerialInterface(p.nextToken(',')));
            jcboSerialPrinter3.setSelectedItem(p.nextToken(','));
        }

        p = new StringParser(config.getProperty("machine.display"));
        sparam = unifySerialInterface(p.nextToken(':'));
        if ("serial".equals(sparam) || "file".equals(sparam) || "tcp".equals(sparam)) {
            jcboMachineDisplay.setSelectedItem("epson");
            jcboConnDisplay.setSelectedItem(sparam);
            jcboSerialDisplay.setSelectedItem(p.nextToken(','));
        } else if ("javapos".equals(sparam)) {
            jcboMachineDisplay.setSelectedItem(sparam);
            m_jtxtJPOSName.setText(p.nextToken(','));
        } else {
            jcboMachineDisplay.setSelectedItem(sparam);
            jcboConnDisplay.setSelectedItem(unifySerialInterface(p.nextToken(',')));
            jcboSerialDisplay.setSelectedItem(p.nextToken(','));
        }

        p = new StringParser(config.getProperty("machine.scale"));
        sparam = p.nextToken(':');
        jcboMachineScale.setSelectedItem(sparam);
        if ("dialog1".equals(sparam) || "samsungesp".equals(sparam)) {
            jcboSerialScale.setSelectedItem(p.nextToken(','));
        }

        p = new StringParser(config.getProperty("machine.scanner"));
        sparam = p.nextToken(':');
        jcboMachineScanner.setSelectedItem(sparam);
        if ("scanpal2".equals(sparam)) {
            jcboSerialScanner.setSelectedItem(p.nextToken(','));
        }

        cboPrinters.setSelectedItem(config.getProperty("machine.printername"));

        
        p = new StringParser(config.getProperty("machine.drawer"));
        sparam = p.nextToken(':');
        jcboMachineDrawer.setSelectedItem(sparam);
        if ("executable".equals(sparam)) {
            m_jtxtDrawerExecFile.setText(p.nextToken(','));
        }

        dirty.setDirty(false);
    }

    public void saveProperties(AppConfig config) {

        config.setProperty("machine.hostname", jtxtMachineHostname.getText());

        LAFInfo laf = (LAFInfo) jcboLAF.getSelectedItem();
        config.setProperty("swing.defaultlaf", laf == null
                ? System.getProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel")
                : laf.getClassName());

        config.setProperty("machine.screenmode", comboValue(jcboMachineScreenmode.getSelectedItem()));
        config.setProperty("machine.ticketsbag", comboValue(jcboTicketsBag.getSelectedItem()));

        String sMachinePrinter = comboValue(jcboMachinePrinter.getSelectedItem());
        if ("epson".equals(sMachinePrinter) || "tmu220".equals(sMachinePrinter) || "star".equals(sMachinePrinter) || "ithaca".equals(sMachinePrinter) || "surepos".equals(sMachinePrinter)) {
            config.setProperty("machine.printer", sMachinePrinter + ":" + comboValue(jcboConnPrinter.getSelectedItem()) + "," + comboValue(jcboSerialPrinter.getSelectedItem()));
        } else if ("javapos".equals(sMachinePrinter)) {
            config.setProperty("machine.printer", sMachinePrinter + ":" + m_jtxtJPOSPrinter.getText() + "," + m_jtxtJPOSDrawer.getText());
        } else if ("printer".equals(sMachinePrinter)) {
            config.setProperty("machine.printer", sMachinePrinter + ":" + printer1printerparams.getParameters());
        } else if ("epos".equals(sMachinePrinter)) {
            config.setProperty("machine.printer", sMachinePrinter + ":" + m_jtxtEPOSPrinterIP.getText().trim() + "," + m_jtxtEPOSPrinterID.getText().trim());
        } else {
            config.setProperty("machine.printer", sMachinePrinter);
        }

        String sMachinePrinter2 = comboValue(jcboMachinePrinter2.getSelectedItem());
        if ("epson".equals(sMachinePrinter2) || "tmu220".equals(sMachinePrinter2) || "star".equals(sMachinePrinter2) || "ithaca".equals(sMachinePrinter2) || "surepos".equals(sMachinePrinter2)) {
            config.setProperty("machine.printer.2", sMachinePrinter2 + ":" + comboValue(jcboConnPrinter2.getSelectedItem()) + "," + comboValue(jcboSerialPrinter2.getSelectedItem()));
        } else if ("javapos".equals(sMachinePrinter2)) {
            config.setProperty("machine.printer.2", sMachinePrinter2 + ":" + m_jtxtJPOSPrinter2.getText() + "," + m_jtxtJPOSDrawer2.getText());
        } else if ("printer".equals(sMachinePrinter2)) {
            config.setProperty("machine.printer.2", sMachinePrinter2 + ":" + printer2printerparams.getParameters());
        } else if ("epos".equals(sMachinePrinter2)) {
            config.setProperty("machine.printer.2", sMachinePrinter2 + ":" + m_jtxtEPOSPrinterIP2.getText().trim() + "," + m_jtxtEPOSPrinterID2.getText().trim());
        } else {
            config.setProperty("machine.printer.2", sMachinePrinter2);
        }

        String sMachinePrinter3 = comboValue(jcboMachinePrinter3.getSelectedItem());
        if ("epson".equals(sMachinePrinter3) || "tmu220".equals(sMachinePrinter3) || "star".equals(sMachinePrinter3) || "ithaca".equals(sMachinePrinter3) || "surepos".equals(sMachinePrinter3)) {
            config.setProperty("machine.printer.3", sMachinePrinter3 + ":" + comboValue(jcboConnPrinter3.getSelectedItem()) + "," + comboValue(jcboSerialPrinter3.getSelectedItem()));
        } else if ("javapos".equals(sMachinePrinter3)) {
            config.setProperty("machine.printer.3", sMachinePrinter3 + ":" + m_jtxtJPOSPrinter3.getText() + "," + m_jtxtJPOSDrawer3.getText());
        } else if ("printer".equals(sMachinePrinter3)) {
            config.setProperty("machine.printer.3", sMachinePrinter3 + ":" + printer3printerparams.getParameters());
        } else if ("epos".equals(sMachinePrinter3)) {
            config.setProperty("machine.printer.3", sMachinePrinter3 + ":" + m_jtxtEPOSPrinterIP3.getText() + "," + m_jtxtEPOSPrinterID3.getText());
        } else {
            config.setProperty("machine.printer.3", sMachinePrinter3);
        }

        String sMachineDisplay = comboValue(jcboMachineDisplay.getSelectedItem());
        if ("epson".equals(sMachineDisplay) || "ld200".equals(sMachineDisplay) || "surepos".equals(sMachineDisplay)) {
            config.setProperty("machine.display", sMachineDisplay + ":" + comboValue(jcboConnDisplay.getSelectedItem()) + "," + comboValue(jcboSerialDisplay.getSelectedItem()));
        } else if ("javapos".equals(sMachineDisplay)) {
            config.setProperty("machine.display", sMachineDisplay + ":" + m_jtxtJPOSName.getText());
        } else {
            config.setProperty("machine.display", sMachineDisplay);
        }

        // La bascula
        String sMachineScale = comboValue(jcboMachineScale.getSelectedItem());
        if ("dialog1".equals(sMachineScale) || "samsungesp".equals(sMachineScale)) {
            config.setProperty("machine.scale", sMachineScale + ":" + comboValue(jcboSerialScale.getSelectedItem()));
        } else {
            config.setProperty("machine.scale", sMachineScale);
        }

        // El scanner
        String sMachineScanner = comboValue(jcboMachineScanner.getSelectedItem());
        if ("scanpal2".equals(sMachineScanner)) {
            config.setProperty("machine.scanner", sMachineScanner + ":" + comboValue(jcboSerialScanner.getSelectedItem()));
        } else {
            config.setProperty("machine.scanner", sMachineScanner);
        }

        config.setProperty("machine.printername", comboValue(cboPrinters.getSelectedItem()));
        
        // Drawer
        String sMachineDrawer = comboValue(jcboMachineDrawer.getSelectedItem());
        if ("executable".equals(sMachineDrawer)) {
            config.setProperty("machine.drawer", sMachineDrawer + ":" + m_jtxtDrawerExecFile.getText());
        } else {
            config.setProperty("machine.drawer", sMachineDrawer);
        }

        dirty.setDirty(false);
    }

    private String unifySerialInterface(String sparam) {

        // for backward compatibility
        return ("rxtx".equals(sparam))
                ? "serial"
                : sparam;
    }

    private String comboValue(Object value) {
        return value == null ? "" : value.toString();
    }

    private void changeLAF() {

        final LAFInfo laf = (LAFInfo) jcboLAF.getSelectedItem();
        if (laf != null && !laf.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
            // The selected look and feel is different from the current look and feel.
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    try {
                        String lafname = laf.getClassName();
                        Object laf = Class.forName(lafname).newInstance();

                        if (laf instanceof LookAndFeel) {
                            UIManager.setLookAndFeel((LookAndFeel) laf);
                        } else if (laf instanceof SubstanceSkin) {
                            SubstanceLookAndFeel.setSkin((SubstanceSkin) laf);
                        }

                        SwingUtilities.updateComponentTreeUI(JPanelConfigGeneral.this.getTopLevelAncestor());
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    private static class LAFInfo {

        private String name;
        private String classname;

        public LAFInfo(String name, String classname) {
            this.name = name;
            this.classname = classname;
        }

        public String getName() {
            return name;
        }

        public String getClassName() {
            return classname;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtxtMachineHostname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jcboLAF = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jcboMachineScreenmode = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jcboTicketsBag = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jcboMachineDisplay = new javax.swing.JComboBox();
        m_jDisplayParams = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlblConnDisplay = new javax.swing.JLabel();
        jcboConnDisplay = new javax.swing.JComboBox();
        jlblDisplayPort = new javax.swing.JLabel();
        jcboSerialDisplay = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        m_jtxtJPOSName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jcboMachinePrinter = new javax.swing.JComboBox();
        m_jPrinterParams1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlblConnPrinter = new javax.swing.JLabel();
        jcboConnPrinter = new javax.swing.JComboBox();
        jlblPrinterPort = new javax.swing.JLabel();
        jcboSerialPrinter = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        m_jtxtJPOSPrinter = new javax.swing.JTextField();
        m_jtxtJPOSDrawer = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        m_jtxtEPOSPrinterIP = new javax.swing.JTextField();
        m_jtxtEPOSPrinterID = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jcboMachinePrinter2 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        m_jPrinterParams2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jlblConnPrinter2 = new javax.swing.JLabel();
        jcboConnPrinter2 = new javax.swing.JComboBox();
        jlblPrinterPort2 = new javax.swing.JLabel();
        jcboSerialPrinter2 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        m_jtxtJPOSPrinter2 = new javax.swing.JTextField();
        m_jtxtJPOSDrawer2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        m_jtxtEPOSPrinterIP2 = new javax.swing.JTextField();
        m_jtxtEPOSPrinterID2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jcboMachinePrinter3 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jcboMachineScale = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jcboMachineScanner = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cboPrinters = new javax.swing.JComboBox();
        m_jPrinterParams3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jlblConnPrinter3 = new javax.swing.JLabel();
        jcboConnPrinter3 = new javax.swing.JComboBox();
        jlblPrinterPort3 = new javax.swing.JLabel();
        jcboSerialPrinter3 = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        m_jtxtJPOSPrinter3 = new javax.swing.JTextField();
        m_jtxtJPOSDrawer3 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        m_jtxtEPOSPrinterIP3 = new javax.swing.JTextField();
        m_jtxtEPOSPrinterID3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        m_jScaleParams = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jlblPrinterPort4 = new javax.swing.JLabel();
        jcboSerialScale = new javax.swing.JComboBox();
        m_jScannerParams = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jlblPrinterPort5 = new javax.swing.JLabel();
        jcboSerialScanner = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcboMachineDrawer = new javax.swing.JComboBox();
        m_jDrawerParams = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        m_jtxtDrawerExecFile = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jbtnDrawerChooseExec = new javax.swing.JButton();

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("Label.CashMachine"))); // NOI18N

        jLabel5.setText(AppLocal.getIntString("Label.MachineName")); // NOI18N

        jLabel2.setText(AppLocal.getIntString("label.looknfeel")); // NOI18N

        jLabel6.setText(AppLocal.getIntString("Label.MachineScreen")); // NOI18N

        jLabel16.setText(AppLocal.getIntString("Label.Ticketsbag")); // NOI18N

        jLabel15.setText(AppLocal.getIntString("Label.MachineDisplay")); // NOI18N

        jcboMachineDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineDisplayActionPerformed(evt);
            }
        });

        m_jDisplayParams.setLayout(new java.awt.CardLayout());
        m_jDisplayParams.add(jPanel2, "empty");

        jlblConnDisplay.setText(AppLocal.getIntString("label.machinedisplayconn")); // NOI18N

        jlblDisplayPort.setText(AppLocal.getIntString("label.machinedisplayport")); // NOI18N

        jcboSerialDisplay.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblConnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboConnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblDisplayPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboConnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblDisplayPort)
                    .addComponent(jcboSerialDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblConnDisplay))
                .addGap(59, 59, 59))
        );

        m_jDisplayParams.add(jPanel1, "comm");

        jLabel20.setText(AppLocal.getIntString("Label.Name")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSName, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtJPOSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(184, 184, 184))
        );

        m_jDisplayParams.add(jPanel3, "javapos");

        jLabel7.setText(AppLocal.getIntString("Label.MachinePrinter")); // NOI18N

        jcboMachinePrinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachinePrinterActionPerformed(evt);
            }
        });

        m_jPrinterParams1.setLayout(new java.awt.CardLayout());
        m_jPrinterParams1.add(jPanel5, "empty");

        jlblConnPrinter.setText(AppLocal.getIntString("label.machinedisplayconn")); // NOI18N

        jlblPrinterPort.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N

        jcboSerialPrinter.setEditable(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblConnPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboConnPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblPrinterPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboConnPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrinterPort)
                    .addComponent(jcboSerialPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblConnPrinter))
                .addGap(195, 195, 195))
        );

        m_jPrinterParams1.add(jPanel6, "comm");

        jLabel21.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        jLabel24.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSDrawer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtJPOSPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(m_jtxtJPOSDrawer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(184, 184, 184))
        );

        m_jPrinterParams1.add(jPanel4, "javapos");

        jLabel33.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        jLabel34.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterIP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtEPOSPrinterIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(m_jtxtEPOSPrinterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(224, 224, 224))
        );

        m_jPrinterParams1.add(jPanel18, "epos");

        jLabel18.setText(AppLocal.getIntString("Label.MachinePrinter2")); // NOI18N

        jcboMachinePrinter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachinePrinter2ActionPerformed(evt);
            }
        });

        jLabel19.setText(AppLocal.getIntString("Label.MachinePrinter3")); // NOI18N

        m_jPrinterParams2.setLayout(new java.awt.CardLayout());
        m_jPrinterParams2.add(jPanel7, "empty");

        jlblConnPrinter2.setText(AppLocal.getIntString("label.machinedisplayconn")); // NOI18N

        jlblPrinterPort2.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N

        jcboSerialPrinter2.setEditable(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblConnPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboConnPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblPrinterPort2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboConnPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrinterPort2)
                    .addComponent(jcboSerialPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblConnPrinter2))
                .addGap(205, 205, 205))
        );

        m_jPrinterParams2.add(jPanel8, "comm");

        jLabel27.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        jLabel22.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSDrawer2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtJPOSPrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(m_jtxtJPOSDrawer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(184, 184, 184))
        );

        m_jPrinterParams2.add(jPanel11, "javapos");

        jLabel31.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        jLabel32.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterID2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtEPOSPrinterIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(m_jtxtEPOSPrinterID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(224, 224, 224))
        );

        m_jPrinterParams2.add(jPanel15, "epos");

        jcboMachinePrinter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachinePrinter3ActionPerformed(evt);
            }
        });

        jLabel25.setText(AppLocal.getIntString("label.scale")); // NOI18N

        jcboMachineScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineScaleActionPerformed(evt);
            }
        });

        jLabel26.setText(AppLocal.getIntString("label.scanner")); // NOI18N

        jcboMachineScanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineScannerActionPerformed(evt);
            }
        });

        jLabel1.setText(AppLocal.getIntString("label.reportsprinter")); // NOI18N

        m_jPrinterParams3.setLayout(new java.awt.CardLayout());
        m_jPrinterParams3.add(jPanel9, "empty");

        jlblConnPrinter3.setText(AppLocal.getIntString("label.machinedisplayconn")); // NOI18N

        jlblPrinterPort3.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N

        jcboSerialPrinter3.setEditable(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblConnPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboConnPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblPrinterPort3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboConnPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrinterPort3)
                    .addComponent(jcboSerialPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblConnPrinter3))
                .addGap(125, 125, 125))
        );

        m_jPrinterParams3.add(jPanel10, "comm");

        jLabel28.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        jLabel23.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jtxtJPOSDrawer3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtJPOSPrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(m_jtxtJPOSDrawer3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(224, 224, 224))
        );

        m_jPrinterParams3.add(jPanel12, "javapos");

        jLabel29.setText(AppLocal.getIntString("label.javapos.printer")); // NOI18N

        jLabel30.setText(AppLocal.getIntString("label.javapos.drawer")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtEPOSPrinterID3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtEPOSPrinterIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(m_jtxtEPOSPrinterID3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(224, 224, 224))
        );

        m_jPrinterParams3.add(jPanel14, "epos");

        m_jScaleParams.setLayout(new java.awt.CardLayout());
        m_jScaleParams.add(jPanel16, "empty");

        jlblPrinterPort4.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N

        jcboSerialScale.setEditable(true);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblPrinterPort4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialScale, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboSerialScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrinterPort4))
                .addGap(135, 135, 135))
        );

        m_jScaleParams.add(jPanel17, "comm");

        m_jScannerParams.setLayout(new java.awt.CardLayout());
        m_jScannerParams.add(jPanel24, "empty");

        jlblPrinterPort5.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N

        jcboSerialScanner.setEditable(true);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblPrinterPort5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboSerialScanner, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboSerialScanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrinterPort5))
                .addGap(235, 235, 235))
        );

        m_jScannerParams.add(jPanel19, "comm");

        jLabel3.setText(AppLocal.getIntString("label.drawer")); // NOI18N

        jcboMachineDrawer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineDrawerActionPerformed(evt);
            }
        });

        m_jDrawerParams.setPreferredSize(new java.awt.Dimension(484, 155));
        m_jDrawerParams.setLayout(new java.awt.CardLayout());
        m_jDrawerParams.add(jPanel20, "empty");

        jLabel37.setText(AppLocal.getIntString("label.drawer.filepath")); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        jbtnDrawerChooseExec.setText(bundle.getString("button.drawer.choosefile")); // NOI18N
        jbtnDrawerChooseExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDrawerChooseExecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jtxtDrawerExecFile, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDrawerChooseExec, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jtxtDrawerExecFile, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jbtnDrawerChooseExec, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(222, 222, 222))
        );

        m_jDrawerParams.add(jPanel23, "exec");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachineDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jDisplayParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachinePrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPrinterParams1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachinePrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPrinterParams2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachinePrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPrinterParams3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachineScale, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jScaleParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboMachineScanner, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboPrinters, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jScannerParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtMachineHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboLAF, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboMachineScreenmode, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboTicketsBag, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboMachineDrawer, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jDrawerParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtMachineHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcboLAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcboMachineScreenmode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jcboTicketsBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jcboMachineDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jDisplayParams, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jcboMachinePrinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jPrinterParams1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jcboMachinePrinter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jPrinterParams2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jcboMachinePrinter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jPrinterParams3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jcboMachineScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jScaleParams, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jcboMachineScanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cboPrinters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(m_jScannerParams, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jcboMachineDrawer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jDrawerParams, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcboMachineScannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineScannerActionPerformed
        CardLayout cl = (CardLayout) (m_jScannerParams.getLayout());

        if ("scanpal2".equals(jcboMachineScanner.getSelectedItem())) {
            cl.show(m_jScannerParams, "comm");
        } else {
            cl.show(m_jScannerParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineScannerActionPerformed

    private void jcboMachineScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineScaleActionPerformed
        CardLayout cl = (CardLayout) (m_jScaleParams.getLayout());

        if ("dialog1".equals(jcboMachineScale.getSelectedItem()) || "samsungesp".equals(jcboMachineScale.getSelectedItem())) {
            cl.show(m_jScaleParams, "comm");
        } else {
            cl.show(m_jScaleParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineScaleActionPerformed

    private void jcboMachinePrinter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachinePrinter3ActionPerformed
        CardLayout cl = (CardLayout) (m_jPrinterParams3.getLayout());

        if ("epson".equals(jcboMachinePrinter3.getSelectedItem()) || "tmu220".equals(jcboMachinePrinter3.getSelectedItem()) || "star".equals(jcboMachinePrinter3.getSelectedItem()) || "ithaca".equals(jcboMachinePrinter3.getSelectedItem()) || "surepos".equals(jcboMachinePrinter3.getSelectedItem())) {
            cl.show(m_jPrinterParams3, "comm");
        } else if ("javapos".equals(jcboMachinePrinter3.getSelectedItem())) {
            cl.show(m_jPrinterParams3, "javapos");
        } else if ("printer".equals(jcboMachinePrinter3.getSelectedItem())) {
            cl.show(m_jPrinterParams3, "printer");
        } else if ("epos".equals(jcboMachinePrinter3.getSelectedItem())) {
            cl.show(m_jPrinterParams3, "epos");
        } else {
            cl.show(m_jPrinterParams3, "empty");
        }
    }//GEN-LAST:event_jcboMachinePrinter3ActionPerformed

    private void jcboMachinePrinter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachinePrinter2ActionPerformed
        CardLayout cl = (CardLayout) (m_jPrinterParams2.getLayout());

        if ("epson".equals(jcboMachinePrinter2.getSelectedItem()) || "tmu220".equals(jcboMachinePrinter2.getSelectedItem()) || "star".equals(jcboMachinePrinter2.getSelectedItem()) || "ithaca".equals(jcboMachinePrinter2.getSelectedItem()) || "surepos".equals(jcboMachinePrinter2.getSelectedItem())) {
            cl.show(m_jPrinterParams2, "comm");
        } else if ("javapos".equals(jcboMachinePrinter2.getSelectedItem())) {
            cl.show(m_jPrinterParams2, "javapos");
        } else if ("printer".equals(jcboMachinePrinter2.getSelectedItem())) {
            cl.show(m_jPrinterParams2, "printer");
        } else if ("epos".equals(jcboMachinePrinter2.getSelectedItem())) {
            cl.show(m_jPrinterParams2, "epos");
        } else {
            cl.show(m_jPrinterParams2, "empty");
        }
    }//GEN-LAST:event_jcboMachinePrinter2ActionPerformed

    private void jcboMachineDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineDisplayActionPerformed
        CardLayout cl = (CardLayout) (m_jDisplayParams.getLayout());

        if ("epson".equals(jcboMachineDisplay.getSelectedItem()) || "ld200".equals(jcboMachineDisplay.getSelectedItem()) || "surepos".equals(jcboMachineDisplay.getSelectedItem())) {
            cl.show(m_jDisplayParams, "comm");
        } else if ("javapos".equals(jcboMachineDisplay.getSelectedItem())) {
            cl.show(m_jDisplayParams, "javapos");
        } else {
            cl.show(m_jDisplayParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineDisplayActionPerformed

    private void jcboMachinePrinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachinePrinterActionPerformed
        CardLayout cl = (CardLayout) (m_jPrinterParams1.getLayout());

        if ("epson".equals(jcboMachinePrinter.getSelectedItem()) || "tmu220".equals(jcboMachinePrinter.getSelectedItem()) || "star".equals(jcboMachinePrinter.getSelectedItem()) || "ithaca".equals(jcboMachinePrinter.getSelectedItem()) || "surepos".equals(jcboMachinePrinter.getSelectedItem())) {
            cl.show(m_jPrinterParams1, "comm");
        } else if ("javapos".equals(jcboMachinePrinter.getSelectedItem())) {
            cl.show(m_jPrinterParams1, "javapos");
        } else if ("printer".equals(jcboMachinePrinter.getSelectedItem())) {
            cl.show(m_jPrinterParams1, "printer");
        } else if ("epos".equals(jcboMachinePrinter.getSelectedItem())) {
            cl.show(m_jPrinterParams1, "epos");
        } else {
            cl.show(m_jPrinterParams1, "empty");
        }
    }//GEN-LAST:event_jcboMachinePrinterActionPerformed

    private void jbtnDrawerChooseExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDrawerChooseExecActionPerformed
        File current = null;
        try {
            current = new File(m_jtxtDrawerExecFile.getText());
        } catch (Exception e) {
        }
        if (current != null) {
            m_jDrawerExecFileChooser = new JFileChooser(current);
        } else {
            m_jDrawerExecFileChooser = new JFileChooser();
        }
        if(m_jDrawerExecFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            m_jtxtDrawerExecFile.setText(m_jDrawerExecFileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jbtnDrawerChooseExecActionPerformed

    private void jcboMachineDrawerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineDrawerActionPerformed
        CardLayout cl = (CardLayout) (m_jDrawerParams.getLayout());

        if ("executable".equals(jcboMachineDrawer.getSelectedItem())) {
            cl.show(m_jDrawerParams, "exec");
        } else {
            cl.show(m_jDrawerParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineDrawerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboPrinters;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtnDrawerChooseExec;
    private javax.swing.JComboBox jcboConnDisplay;
    private javax.swing.JComboBox jcboConnPrinter;
    private javax.swing.JComboBox jcboConnPrinter2;
    private javax.swing.JComboBox jcboConnPrinter3;
    private javax.swing.JComboBox jcboLAF;
    private javax.swing.JComboBox jcboMachineDisplay;
    private javax.swing.JComboBox jcboMachineDrawer;
    private javax.swing.JComboBox jcboMachinePrinter;
    private javax.swing.JComboBox jcboMachinePrinter2;
    private javax.swing.JComboBox jcboMachinePrinter3;
    private javax.swing.JComboBox jcboMachineScale;
    private javax.swing.JComboBox jcboMachineScanner;
    private javax.swing.JComboBox jcboMachineScreenmode;
    private javax.swing.JComboBox jcboSerialDisplay;
    private javax.swing.JComboBox jcboSerialPrinter;
    private javax.swing.JComboBox jcboSerialPrinter2;
    private javax.swing.JComboBox jcboSerialPrinter3;
    private javax.swing.JComboBox jcboSerialScale;
    private javax.swing.JComboBox jcboSerialScanner;
    private javax.swing.JComboBox jcboTicketsBag;
    private javax.swing.JLabel jlblConnDisplay;
    private javax.swing.JLabel jlblConnPrinter;
    private javax.swing.JLabel jlblConnPrinter2;
    private javax.swing.JLabel jlblConnPrinter3;
    private javax.swing.JLabel jlblDisplayPort;
    private javax.swing.JLabel jlblPrinterPort;
    private javax.swing.JLabel jlblPrinterPort2;
    private javax.swing.JLabel jlblPrinterPort3;
    private javax.swing.JLabel jlblPrinterPort4;
    private javax.swing.JLabel jlblPrinterPort5;
    private javax.swing.JTextField jtxtMachineHostname;
    private javax.swing.JPanel m_jDisplayParams;
    private javax.swing.JPanel m_jDrawerParams;
    private javax.swing.JPanel m_jPrinterParams1;
    private javax.swing.JPanel m_jPrinterParams2;
    private javax.swing.JPanel m_jPrinterParams3;
    private javax.swing.JPanel m_jScaleParams;
    private javax.swing.JPanel m_jScannerParams;
    private javax.swing.JTextField m_jtxtDrawerExecFile;
    private javax.swing.JTextField m_jtxtEPOSPrinterID;
    private javax.swing.JTextField m_jtxtEPOSPrinterID2;
    private javax.swing.JTextField m_jtxtEPOSPrinterID3;
    private javax.swing.JTextField m_jtxtEPOSPrinterIP;
    private javax.swing.JTextField m_jtxtEPOSPrinterIP2;
    private javax.swing.JTextField m_jtxtEPOSPrinterIP3;
    private javax.swing.JTextField m_jtxtJPOSDrawer;
    private javax.swing.JTextField m_jtxtJPOSDrawer2;
    private javax.swing.JTextField m_jtxtJPOSDrawer3;
    private javax.swing.JTextField m_jtxtJPOSName;
    private javax.swing.JTextField m_jtxtJPOSPrinter;
    private javax.swing.JTextField m_jtxtJPOSPrinter2;
    private javax.swing.JTextField m_jtxtJPOSPrinter3;
    // End of variables declaration//GEN-END:variables
}
