package de.mpicbg.scf.rhaase.fiji.ij2course.scijavaservices.extra;

import fiji.plugin.trackmate.Model;
import fiji.plugin.trackmate.Spot;
import fiji.plugin.trackmate.TrackMate;
import fiji.plugin.trackmate.action.AbstractTMAction;
import fiji.plugin.trackmate.action.TrackMateAction;
import fiji.plugin.trackmate.action.TrackMateActionFactory;
import fiji.plugin.trackmate.gui.TrackMateGUIController;
import fiji.plugin.trackmate.gui.TrackMateWizard;
import javax.swing.*;
import org.scijava.command.Command;
import org.scijava.plugin.Plugin;

/**
 * Author: Robert Haase, Scientific Computing Facility, MPI-CBG Dresden,
 * rhaase@mpi-cbg.de
 * Date: May 2017
 * <p>
 * Copyright 2017 Max Planck Institute of Molecular Cell Biology and Genetics,
 * Dresden, Germany
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
@Plugin(type = Command.class, menuPath = "")
public class TrackMateExtension extends AbstractTMAction {


    public static final ImageIcon ICON = new ImageIcon(TrackMateWizard.class.getResource("images/calculator.png"));
    public static final String NAME = "Print out all points to stdout";
    public static final String KEY = "PRINT_OUT_ALL_POINTS";
    public static final String INFO_TEXT = "<html>"
            + "This action prints out all spot positions on stdout."
            + "</html>";

    @Override
    public void execute(TrackMate trackmate) {
        final Model model = trackmate.getModel();

        final Iterable<Spot> iterable = model.getSpots().iterable(true);

        for (final Spot spot : iterable) {
            double x = spot.getFeature(Spot.POSITION_X);
            double y = spot.getFeature(Spot.POSITION_Y);
            double t = spot.getFeature(Spot.POSITION_T);

            System.out.println("There is a spot at: " + x + "/" + y + "/" + t);
        }
    }

    @Plugin(type = TrackMateActionFactory.class)
    public static class Factory implements TrackMateActionFactory {

        @Override
        public String getInfoText() {
            return INFO_TEXT;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public String getKey() {
            return KEY;
        }

        @Override
        public ImageIcon getIcon() {
            return ICON;
        }

        @Override
        public TrackMateAction create(final TrackMateGUIController controller) {
            return new TrackMateExtension();
        }
    }
}
