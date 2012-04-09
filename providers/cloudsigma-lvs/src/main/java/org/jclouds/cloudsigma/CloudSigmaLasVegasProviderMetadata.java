/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.cloudsigma;

import java.net.URI;

import org.jclouds.providers.BaseProviderMetadata;

/**
 * Implementation of {@link org.jclouds.types.ProviderMetadata} for CloudSigma Las Vegas.
 *
 * @author Adrian Cole
 */
public class CloudSigmaLasVegasProviderMetadata extends BaseProviderMetadata {

   public CloudSigmaLasVegasProviderMetadata() {
      this(builder()
            .id("cloudsigma-lvs")
            .name("CloudSigma Las Vegas")
            .api(new CloudSigmaApiMetadata())
            .homepage(URI.create("http://www.cloudsigma.com/en/our-cloud/features"))
            .console(URI.create("https://gui.lvs.cloudsigma.com/"))
            .iso3166Codes("US-NV"));
   }

   // below are so that we can reuse builders, toString, hashCode, etc.
   // we have to set concrete classes here, as our base class cannot be
   // concrete due to serviceLoader
   protected CloudSigmaLasVegasProviderMetadata(ConcreteBuilder builder) {
      super(builder);
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {

      @Override
      public CloudSigmaLasVegasProviderMetadata build() {
         return new CloudSigmaLasVegasProviderMetadata(this);
      }
   }

   public static ConcreteBuilder builder() {
      return new ConcreteBuilder();
   }

   public ConcreteBuilder toBuilder() {
      return builder().fromProviderMetadata(this);
   }
}
