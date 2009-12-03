/*
 * ome.xml.r200809.ome.OTFNode
 *
 *-----------------------------------------------------------------------------
 *
 *  Copyright (C) 2007 Open Microscopy Environment
 *      Massachusetts Institute of Technology,
 *      National Institutes of Health,
 *      University of Dundee,
 *      University of Wisconsin-Madison
 *
 *
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *-----------------------------------------------------------------------------
 */

/*-----------------------------------------------------------------------------
 *
 * THIS IS AUTOMATICALLY GENERATED CODE.  DO NOT MODIFY.
 * Created by curtis via xsd-fu on 2008-10-17 01:41:39-0500
 *
 *-----------------------------------------------------------------------------
 */

package ome.xml.r200809.ome;

import ome.xml.DOMUtil;
import ome.xml.OMEXMLNode;
import ome.xml.r200809.ome.*;
import ome.xml.r200809.spw.*;

import org.w3c.dom.Element;

public class OTFNode extends OMEXMLNode
{

	// -- Constructors --

	/** Constructs a OTF node with an associated DOM element. */
	public OTFNode(Element element)
	{
		super(element);
	}

	/**
	 * Constructs a OTF node with an associated DOM element beneath
	 * a given parent.
	 */
	public OTFNode(OMEXMLNode parent)
	{
		this(parent, true);
	}

	/**
	 * Constructs a OTF node with an associated DOM element beneath
	 * a given parent.
	 */
	public OTFNode(OMEXMLNode parent, boolean attach)
	{
		super(DOMUtil.createChild(parent.getDOMElement(),
		                          "OTF", attach));
	}

	// -- OTF API methods --

	// Attribute
	public String getPixelType()
	{
		return getStringAttribute("PixelType");
	}

	public void setPixelType(String pixelType)
	{
		setAttribute("PixelType", pixelType);
	}

	// Attribute
	public Integer getSizeX()
	{
		return getIntegerAttribute("SizeX");
	}

	public void setSizeX(Integer sizeX)
	{
		setAttribute("SizeX", sizeX);
	}

	// Attribute
	public Integer getSizeY()
	{
		return getIntegerAttribute("SizeY");
	}

	public void setSizeY(Integer sizeY)
	{
		setAttribute("SizeY", sizeY);
	}

	// Virtual, inferred back reference LogicalChannel_BackReference
	public int getReferringLogicalChannelCount()
	{
		return getReferringCount("LogicalChannel");
	}

	public java.util.List getReferringLogicalChannelList()
	{
		return getReferringNodes("LogicalChannel");
	}

	// Element which is complex and is an OME XML "Ref"
	public FilterSetNode getFilterSet()
	{
		return (FilterSetNode)
			getReferencedNode("FilterSet", "FilterSetRef");
	}

	public FilterSetRefNode getFilterSetRef()
	{
		return (FilterSetRefNode)
			getChildNode("FilterSetRef", "FilterSetRef");
	}

	// Attribute
	public Boolean getOpticalAxisAveraged()
	{
		return getBooleanAttribute("OpticalAxisAveraged");
	}

	public void setOpticalAxisAveraged(Boolean opticalAxisAveraged)
	{
		setAttribute("OpticalAxisAveraged", opticalAxisAveraged);
	}

	// Element which is complex and is an OME XML "Ref"
	public ObjectiveNode getObjective()
	{
		return (ObjectiveNode)
			getReferencedNode("Objective", "ObjectiveRef");
	}

	public ObjectiveRefNode getObjectiveRef()
	{
		return (ObjectiveRefNode)
			getChildNode("ObjectiveRef", "ObjectiveRef");
	}

	// Element which is not complex (has only a text node)
	public String getBinaryFile()
	{
		return getStringCData("BinaryFile");
	}

	public void setBinaryFile(String binaryFile)
	{
		setCData("BinaryFile", binaryFile);
	}

	// *** WARNING *** Unhandled or skipped property ID

	// -- OMEXMLNode API methods --

	public boolean hasID()
	{
		return true;
	}

}
