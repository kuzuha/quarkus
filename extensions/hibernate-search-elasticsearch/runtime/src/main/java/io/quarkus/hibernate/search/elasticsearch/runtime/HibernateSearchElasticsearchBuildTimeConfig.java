/*
 * Copyright 2019 Red Hat, Inc.
 *
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
 */

package io.quarkus.hibernate.search.elasticsearch.runtime;

import java.util.Map;
import java.util.Optional;

import org.hibernate.search.backend.elasticsearch.cfg.ElasticsearchDialectName;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "hibernate-search", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class HibernateSearchElasticsearchBuildTimeConfig {

    /**
     * Configuration of the default backend.
     */
    public ElasticsearchBackendBuildTimeConfig elasticsearch;

    /**
     * If not using the default backend configuration, the name of the default backend that is part of the
     * {@link #additionalBackends}.
     */
    public Optional<String> defaultBackend;

    /**
     * Configuration of optional additional backends.
     */
    @ConfigItem(name = "elasticsearch.backends")
    public Map<String, ElasticsearchBackendBuildTimeConfig> additionalBackends;

    @ConfigGroup
    public static class ElasticsearchBackendBuildTimeConfig {
        /**
         * The dialect used to converse with the Elasticsearch cluster.
         * <p>
         * As the schema is generated without a connection to the server, this item is mandatory.
         */
        @ConfigItem
        public Optional<ElasticsearchDialectName> dialect;

        /**
         * The class or the name of the bean used to configure full text analysis (e.g. analyzers, normalizers).
         */
        @ConfigItem
        public Optional<Class<?>> analysisConfigurer;
    }
}
