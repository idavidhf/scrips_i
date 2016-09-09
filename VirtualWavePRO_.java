// Author: Hernández, Irving
// v.06.09.2016
// Java

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;
import ij.process.*;
import ij.plugin.filter.ParticleAnalyzer;
import ij.plugin.filter.Analyzer;
import ij.measure.*;
import ij.gui.Plot;

public class VirtualWavePRO_ implements PlugIn {
    static String pthO="C:\\Temp\\out_wp_00.txt";
    static String minPS="1";
    static String maxPS="Infinity";
    static String minPC="0.00";
    static String maxPC="1.00";
    static String knPIXS="1.000";
    static String knUNIT="1.000";
    static String uniTs="mm";
    static int roix1=0,roiy1=0,roiw=1,roih=1;
    
    public void run(String arg) {
        GenericDialog gd = new GenericDialog("Virtual WavePRO by Hern\u00e1ndez & Hern\u00e1ndez-Fontes (2016).");
        gd.addStringField("Results Path: ", pthO,25);
        gd.addStringField("Min. Particle Size: ", minPS);
        gd.addStringField("M\u00e1x. Particle Size: ", maxPS);
        gd.addStringField("Min. Particle Circularity: ", minPC);
        gd.addStringField("M\u00e1x. Particle Circularity: ", maxPC);
        gd.addNumericField("R.O.I. X1 (pixels): ", roix1, 0);
        gd.addNumericField("R.O.I. Y1 (pixels): ", roiy1, 0);
        gd.addNumericField("R.O.I. Width (pixels): ", roiw, 0);
        gd.addNumericField("R.O.I. Height (pixels): ", roih, 0);
        gd.addStringField("Known dimension (pixels): ", knPIXS);
        gd.addStringField("Known dimension (units): ", knUNIT);
        gd.addStringField("Used Units: ", uniTs);
        gd.addMessage("COPPE/UFRJ (2016). All rigths are reserved.");
        //gd.addNumericField("M\u00e1n. Particle Size: ", height, 0);
        gd.showDialog();
        if (gd.wasCanceled()) return;
        pthO = gd.getNextString();
        minPS = gd.getNextString();
        maxPS = gd.getNextString();
        minPC = gd.getNextString();
        maxPC = gd.getNextString();
        roix1 = (int)gd.getNextNumber();
        roiy1 = (int)gd.getNextNumber();
        roiw = (int)gd.getNextNumber();
        roih = (int)gd.getNextNumber();
        knPIXS = gd.getNextString();
        knUNIT = gd.getNextString();
        uniTs = gd.getNextString();
        //height = (int)gd.getNextNumber();
        
        // Here begins the measurements
        ImagePlus imp = IJ.getImage();
        IJ.run("Set Measurements...", "stack centroid redirect=None decimal=8");
        Prefs.blackBackground = false;
        IJ.run(imp, "Make Binary", "method=Otsu background=Light calculate");
        imp.setRoi(roix1,roiy1,roiw,roih);
        IJ.run(imp, "Set Scale...", "distance="+knPIXS+" known="+knUNIT+" unit="+uniTs);
        IJ.run(imp, "Analyze Particles...", "  size="+minPS+"-"+maxPS+" circularity="+minPC+"-"+maxPC+" display clear stack");
        IJ.saveAs("Results", pthO);
    }
}
