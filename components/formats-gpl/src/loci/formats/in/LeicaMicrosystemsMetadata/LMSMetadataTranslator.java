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

package loci.formats.in.LeicaMicrosystemsMetadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import loci.formats.CoreMetadata;
import loci.formats.FormatException;
import loci.formats.MetadataTools;
import loci.formats.in.LeicaMicrosystemsMetadata.doc.LMSImageXmlDocument;
import loci.formats.meta.MetadataStore;

/**
 * LMSMetadataTranslator sets up the whole metadata translation for all images of an LMSFileReader,
 * translating LMS image XML to the reader's CoreMetadata and MetadataStore, and mapping LMS image and
 * instrument metadata to OME metadata.
 * 
 * @author Constanze Wendlandt constanze.wendlandt at leica-microsystems.com
 */
public class LMSMetadataTranslator {
  // -- Fields --
  private LMSFileReader r;
  MetadataStore store;

  // -- Constructor --
  public LMSMetadataTranslator(LMSFileReader reader) {
    this.r = reader;
    store = r.makeFilterMetadata();
  }

  public void translateMetadata(List<LMSImageXmlDocument> docs) throws FormatException, IOException {
    initCoreMetadata(docs.size());
    r.setSeries(0);

    for (int i = 0; i < docs.size(); i++) {
      r.setSeries(i);

      SingleImageTranslator translator = new SingleImageTranslator(docs.get(i), i, docs.size(), r);
      r.metadataTranslators.add(translator);
      translator.translate();
    }
    r.setSeries(0);

    MetadataTools.populatePixels(store, this.r, true, false);

    // after the following call, we don't have 1 CoreMetadata per xlif-referenced
    // image, but 1 CoreMetadata per series ( = tile )!
    setCoreMetadataForTiles();
  }

  private void initCoreMetadata(int len) {
    r.setCore(new ArrayList<CoreMetadata>(len));
    r.getCore().clear();

    for (int i = 0; i < len; i++) {
      CoreMetadata ms = new CoreMetadata();
      ms.orderCertain = true;
      ms.metadataComplete = true;
      ms.littleEndian = true;
      ms.falseColor = true;
      r.getCore().add(ms);
    }
  }

  private void setCoreMetadataForTiles() {
    ArrayList<CoreMetadata> core = new ArrayList<CoreMetadata>();
    for (int i = 0; i < r.getCore().size(); i++) {
      for (int tile = 0; tile < r.metadataTranslators.get(i).dimensionStore.tileCount; tile++) {
        core.add(r.getCore().get(i));
      }
    }
    r.setCore(core);
  }
}
