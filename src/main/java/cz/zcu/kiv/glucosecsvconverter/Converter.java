package cz.zcu.kiv.glucosecsvconverter;

import cz.zcu.kiv.glucosecsvconverter.data.Subject;

import java.io.InputStream;

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
 * Converter, 2015/09/18 09:16 petr-jezek
 **********************************************************************************************************************/
public interface Converter  {
    Subject convert(InputStream csv) throws ConvertException;
    Subject convert(InputStream csv, String separator) throws ConvertException;
}
