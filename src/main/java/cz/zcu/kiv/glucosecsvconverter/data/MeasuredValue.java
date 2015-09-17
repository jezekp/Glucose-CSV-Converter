package cz.zcu.kiv.glucosecsvconverter.data;

import java.util.Date;

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
 * MeasuredValue, 2015/09/17 15:28 petr-jezek
 **********************************************************************************************************************/
public class MeasuredValue {

    private TimeSegment timeSegment;
    private Date measuredAt;
    private double blood;
    private double ist;

    public TimeSegment getTimeSegment() {
        return timeSegment;
    }

    public void setTimeSegment(TimeSegment timeSegment) {
        this.timeSegment = timeSegment;
    }

    public double getBlood() {
        return blood;
    }

    public void setBlood(double blood) {
        this.blood = blood;
    }

    public double getIst() {
        return ist;
    }

    public void setIst(double ist) {
        this.ist = ist;
    }

    public Date getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(Date measuredAt) {
        this.measuredAt = measuredAt;
    }
}
