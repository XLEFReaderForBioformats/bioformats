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

package loci.formats.in.LeicaMicrosystemsMetadata.helpers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * LMSMainXmlNodes is a storage class that holds the main XML nodes and XML layout information from LMS XML files.
 * 
 * @author Constanze Wendlandt constanze.wendlandt at leica-microsystems.com
 */
public class LMSMainXmlNodes {
  public Element imageNode;
  public Element imageDescription;
  public Element hardwareSetting;
  
  public Element mainConfocalSetting;
  public Element masterConfocalSetting;
  public List<Element> sequentialConfocalSettings = new ArrayList<Element>();
  
  public Element mainCameraSetting;
  public Element masterCameraSetting;
  public List<Element> sequentialCameraSettings = new ArrayList<Element>();
  public Element widefieldChannelConfig;
  public List<Element> widefieldChannelInfos = new ArrayList<Element>();

  public NodeList scannerSettingRecords;
  public NodeList filterSettingRecords;

  public enum HardwareSettingLayout {
    OLD, // e.g. SP5                      --> <Attachment Name "HardwareSettingList" .. > <HardwareSetting...>
    NEW, // e.g. SP8, STELLARIS, MICA, ... --> <Attachment Name="HardwareSetting" ...>
    NONE // e.g. some depth map or multifocus images don't have a hardware setting
  }
  public HardwareSettingLayout hardwareSettingLayout;

  public enum DataSourceType {
    CAMERA, CONFOCAL
  }
  public DataSourceType dataSourceType;

  public enum CameraSettingsLayout {
    SIMPLE, //main AtlCameraSettingsDefinition exists and contains e.g. all widefield channel infos
    SEQUENTIAL // sequential AtlCameraSettingsDefinitions within LDM_Block_Widefield_Sequential exist and contain e.g. widefield channel infos
  }

  public CameraSettingsLayout cameraSettingsLayout;

  /**
   * Depending on hardware setting layout and data source type, it returns the main ATL setting that
   * shall be used for extracting further hardware settings information.
   */
  public Element getAtlSetting(){
    if (hardwareSettingLayout == HardwareSettingLayout.OLD){
      return dataSourceType == DataSourceType.CONFOCAL ? masterConfocalSetting : masterCameraSetting;
    } else {
      return dataSourceType == DataSourceType.CONFOCAL ? mainConfocalSetting : mainCameraSetting;
    }
  }
}
