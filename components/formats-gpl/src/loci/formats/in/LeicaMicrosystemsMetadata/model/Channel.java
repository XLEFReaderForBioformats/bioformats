/*
 * #%L
 * OME Bio-Formats package for reading and converting biological file formats.
 * %%
 * Copyright (C) 2005 - 2017 Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package loci.formats.in.LeicaMicrosystemsMetadata.model;

import ome.xml.model.primitives.Color;

/**
 * This class represents image channels extracted from LMS image xmls.
 * 
 * @author Constanze Wendlandt constanze.wendlandt at leica-microsystems.com
 */
public class Channel {
  // -- Fields --
  public int channelTag;
  public int resolution;
  public double min;
  public double max;
  public String unit;
  public String lutName;
  public long bytesInc;
  public Color lutColor;
  public int lutColorIndex;

  public boolean isLutInverted;
  public long bitInc;
  public int dataType;
  public String nameOfMeasuredQuantity;

  public enum ChannelType {
    MONO,
    RED,
    GREEN,
    BLUE,
    // PHOTON_COUNTING,
    // ARRIVAL_TIME
  }

  public static final int RED = 0;
  public static final int GREEN = 1;
  public static final int BLUE = 2;
  public static final int CYAN = 3;
  public static final int MAGENTA = 4;
  public static final int YELLOW = 5;
  public static final int GREY = 6;

  public ChannelType channelType;

  public String dye;
  public DetectorSetting detectorSetting;
  public LaserSetting laserSetting;
  public Filter filter;
  public double pinholeSize;
  public double exposureTime;
  public String channelName;

  // -- Constructor --
  public Channel() {
    setChannelType();
  }

  //-- Methods --
  private void setChannelType(){
    if (channelTag == 0) {
      channelType = ChannelType.MONO;
    } else {
      if (lutName.equals("Red")){
        channelType = ChannelType.RED;
      } else if (lutName.equals("Green")){
        channelType = ChannelType.GREEN;
      } else if (lutName.equals("Blue")){
        channelType = ChannelType.BLUE;
      }
    }
  }
}
