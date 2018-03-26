/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.spring.datasource;

import io.shardingjdbc.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;
import io.shardingjdbc.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithmType;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * Master-slave datasource for spring namespace.
 *
 * @author zhangliang
 */
public class SpringMasterSlaveDataSource extends MasterSlaveDataSource {
    
    public SpringMasterSlaveDataSource(final Map<String, DataSource> dataSourceMap, final String name, final String masterDataSourceName, final Collection<String> slaveDataSourceNames, 
                                       final MasterSlaveLoadBalanceAlgorithm strategy, final Map<String, Object> configMap) throws SQLException {
        super(dataSourceMap, getMasterSlaveRuleConfiguration(name, masterDataSourceName, slaveDataSourceNames, strategy), configMap);
    }
    
    public SpringMasterSlaveDataSource(final Map<String, DataSource> dataSourceMap, final String name, final String masterDataSourceName, final Collection<String> slaveDataSourceNames, 
                                       final MasterSlaveLoadBalanceAlgorithmType strategyType, final Map<String, Object> configMap) throws SQLException {
        super(dataSourceMap, getMasterSlaveRuleConfiguration(name, masterDataSourceName, slaveDataSourceNames, strategyType.getAlgorithm()), configMap);
    }
    
    private static MasterSlaveRuleConfiguration getMasterSlaveRuleConfiguration(
            final String name, final String masterDataSourceName, final Collection<String> slaveDataSourceNames, final MasterSlaveLoadBalanceAlgorithm loadBalanceAlgorithm) {
        MasterSlaveRuleConfiguration result = new MasterSlaveRuleConfiguration();
        result.setName(name);
        result.setMasterDataSourceName(masterDataSourceName);
        result.setSlaveDataSourceNames(slaveDataSourceNames);
        result.setLoadBalanceAlgorithm(loadBalanceAlgorithm);
        return result;
    }
}
