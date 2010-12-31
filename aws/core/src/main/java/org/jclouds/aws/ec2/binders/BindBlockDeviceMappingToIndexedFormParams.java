/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.aws.ec2.binders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

import org.jclouds.aws.ec2.domain.BlockDeviceMapping;
import org.jclouds.aws.ec2.domain.RunningInstance;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.utils.ModifyRequest;
import org.jclouds.rest.Binder;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;

/**
 * @author Oleksiy Yarmula
 * @author Adrian Cole
 */
public class BindBlockDeviceMappingToIndexedFormParams implements Binder {

   private static final String deviceNamePattern = "BlockDeviceMapping.%d.DeviceName";
   private static final String volumeIdPattern = "BlockDeviceMapping.%d.Ebs.VolumeId";
   private static final String deleteOnTerminationPattern = "BlockDeviceMapping.%d.Ebs.DeleteOnTermination";

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Object input) {
      checkArgument(checkNotNull(input, "input") instanceof BlockDeviceMapping,
            "this binder is only valid for BlockDeviceMapping");
      BlockDeviceMapping blockDeviceMapping = (BlockDeviceMapping) input;

      Builder<String, String> builder = ImmutableMultimap.<String, String> builder();
      int amazonOneBasedIndex = 1; // according to docs, counters must start with 1
      for (String ebsBlockDeviceName : blockDeviceMapping.getEbsBlockDevices().keySet()) {
         for (RunningInstance.EbsBlockDevice ebsBlockDevice : blockDeviceMapping.getEbsBlockDevices().get(
               ebsBlockDeviceName)) {

            // not null by contract
            builder.put(format(volumeIdPattern, amazonOneBasedIndex), ebsBlockDevice.getVolumeId());

            if (ebsBlockDeviceName != null) {
               builder.put(format(deviceNamePattern, amazonOneBasedIndex), ebsBlockDeviceName);
            }
            builder.put(format(deleteOnTerminationPattern, amazonOneBasedIndex),
                  String.valueOf(ebsBlockDevice.isDeleteOnTermination()));

            amazonOneBasedIndex++;
         }
      }
      ImmutableMultimap<String, String> forms = builder.build();
      return forms.size() == 0 ? request : ModifyRequest.putFormParams(request, forms);
   }

}
