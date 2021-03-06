/*
 * Copyright (C) 2020 The Second Mile
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. 
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package ke.co.miles.submissions.util.databuilders.common.observations;

import ke.co.miles.submissions.models.observation.Measurement;
import ke.co.miles.submissions.models.observation.Unit;
import ke.co.miles.submissions.util.common.observations.PhenomenonTypeEnum;
import ke.co.miles.submissions.util.common.observations.UnitEnum;

/**
 * 
 * @since 0.0.1
 * @author Kwaje Anthony <tony@miles.co.ke>
 * @version 1.0
 */
public class MeasurementBuilder {

    private int phenomenonTypeId;
    private String phenomenonType;    
    private Number value;
    private Unit unit;
    
    public MeasurementBuilder phenomenonTypeId(int phenomenonTypeId){
        this.phenomenonTypeId = phenomenonTypeId;
        return this;
    }   
    
    public MeasurementBuilder phenomenonType(String phenomenonType){
        this.phenomenonType = phenomenonType;
        return this;
    }
    
    public MeasurementBuilder phenomenonType(PhenomenonTypeEnum p){
        if(p != null) {
           this.phenomenonTypeId = p.getId(); 
           this.phenomenonType = p.getName();
        }
        
        return this;
    }    
    
    public MeasurementBuilder value(Number value){
        this.value = value;
        return this;
    }
    
    public MeasurementBuilder unit(UnitEnum unit){
        this.unit = new UnitBuilder()
                .id(unit.getId())
                .name(unit.getName())
                .abbreviation(unit.getAbbreviation())
                .build();
        return this;
    }   
    
    public Measurement build() {
        return new Measurement(phenomenonTypeId, phenomenonType, value, unit);
    }
}
