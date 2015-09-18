package cz.zcu.kiv.glucosecsvconverter.converters;

import cz.zcu.kiv.glucosecsvconverter.ConvertException;
import cz.zcu.kiv.glucosecsvconverter.data.MeasuredValue;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import cz.zcu.kiv.glucosecsvconverter.data.TimeSegment;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
 * BloodCsvConverter, 2015/09/18 09:18 petr-jezek
 **********************************************************************************************************************/
public class BloodCsvConverter implements CsvConverter {

    public static final String HEADER_VALUE = "Action";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    @Override
    public Subject convert(List<String[]> rows) throws ConvertException {
        Subject subject = new Subject();
        List<TimeSegment> timeSegments = new LinkedList<>();
        subject.setTimeSegments(timeSegments);
        try {
            for (String[] line : rows) {
                TimeSegment timeSegment = new TimeSegment();
                timeSegments.add(timeSegment);
                List<MeasuredValue> measuredValues = new LinkedList<>();
                MeasuredValue measuredValue = new MeasuredValue();
                measuredValues.add(measuredValue);
                timeSegment.setMeasuredValues(measuredValues);
                measuredValue.setTimeSegment(timeSegment);
                measuredValue.setMeasuredAt(simpleDateFormat.parse(line[1] + " " + line[2]));
                String blood = line[3];
                if (blood != null && !blood.isEmpty()) {
                    measuredValue.setBlood(Double.parseDouble(blood));
                }
            }
        }catch (Exception e) {
            throw new ConvertException(e);
        }
        return subject;
    }
}
