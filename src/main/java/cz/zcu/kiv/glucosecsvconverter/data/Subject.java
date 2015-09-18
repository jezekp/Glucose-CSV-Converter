package cz.zcu.kiv.glucosecsvconverter.data;

import java.util.List;

/***********************************************************************************************************************
 * This file is part of the Glucose-CSV-CsvConverterImpl project
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
 * Subject, 2015/09/17 14:58 petr-jezek
 **********************************************************************************************************************/
public class Subject {

    private String givenName;
    private String surname;
    private List<TimeSegment> timeSegments;
    private List<Meal> meals;


    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<TimeSegment> getTimeSegments() {
        return timeSegments;
    }

    public void setTimeSegments(List<TimeSegment> timeSegments) {
        this.timeSegments = timeSegments;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
