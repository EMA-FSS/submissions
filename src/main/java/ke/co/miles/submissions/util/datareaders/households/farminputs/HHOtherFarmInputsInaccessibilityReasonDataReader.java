/*
 * Copyright (C) 2020 The Second Mile
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package ke.co.miles.submissions.util.datareaders.households.farminputs;

import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import ke.co.miles.submissions.util.datareaders.households.AbstractHouseholdDataReader;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;
import ke.co.miles.submissions.util.databuilders.households.HouseholdDataBuilder;
import ke.co.miles.submissions.util.forms.fields.households.HHFormField;
import lombok.extern.slf4j.Slf4j;

/**
 * @since 0.0.1
 * @author Kwaje Anthony <tony@miles.co.ke>
 * @version 1.0
 */
@Slf4j
public class HHOtherFarmInputsInaccessibilityReasonDataReader extends AbstractHouseholdDataReader {

  public HHOtherFarmInputsInaccessibilityReasonDataReader(JsonParser parser) {
    super(parser);
  }

  @Override
  public boolean read(HouseholdDataBuilder hdb) {

    log.trace("Entering Other Farm Input Inaccessibility Reasons Data Reader");

    // Initialize the result status flag
    log.trace("Setting the processing status to false");
    boolean status = false;

    try {

      log.debug("Field Name = {}", parser.getString());

      // Check if the target field name is equivalent to the field name that the processor is
      // responsible for
      log.trace("Checking if Field Name =  {}", HHFormField.SECTION_B_FARM_INPUTS_NONE_OTHER.getFieldName());
      if (parser.getString().equals(HHFormField.SECTION_B_FARM_INPUTS_NONE_OTHER.getFieldName())) {

        // The target field name is equivalent to the field name that the processor is responsible
        // for
        log.trace("Field Name =  {}", HHFormField.SECTION_B_FARM_INPUTS_NONE_OTHER.getFieldName());

        // Parse the Next Token
        log.trace("Parsing the Next Token");
        Event e = parser.next();

        // Check if the Next Token is a Value String
        log.trace("Checking if the Next Token is a Value String");
        if (e == VALUE_STRING) {

          log.trace("The Next Token is a Value String");

          // Read in the Other Farm Input Inaccessibility Reasons from the Value String
          log.trace("Reading in the Other Farm Input Inaccessibility Reasons Value from the Value String");
          log.debug("Other Farm Input Inaccessibility Reasons = {}", parser.getString());
          String reason = parser.getString();
          if (reason != null && !reason.trim().isEmpty()) {
            hdb.otherFarmInputInaccessibilityReason(reason);
          }

          // Update the processing status flag
          log.trace("Setting the processing status to true");
          status = true;

        } else {
          log.warn("The Next Token is not a Value String");
          log.error("An invalid Other Farm Input Inaccessibility Reasons Value has been passed to the data processor");
        }
      } else {
        if (next != null) {
          log.trace("Calling the next Data Reader");
          return next.read(hdb);
        } else {
          log.warn(
              "Could not find a downline Data Reader to forward the Data Processing Request to");
        }
      }
    } catch (Exception e) {
      log.error("An invalid field name has been passed to the data processor", e);
    }

    log.debug("Returning {}", status);
    return status;
  }
}
