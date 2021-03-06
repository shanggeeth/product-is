/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.identity.integration.test.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.wso2.carbon.integration.common.utils.mgt.ServerConfigurationManager;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.identity.integration.common.utils.ISIntegrationTest;

import java.io.File;

public class GraalJsScriptEngineInitializerTestCase extends ISIntegrationTest {

    private ServerConfigurationManager scm;
    private File defaultConfigFile;

    @BeforeTest(alwaysRun = true)
    public void initScriptEngineConfig() throws Exception {

        super.init();
        String carbonHome = CarbonUtils.getCarbonHome();
        defaultConfigFile = getDeploymentTomlFile(carbonHome);
        File scriptEngineConfigFile = new File(getISResourceLocation() + File.separator + "scriptengine"
                + File.separator + "graaljs_script_engine_config.toml");
        scm = new ServerConfigurationManager(isServer);
        scm.applyConfiguration(scriptEngineConfigFile, defaultConfigFile, true, true);
    }

    @AfterTest(alwaysRun = true)
    public void resetScriptEngineConfig() throws Exception {

        super.init();
        scm.restoreToLastConfiguration(false);
    }
}
