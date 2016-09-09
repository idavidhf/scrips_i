// Author: Hernández, Irving
// Institution: Universidade Federal do Rio de Janeiro
// Department: Ocean Engineering
// Laboratory: Ocean Structures Nucleus (NEO) - COPPE
// v.06.09.2016
// Language: Java
// Project: FIJI/ImageJ plug-in for Virtual-WavePRO


// Importing only the necessary libraries
import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;

// Release the class (consider to finalize the name with underscore "_")
public class SingleWP_ implements PlugIn {

	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
		// Here "distance" defines an image measurement in pixels and
		// "known" the real measurements in an specific "unit"
		IJ.run(imp, "Set Scale...", "distance=1 known=2 unit=mm");
		// We are interested on the centroid position of particles,
		// then is only needed to define the precission of measurements
		// in the "decimal" definition
		IJ.run("Set Measurements...", "stack centroid redirect=None decimal=8");
		Prefs.blackBackground = false;
		// For black particles define Ligth in "background" or Dark in
		// case of withe particles
		IJ.run(imp, "Make Binary", "method=Otsu background=Light calculate");
		// Define a rectangular region of interest (R.O.I.) in the form (x1,y1,x2,y2)
		// these coordinates correspond to the top left and the bottom rigth
		// vertex of the rectangle
		imp.setRoi(215,57,319,486);
		// Particle analysis will be check all particles sizes, to filter
		// noise particles, use the "circularity" parameter range. 
		IJ.run(imp, "Analyze Particles...", "  circularity=0.00-0.99 display clear stack");
		IJ.saveAs("Results", "C:\\WavePro\\frames\\Out_data.xls");
	}

}
