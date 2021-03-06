// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.testing;

import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InProject {
  /**
   * Locates a file in the current project
   *
   * @param path path to file to locate from root of project
   * @return file being sought, if it exists
   * @throws org.openqa.selenium.WebDriverException wrapped FileNotFoundException if file could not
   *         be found
   */
  public static Path locate(String path) {
    Path dir = Paths.get(".").toAbsolutePath();
    while (dir != null && !dir.equals(dir.getParent())) {
      Path needle = dir.resolve(path);
      if (Files.exists(needle)) {
        return needle;
      }
      dir = dir.getParent();
    }

    throw new WebDriverException(new FileNotFoundException(
        "Could not find " + path + " in the project"));
  }
}
