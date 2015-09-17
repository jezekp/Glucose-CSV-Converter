package cz.zcu.kiv.glucosecsvconverter.data;

import java.util.List;

/***********************************************************************************************************************
 * This file is part of the Glucose-CSV-Converter project
 * <p>
 * ==========================================
 * <p>
 * Copyright (C) 2015 by University of West Bohemia (http://www.zcu.cz/en/)
 * <p>
 * **********************************************************************************************************************
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * **********************************************************************************************************************
 * <p>
 * TimeSegment, 2015/09/17 15:23 petr-jezek
 **********************************************************************************************************************/
public class TimeSegment {

    private Subject subject;
    private String device;
    private List<MeasuredValue> measuredValues;


    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<MeasuredValue> getMeasuredValues() {
        return measuredValues;
    }

    public void setMeasuredValues(List<MeasuredValue> measuredValues) {
        this.measuredValues = measuredValues;
    }
}
