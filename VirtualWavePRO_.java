// Author: Hernández, Irving
// v.06.09.2016
// Java

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;

public class SingleWP_ implements PlugIn {

	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
		IJ.run(imp, "Set Scale...", "distance=1 known=2 unit=mm");
		IJ.run("Set Measurements...", "stack centroid redirect=None decimal=8");
		Prefs.blackBackground = false;
		IJ.run(imp, "Make Binary", "method=Otsu background=Light calculate");
		imp.setRoi(215,57,319,486);
		IJ.run(imp, "Analyze Particles...", "  circularity=0.00-0.99 display clear stack");
		IJ.saveAs("Results", "C:\\WavePro\\frames\\Out_data.xls");
	}

}
